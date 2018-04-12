package lt.andrius_statkevicius.laivu_musis.entities;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    public static final String READY_FOR_SECOND_PLAYER = "READY_FOR_SECOND_PLAYER";
    public static final String READY_TO_PLAY = "READY_TO_PLAY";
    public static final String FINISHED = "FINISHED";

    private String gameId;
    private String nextTurnForUserId;
    private String status;
    private String winnerUserId;
    private List<Event> events;

    public GameData(String gameId, String nextTurnForUserId, String status, String winnerUserId, List<Event> events) {
        this.gameId = gameId;
        this.nextTurnForUserId = nextTurnForUserId;
        this.status = status;
        this.winnerUserId = winnerUserId;
        this.events = events;
    }

    public String getId() {
        return gameId;
    }


    public String getNextTurnForUserId() {
        return nextTurnForUserId;
    }


    public String getStatus() {
        return status;
    }


    public String getWinnerUserId() {
        return winnerUserId;
    }


    public List<Event> getEvents() {
        return events;
    }


}
