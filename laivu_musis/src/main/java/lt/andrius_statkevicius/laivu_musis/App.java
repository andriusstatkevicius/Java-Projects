package lt.andrius_statkevicius.laivu_musis;

import lt.andrius_statkevicius.laivu_musis.entities.*;
import lt.andrius_statkevicius.laivu_musis.services.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class App {

    static Scanner scanner = new Scanner( System.in );
    static UserService userService = new UserService();
    static GameService gameService = new GameService();
    static LocalGameService localGameService = new LocalGameService();


    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        System.out.println( "Welcome to the BATTLESHIP3000 game!" );
        System.out.println( "Please enter your name:" );
        String playerName = scanner.nextLine();
        System.out.println( "Please enter your email address:" );
        String playerEmailAddress = scanner.nextLine();
        User myPlayer = userService.createUser( playerName, playerEmailAddress );
        System.out.printf( "Welcome, %s, your user ID is: %s%n", myPlayer.getName(), myPlayer.getUserId() );

        GameData game = gameService.joinUser( myPlayer.getUserId() );

        System.out.println( "You have joined a new game (Game Id: " + game.getId() + " )" );

        while (GameData.READY_FOR_SECOND_PLAYER.equals( gameService.status( game.getId() ).getStatus() )) {
            Thread.sleep( 2000 );
            System.out.print( "." );
        }

        System.out.printf( "%nAnother player has joined the game!%n%n" );

        System.out.println( "Game status: " + gameService.status( game.getId() ).getStatus() );

        String[][] myBoard = localGameService.initializeBoard();

        String[][] enemyBoard = localGameService.initializeBoard();

        List<Ship> ships = readShipFromConsole();


        localGameService.placeShipsOnBoard( myBoard, ships );

        String shipLocations = convertShipLocationsToString( ships );

        gameService.setUpGame( game.getId(), myPlayer.getUserId(), shipLocations );

        System.out.println( "Your ships are now deployed!" );
        localGameService.drawBoard( myBoard );

        while (!GameData.READY_TO_PLAY.equals( gameService.status( game.getId() ).getStatus() )) {
            Thread.sleep( 2000 );
            System.out.print( "." );
        }

        System.out.printf( "%nYou are now set to play! Await your turn!%n" );

        Set<Date> eventDates = new HashSet<>();
        Set<String> shotsHistory = new HashSet<>();

        boolean canPlay = true;
        boolean myTurn = true;

        do {

            Thread.sleep( 1000 );
            System.out.print( "." );

            List<Event> gameEvents = updateGameEvents( game.getId() );

            for (Event event : gameEvents) {
                if (hasEnemyMadeNewShots( event, myPlayer.getUserId(), eventDates )) {
                    Coordinate enemyShot = event.getCoordinate();
                    String enemyShotAsString = localGameService.parseCoordinateToString( enemyShot );
                    if (event.isHit()) {
                        enemyHasHitMyShipScenario( enemyShot, enemyShotAsString, myBoard, event );
                        eventDates = updateDates( eventDates, event );
                        if (isThereAWinner( game.getId() )) {
                            canPlay = false;
                            break;
                        } else {
                            while (eventDates.size() == gameEvents.size()) {
                                gameEvents = updateGameEvents( game.getId() );
                                Thread.sleep( 1000 );
                                System.out.print( "." );
                            }
                            continue;
                        }
                    } else {
                        enemyHasMissedScenario( enemyShot, enemyShotAsString, myBoard, event );
                        eventDates = updateDates( eventDates,event );
                        myTurn = true;
                        break;
                    }
                }
            }
            while (isItMyTurn( myPlayer.getUserId(), game.getId() ) && eventDates.size() == gameEvents.size() && myTurn) {
                Coordinate myShot = readAndValidateMyShot(shotsHistory);
                String myShotAsString = localGameService.parseCoordinateToString( myShot );
                gameService.turn( game.getId(), myPlayer.getUserId(), myShotAsString );
                gameEvents = updateGameEvents( game.getId() );

                for (Event event : gameEvents) {
                    if (hasMyPlayerMadeNewSHots (event, myPlayer.getUserId(), eventDates)) {
                        if (event.isHit()) {
                            myPlayerHasHitEnemyShipScenario (myShot, myShotAsString, enemyBoard );
                            eventDates = updateDates(eventDates, event);
                            if (isThereAWinner( game.getId() )) {
                                canPlay = false;
                                break;
                            } else {
                                continue;
                            }
                        } else {
                            myPlayerHasMissedScenario (myShotAsString, myShot, enemyBoard);
                            eventDates = updateDates( eventDates, event );
                            myTurn = false;
                            break;
                        }
                    }
                }
            }
        } while (canPlay);

        if (myPlayer.getUserId().equals( gameService.status( game.getId() ).getWinnerUserId() )) {
            System.out.println( "You have won the game!" );
        } else {
            System.out.println( "You have lost the game - better luck next time!" );
        }
        scanner.close();
    }

    private static void myPlayerHasMissedScenario(String myShotAsString, Coordinate myShot, String[][] enemyBoard) {
        System.out.printf( "%nYou have missed: %s%n", myShotAsString );
        enemyBoard[myShot.getRow()][myShot.getColumn()] = LocalGameService.MISSED;
        localGameService.drawBoard( enemyBoard );
    }

    private static void myPlayerHasHitEnemyShipScenario(Coordinate myShot, String myShotAsString, String[][] enemyBoard) {
        System.out.printf("%nYou have hit: %s%n", myShotAsString );
        enemyBoard[myShot.getRow()][myShot.getColumn()] = "X";
        localGameService.drawBoard( enemyBoard );
    }

    private static boolean hasMyPlayerMadeNewSHots(Event event, String userId, Set<Date> eventDates) {
        boolean result = false;
        if (event.getUserId().equals( userId ) && !eventDates.contains( event.getDate() )) {
            result = true;
        }
        return result;
    }

    private static void enemyHasMissedScenario(Coordinate enemyShot, String enemyShotAsString, String[][] myBoard, Event event) {
        System.out.printf( "%nYour enemy has missed: %s%n", enemyShotAsString );
        myBoard[enemyShot.getRow()][enemyShot.getColumn()] = localGameService.MISSED;
        localGameService.drawBoard( myBoard );
    }

    private static Set<Date> updateDates(Set<Date> eventDates, Event gameEvent) {
        eventDates.add( gameEvent.getDate() );
        return eventDates;
    }

    private static boolean isThereAWinner(String gameId) throws IOException, ParseException {
        boolean result = false;
        if (GameData.FINISHED.equalsIgnoreCase( gameService.status( gameId ).getStatus() )) {
            result = true;

        }
        return result;
    }

    private static void enemyHasHitMyShipScenario(Coordinate enemyShot, String enemyShotAsString, String[][] myBoard, Event gameEvent) {
        System.out.printf( "%nYour enemy has hit: %s%n", enemyShotAsString );
        myBoard[enemyShot.getRow()][enemyShot.getColumn()] = LocalGameService.HIT;
        localGameService.drawBoard( myBoard );

    }

    private static boolean hasEnemyMadeNewShots(Event event, String userId, Set<Date> dates) {
        boolean result = false;
        if (!event.getUserId().equals( userId ) && !dates.contains( event.getDate() )) {
            result = true;
        }
        return result;

    }

    private static List<Event> updateGameEvents(String gameId) throws IOException, ParseException {
        List<Event> result;
        result = gameService.status( gameId ).getEvents();
        return result;
    }

    private static String convertShipLocationsToString(List<Ship> ships) {
        StringBuilder sb = new StringBuilder();
        for (Ship ship : ships) {
            sb.append( localGameService.getColumnFromInt( ship.getHead().getColumn() ) ).append( ship.getHead().getRow() )
                    .append( "-" ).append( localGameService.getColumnFromInt( ship.getTail().getColumn() ) )
                    .append( ship.getTail().getRow() ).append( "!" );
        }
        sb = sb.deleteCharAt( sb.length() - 1 );
        return sb.toString();
    }


    private static Coordinate readAndValidateMyShot(Set<String> shotsHistory) {

        String myShot;

        while (true) {
            try {
                System.out.printf( "%nYour turn - please make your shot (Format K0):%n" );
                myShot = scanner.nextLine();
                if (localGameService.getCoordinateFromString( String.valueOf( myShot.charAt( 0 ) ) ) != -1
                        && Integer.parseInt( String.valueOf( myShot.charAt( 1 ) ) ) >= 0
                        && Integer.parseInt( String.valueOf( myShot.charAt( 1 ) ) ) <= 9
                        && !shotsHistory.contains( myShot )) {
                    break;
                }
                if (shotsHistory.contains( myShot )) {
                    System.out.println( "Shot has already been done!" );
                } else {
                    System.out.println( "You have entered an invalid value, please re-enter" );
                }
            } catch (InputMismatchException e) {
                System.out.println( "Enter a valid value:" );
                scanner.nextLine();
            }
        }
        shotsHistory.add( myShot );
        return localGameService.parseCoordinate( myShot );
    }

    private static boolean isItMyTurn(String userId, String gameId) throws IOException, ParseException {
        return userId.equalsIgnoreCase( gameService.status( gameId ).getNextTurnForUserId() );
    }

    private static List<Ship> readShipFromConsole() {

        List<Ship> result = new ArrayList<>();

        for (int i = 0; i < Ship.shipLengths.length; i++) {

            String shipAsString = readAndValidateShip( Ship.shipLengths[i] );

            result.add( localGameService.parseShip( shipAsString ) );
        }
        return result;
    }

    private static String readAndValidateShip(int shipLength) {
        String shipAsString;
        while (true) {
            try {
                System.out.println( "Enter ship coordinates (format K0-K1, size " + shipLength + "):" );
                shipAsString = scanner.nextLine();
                if (localGameService.getCoordinateFromString( String.valueOf( shipAsString.charAt( 0 ) ) ) != -1
                        && localGameService.getCoordinateFromString( String.valueOf( shipAsString.charAt( 3 ) ) ) != -1
                        && Integer.parseInt( String.valueOf( shipAsString.charAt( 1 ) ) ) >= 0
                        && Integer.parseInt( String.valueOf( shipAsString.charAt( 1 ) ) ) <= 9
                        && Integer.parseInt( String.valueOf( shipAsString.charAt( 4 ) ) ) <= 9
                        && Integer.parseInt( String.valueOf( shipAsString.charAt( 4 ) ) ) >= 0
                        && determineShipSize( shipAsString ) == shipLength
                        && IsShipVerticalOrHorizontal( shipAsString )) {
                    break;
                }
                System.out.println( "You have entered an invalid ship, please re-enter!" );
            } catch (InputMismatchException e) {
                System.out.println( "Enter a valid value:" );
                scanner.nextLine();
            }
        }
        return shipAsString;
    }

    private static boolean IsShipVerticalOrHorizontal(String shipAsString) {

        boolean result = false;
        if (String.valueOf( shipAsString.charAt( 0 ) ).equals( String.valueOf( shipAsString.charAt( 3 ) ) )) {
            result = true;
        }
        if (Integer.parseInt( String.valueOf( shipAsString.charAt( 1 ) ) ) == Integer.parseInt( String.valueOf( shipAsString.charAt( 4 ) ) )) {
            result = true;
        }
        return result;
    }

    private static int determineShipSize(String shipAsString) {
        int size;
        if (String.valueOf( shipAsString.charAt( 0 ) ).equals( String.valueOf( shipAsString.charAt( 3 ) ) )) {
            size = Integer.parseInt( String.valueOf( shipAsString.charAt( 4 ) ) ) - Integer.parseInt( String.valueOf( shipAsString.charAt( 1 ) ) ) + 1;
        } else {
            size = localGameService.getCoordinateFromString( String.valueOf( shipAsString.charAt( 3 ) ) ) - localGameService.getCoordinateFromString( String.valueOf( shipAsString.charAt( 0 ) ) ) + 1;
        }
        return size;
    }
}








