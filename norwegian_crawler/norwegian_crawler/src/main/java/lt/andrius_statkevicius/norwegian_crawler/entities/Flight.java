package lt.andrius_statkevicius.norwegian_crawler.entities;

import java.time.LocalDate;

public class Flight {

    private String departureTime;
    private String arrivalTime;
    private String departureAirport;
    private String arrivalAirport;
    private double lowfare;
    private double lowfare_plus;
    private double flex;
    private LocalDate departureDate;
    private double airportSurcharge;
    private double lowestPrice;


    public Flight(String departureTime, String arrivalTime, String departureAirport, String arrivalAirport, double lowfare, double lowfare_plus, double flex, LocalDate departureDate, double airportSurcharge) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.lowfare = lowfare;
        this.lowfare_plus = lowfare_plus;
        this.flex = flex;
        this.departureDate = departureDate;
        this.airportSurcharge = airportSurcharge;
        lowestPrice = Math.min( lowfare, Math.min(lowfare_plus, flex));
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public double getLowfare() {
        return lowfare;
    }

    public double getLowfare_plus() {
        return lowfare_plus;
    }

    public double getFlex() {
        return flex;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public double getAirportSurcharge() {
        return airportSurcharge;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(  );
        sb.append("Departure date :").append( departureDate ).
                append( "\nDeparture airport: " ).append( departureAirport ).
                append( "\nDeparture time: " ).append( departureTime ).
                append( "\nArrival airport: " ).append( arrivalAirport ).
                append("\nArrival time: "  ).append( arrivalTime ).
                append("\nLowest price: "  ).append( lowestPrice );

        return sb.toString();

    }
}
