package lt.andrius_statkevicius.sas_crawler.entities;

public class Flight {

    //outbound or inbound
    private String outboundOrInbound;
    private String connection;
    private double price;
    private String departure_time;
    private String arrival_time;
    private String class_type;
    private int flightId;
    private String departureAirport;
    private String arrivalAirport;
    private String date;


    public Flight(String outboundOrInbound, String connection, double price, String departure_time, String arrival_time, String class_type, int flightId) {
        this.outboundOrInbound = outboundOrInbound;
        this.connection = connection;
        this.price = price;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.class_type = class_type;
        this.flightId = flightId;

        if ("Outbound".equals( outboundOrInbound )) {
            this.departureAirport= "ARN";
            this.arrivalAirport = "LHR";
        } else {
            this.departureAirport = "LHR";
            this.arrivalAirport = "ARN";
        }

        if ("Outbound".equals( outboundOrInbound )) {
            this.date= "Mon 7 May";
        } else {
            this.date = "Sun 13 May";
        }

    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }


    public int getFlightId() {
        return flightId;
    }


    public String getClass_type() {
        return class_type;
    }

    public String getConnection() {
        return connection;
    }

    public double getPrice() {
        return price;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }


    public String getOutboundOrInbound() {
        return outboundOrInbound;
    }


    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(  );
        sb.append( "Outbound / Inbound: " ).append( outboundOrInbound )
                .append( "Flight ID: " ).append( flightId )
                .append( "\nDate: " ).append( date )
                .append( "\nDeparture time: " ).append( departure_time )
                .append( "\nArrival time: " ).append( arrival_time )
                .append( "\nConnection: " ).append( connection )
                .append( "\nPrice: " ).append( price );

        return sb.toString();
    }
}
