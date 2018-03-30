package lt.andrius_statkevicius.norwegian_crawler;

import lt.andrius_statkevicius.norwegian_crawler.entities.Flight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerService {

    public static final String LANGUAGE = "en";
    public static final String DEPARTURE_AIRPORT = "OSL";
    public static final String ARRIVAL_AIRPORT = "RIX";
    public static final String MONTH = "201805";
    public static final Boolean IS_TRANSIT_INCLUDED = false;
    public static final String CURRENCY_CODE = "EUR";


    public Document returnHTMLDocument (int dayOfTheMonth) throws IOException {
        StringBuilder url = new StringBuilder(  );
        url.append( WebService.NORWEGIAN_URL );
        url.append( LANGUAGE );
        url.append( "/ipc/availability/avaday?" );
        url.append( "D_City=" ).append( DEPARTURE_AIRPORT );
        url.append( "&" );
        url.append( "A_City=" ).append( ARRIVAL_AIRPORT );
        url.append( "&" );
        url.append( "TripType=1" );
        url.append( "&" );
        url.append( "D_Day=" );
        if ( dayOfTheMonth <10) {
            url.append( "0" + dayOfTheMonth);
        } else {
            url.append( dayOfTheMonth);
        }
        url.append( "&D_Month=" ).append( MONTH );
        url.append( "&D_SelectedDay=01" );
        url.append( "&R_Day=01" );
        url.append( "&R_Month=" ).append( MONTH );
        url.append( "&R_SelectedDay=01&dFlight=DY1072OSLRIX&dCabinFareType=2&IncludeTransit=").append( IS_TRANSIT_INCLUDED );
        url.append( "&AgreementCodeFK=-1&"  );
        url.append( "CurrencyCode=" ).append( CURRENCY_CODE );
        url.append( "&rnd=9384&processid=38884&mode=ab" );


        Document result = Jsoup.connect( url.toString() ).get();
        return result;

    }


    public List<String> getListOfDepartureTimes(Document doc) {
        List<String> result = new ArrayList<>(  );
        Elements departureTimes = doc.getElementsByClass( "depdest" );
        for (Element depTime: departureTimes) {
            if (depTime.text().contains( ":" )){
                result.add( depTime.text() );
            }
        }
        return result;
    }

    public List<String> getListOfArrivalTimes(Document doc) {
        List<String> result = new ArrayList<>(  );
        Elements arrivalTimes = doc.getElementsByClass( "arrdest" );
        for (Element arrTime: arrivalTimes) {
            if (arrTime.text().contains( ":" )){
                result.add( arrTime.text() );
            }
        }
        return result;
    }

    public List<Double> getListOfLowfaresPrices(Document doc) {
        List<Double> result = new ArrayList<>(  );
        Elements lowFares = doc.getElementsByClass( "fareselect standardlowfare" );
        for (Element lf: lowFares) {
            result.add(Double.parseDouble(lf.text()));
        }
        return result;
    }

    public List<Double> getListOfLowfaresPlusPrices(Document doc) {
        List<Double> result = new ArrayList<>(  );
        Elements lowFaresPlus = doc.getElementsByClass( "fareselect standardlowfareplus" );
        for (Element lf: lowFaresPlus) {
            result.add(Double.parseDouble(lf.text()));
        }
        return result;
    }

    public List<Double> getListOfFlexPrices(Document doc) {
        List<Double> result = new ArrayList<>(  );
        Elements lowFaresPlus = doc.getElementsByClass( "standardflex" );
        for (Element lf: lowFaresPlus) {
            if (!lf.text().isEmpty()) {
                result.add( Double.parseDouble( lf.text() ) );
            }
        }
        return result;
    }

    public double getAirporSurcharge(Document doc) {

        double result = 0;

        Elements taxes = doc.getElementsByClass( "togglebox" );


        for (Element tx: taxes) {
                if(tx.text().contains( "Airport surcharge" )) {
                result=Double.parseDouble(tx.text(  ).substring(tx.text().indexOf( "€" )+1, tx.text().length() ) );
            }
        }
        return result;
    }

    public Flight getCheapestFlight(List<Flight> flights) {
        Flight result = null;
        double max = Double.MAX_VALUE;
        for (Flight flt: flights) {
            if (flt.getLowestPrice()<max) {
                max = flt.getLowestPrice();
                result=flt;
            }
        }

        return result;
    }
}
