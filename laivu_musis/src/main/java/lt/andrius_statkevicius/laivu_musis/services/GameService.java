package lt.andrius_statkevicius.laivu_musis.services;

import lt.andrius_statkevicius.laivu_musis.entities.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GameService extends WebService {

    public static final String JOIN_USER = "join?";
    public static final String SET_UP_GAME = "setup?";
    public static final String TURN = "turn?";
    public static final String RETURN_GAME_STATUS = "status?";

    LocalGameService localGameService = new LocalGameService();


    public GameData joinUser(String id) throws IOException, ParseException {
        StringBuilder url = new StringBuilder( SERVER_URL );
        url.append(JOIN_USER);
        url.append("user_id=");
        url.append( id );
        return performRequest( url.toString() );
    }

    public GameData setUpGame(String gameId, String playerId, String data) throws IOException, ParseException {
        StringBuilder url = new StringBuilder( SERVER_URL );
        url.append( SET_UP_GAME );
        url.append("game_id=");
        url.append( gameId );
        url.append( "&user_id=" );
        url.append( playerId );
        url.append( "&data=" ).append( data );
        return performRequest( url.toString() );
    }

    public GameData turn(String gameId, String playerId, String data) throws IOException, ParseException {
        StringBuilder url = new StringBuilder( SERVER_URL );
        url.append( TURN );
        url.append( "game_id=" );
        url.append( gameId );
        url.append( "&user_id=" );
        url.append( playerId );
        url.append( "&data=" ).append( data );
        return performRequest( url.toString() );
    }

    public GameData status(String gameId) throws IOException, ParseException {
        StringBuilder url = new StringBuilder( SERVER_URL );
        url.append( RETURN_GAME_STATUS );
        url.append( "game_id=" );
        url.append( gameId );
        return performRequest( url.toString() );
    }


    private GameData performRequest(String url) throws IOException, ParseException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet( url.toString() );
        HttpResponse response = client.execute( request );
        String responseAsString = convertInputStreamToString( response.getEntity().getContent() );
        if (response.getStatusLine().getStatusCode() == 200) {
            return convertJsonToGameData( responseAsString );
        }
        System.out.println( "Error  response: " + responseAsString );
        return null;
    }


    private GameData convertJsonToGameData(String body) throws ParseException {
        JSONParser parser = new JSONParser();

        JSONObject gameDataObj = (JSONObject) parser.parse( body );

        String gameId = (String) gameDataObj.get( "id" );
        String status = (String) gameDataObj.get( "status" );
        String nextTurnForUserId = (String) gameDataObj.get( "nextTurnForUserId" );
        String winnerUserId = (String) gameDataObj.get( "winnerUserId" );

        List<Event> events = new ArrayList<>();

        JSONArray eventsObj = (JSONArray) gameDataObj.get( "events" );
        for (Object eventObj : eventsObj) {
            JSONObject event = (JSONObject) eventObj;

            String userId = (String) event.get( "userId" );
            Boolean ishit = (Boolean) event.get( "hit" );
            Long date = (Long) event.get( "date" );
            JSONObject coordinateObj = (JSONObject) event.get( "coordinate" );
            String column = (String) coordinateObj.get( "column" );
            Long row = (Long) coordinateObj.get( "row" );

            Coordinate coordinate = localGameService.parseCoordinate( column + row );

            Event eventData = new Event(coordinate, userId,ishit, new Date(date));
            events.add( eventData );
        }

        return new GameData(gameId, nextTurnForUserId, status, winnerUserId, events);
    }

}


