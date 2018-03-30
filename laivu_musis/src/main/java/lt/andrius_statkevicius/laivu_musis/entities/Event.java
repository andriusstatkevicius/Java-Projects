package lt.andrius_statkevicius.laivu_musis.entities;

import java.util.Date;

public class Event {


    private Coordinate coordinate;
    private String userId;
    private boolean isHit;
    private Date date;

    public Event(Coordinate coordinate, String userId, boolean isHit, Date date) {
        this.coordinate = coordinate;
        this.userId = userId;
        this.isHit = isHit;
        this.date = date;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }


    public String getUserId() {
        return userId;
    }


    public boolean isHit() {
        return isHit;
    }


    public Date getDate() {
        return date;
    }


}
