package lt.andrius_statkevicius.norwegian_crawler;

import lt.andrius_statkevicius.norwegian_crawler.entities.Flight;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        CrawlerService crawlerService = new CrawlerService();

        LocalDate start = LocalDate.of( 2018, Month.MAY, 1 );
        LocalDate end = LocalDate.of( 2018, Month.MAY, 31 );
        LocalDate departureDay;

        int daysInAMonth = end.getDayOfMonth() - start.getDayOfMonth() + 1;

        List<Flight> flights = new ArrayList<>();

        for (int i = 1; i <= daysInAMonth; i++) {

            //Since there are no flights on Saturdays
            departureDay = LocalDate.of( 2018, Month.MAY, i );
            if (departureDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
                continue;
            }
            Document doc = crawlerService.returnHTMLDocument( i );

            List<String> departureTimes = crawlerService.getListOfDepartureTimes( doc );

            List<String> arrivalTimes = crawlerService.getListOfArrivalTimes( doc );

            List<Double> lowFaresPrices = crawlerService.getListOfLowfaresPrices( doc );

            List<Double> lowFaresPlusPrices = crawlerService.getListOfLowfaresPlusPrices( doc );

            List<Double> flexPrices = crawlerService.getListOfFlexPrices( doc );

            for (int j = 0; j < departureTimes.size(); j++) {
                flights.add( new Flight( departureTimes.get( j ), arrivalTimes.get( j ), "Oslo-Gardermoen",
                        "Riga", lowFaresPrices.get( j ), lowFaresPlusPrices.get( j ), flexPrices.get( j ),
                        departureDay, crawlerService.getAirporSurcharge( doc ) ) );

            }

        }

        System.out.printf("CRAWLER RESULTS:%n");
        System.out.printf( "%-15s\t%-18s\t%-15s\t%-15s\t%-12s\t%-8s\t%-9S\t%-8s\t%-12s\t%-5s%n", "Departure date",
                "Departure airport","Departure time", "Arrival airport", "Arrival time",
                "LowFare", "LowFare+", "Flex", "Lowest price", "Taxes (Airport surcharge)");


        for (Flight flt: flights) {
            System.out.format("%n%-15s\t%-18s\t%-15s\t%-15s\t%-12s\t%-8.2f\t%-9.2f\t%-9.2f\t%-12.2f\t%-5.2f%n", flt.getDepartureDate(), flt.getDepartureAirport(),
                    flt.getDepartureTime(),flt.getArrivalAirport(),flt.getArrivalTime(),flt.getLowfare(),
                    flt.getLowfare_plus(),flt.getFlex(),flt.getLowestPrice(),flt.getAirportSurcharge());
        }

        Flight cheapestFlight = crawlerService.getCheapestFlight(flights);

        System.out.printf( "%nCheapest flight is:%n" );
        System.out.println(cheapestFlight);
    }
}

