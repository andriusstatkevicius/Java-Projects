package lt.andrius_statkevicius.sas_crawler;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.HtmlUnitContextFactory;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.sun.org.glassfish.gmbal.NameValue;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lt.andrius_statkevicius.sas_crawler.entities.Flight;
import net.sourceforge.htmlunit.corejs.javascript.Context;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Level;

public class App_main {

    //pakeiciau cookieMap is Hashmap:
    static Map<String, String> cookieMap = new HashMap();
    static Map<String, String> cookieMap2 = new HashMap<>();
    static HashMap<String, String> dataMap = new HashMap();
    static HashMap<String, String> dataMapForSearchResults = new HashMap();
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
    public static final String BOOK_FLYSAS = "https://book.flysas.com/pl/SASC/wds/Override.action?SO_SITE_EXT_PSPURL=https://classic.sas.dk/SASCredits/SASCreditsPaymentMaster.aspx&SO_SITE_TP_TPC_POST_EOT_WT=50000&SO_SITE_USE_ACK_URL_SERVICE=TRUE&WDS_URL_JSON_POINTS=ebwsprod.flysas.com%2FEAJI%2FEAJIService.aspx&SO_SITE_EBMS_API_SERVERURL=https%3A%2F%2F1aebwsprod.flysas.com%2FEBMSPointsInternal%2FEBMSPoints.asmx&WDS_SERVICING_FLOW_TE_SEATMAP=TRUE&WDS_SERVICING_FLOW_TE_XBAG=TRUE&WDS_SERVICING_FLOW_TE_MEAL=TRUE";
    public static final String HOME_PAGE = "http://www.flysas.com";
    public static final WebClient WEB_CLIENT = new WebClient( BrowserVersion.CHROME );
    static Scanner scanner = new Scanner (System.in);

    public static void main(String[] args) throws IOException {

        CrawlerService crawlerService = new CrawlerService();

        List<Flight> allFlights = new ArrayList<>(  );
        List<Flight> directFlightsOrViaOslo;

        WEB_CLIENT.getOptions().setCssEnabled( true );
        WEB_CLIENT.getOptions().setJavaScriptEnabled( true );
        WEB_CLIENT.getOptions().setThrowExceptionOnFailingStatusCode( false );
        WEB_CLIENT.getOptions().setThrowExceptionOnScriptError( false );
        WEB_CLIENT.getOptions().setTimeout( 0 );

        //Warnings caused by HTML Unit's JavaScriptEngine - turned off below:
        java.util.logging.Logger
                .getLogger( "com.gargoylesoftware.htmlunit" )
                .setLevel( Level.OFF );
        java.util.logging.Logger
                .getLogger( "org.apache.commons.httpclient" )
                .setLevel( Level.OFF );
        java.util.logging.Logger
                .getLogger( "com.gargoylesoftware.htmlunit.javascript.StrictErrorReporter" )
                .setLevel( Level.OFF );
        java.util.logging.Logger
                .getLogger( "com.gargoylesoftware.htmlunit.javascript.host.ActiveXObject" )
                .setLevel( Level.OFF );
        java.util.logging.Logger
                .getLogger( "com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDocument" )
                .setLevel( Level.OFF );
        java.util.logging.Logger
                .getLogger( "com.gargoylesoftware.htmlunit.html.HtmlScript" )
                .setLevel( Level.OFF );
        java.util.logging.Logger
                .getLogger( "com.gargoylesoftware.htmlunit.javascript.host.WindowProxy" )
                .setLevel( Level.OFF );
        java.util.logging.Logger
                .getLogger( "org.apache" )
                .setLevel( Level.OFF );

        java.util.logging.Logger.getLogger( "com.gargoylesoftware.htmlunit" ).setLevel( java.util.logging.Level.OFF );
        java.util.logging.Logger.getLogger( "org.apache.http" ).setLevel( java.util.logging.Level.OFF );


        /*was trying to get the cookies from the home page in order to pass it to
        referrer page and get ENC / SIP_INTERNAL variables
         */

//       Connection.Response res = Jsoup.connect( HOME_PAGE ).userAgent( USER_AGENT )
//                .execute();
//
//        cookieMap.putAll( res.cookies() );


//        dataMap = crawlerService.getDataMapForReferrerPage();


        /*Was trying to get ENC and SIP variables with HtmlUnit
        (essential variables in order to get search results - they are changing dynamically
        and are received in a referrer site of
        https://www.flysas.com/en/ - later on they are visible in the book.flysas.com body (i.e. search results URL)
         */

        String enc = "";
        String sip_internal = "";

        WebRequest requestToReferrer = new WebRequest( new URL( "https://www.flysas.com/en/" ), HttpMethod.POST );
        requestToReferrer.setRequestParameters( new ArrayList<NameValuePair>() );

        String[] cookieMapNames = cookieMap.keySet().toArray( new String[cookieMap.size()] );
        String[] cookieMapValues = cookieMap.values().toArray( new String[cookieMap.size()] );

        String[] dataMapKeys = dataMap.keySet().toArray( new String[dataMap.size()] );
        String[] dataMapValues = dataMap.values().toArray( new String[dataMap.size()] );

        for (int i = 0; i < dataMap.size(); i++) {
            requestToReferrer.getRequestParameters().add( new NameValuePair( dataMapKeys[i], dataMapValues[i] ) );
        }

        String cookieHeaderForReferrerPage = crawlerService.buildCookiesHeader( cookieMapNames, cookieMapValues );


        requestToReferrer.setAdditionalHeader( "Cookie", cookieHeaderForReferrerPage.toString() );


//        HtmlPage referrerPage = WEB_CLIENT.getPage( requestToReferrer );
        /* I was trying to extract ENC and SIP_INTERNAL values from a referrer page, however unsuccessfully.
        I think the issue is that it's a referrer page and I get status code 404 that a page wasn't extracted sucesfully.
        I tried to print it out as well to search if
        ENC and SIP_INTERNAL were captured, but they were not. With JSOUP it's not possible to get these
         input fields as JSOUP does not support JavaScript and these variables are generated by JavaScript.
         */

//        enc = referrerPage.getElementById( "ENCT" ).getAttribute( "value" );
//        sip_internal = referrerPage.getElementById( "SIP_INTERNAL" ).getAttribute( "value" );

        //hard-coding datamap manually:
        //TODO - Data variable is changing dynamically everyday - make it dynamic
        dataMapForSearchResults = crawlerService.getDataMap2();


        //cookies are hardcoded manually (from book.flysas.com header)
        Map<String, String> staticCookies = crawlerService.getCookies();


//        HTMLUNIT method (with JavaScript engine embedded)
        String[] cookieMap2Names = staticCookies.keySet().toArray( new String[staticCookies.size()] );
        String[] cookieMap2Values = staticCookies.values().toArray( new String[staticCookies.size()] );

        String[] dataMap2Keys = dataMapForSearchResults.keySet().toArray( new String[dataMapForSearchResults.size()] );
        String[] dataMap2Values = dataMapForSearchResults.values().toArray( new String[dataMapForSearchResults.size()] );

        WebRequest webRequestForSearchResults = new WebRequest( new URL( BOOK_FLYSAS ), HttpMethod.POST );

        webRequestForSearchResults.setRequestParameters( new ArrayList<NameValuePair>() );

        for (int i = 0; i < dataMapForSearchResults.size(); i++) {
            webRequestForSearchResults.getRequestParameters().add( new NameValuePair( dataMap2Keys[i], dataMap2Values[i] ) );
        }

        String cookieHeaderForSearchResults = crawlerService.buildCookiesHeader( cookieMap2Names, cookieMap2Values );

        webRequestForSearchResults.setAdditionalHeader( "Cookie", cookieHeaderForSearchResults );

        HtmlPage searchResults = WEB_CLIENT.getPage( webRequestForSearchResults );

        Document results = Jsoup.parse( searchResults.asXml() );

          /*The following line should print the search results, but since I did not
          manage to take variables of ENC and SIP_INTERNAL dynamically, results are only produced
           if newly generated variables ENC/SIP are present
           */

//        System.out.println( results );

        //Source code of the search results downloaded manually:
        File searchResultsFile = new File( "C:\\Users\\Andrius\\IdeaProjects\\sas_crawler\\book.flysas.com - select flights.html" );

        Document document = Jsoup.parse( searchResultsFile, "UTF-8" );

        Element panelOfOutBoundFlights = document.getElementById( "panel_0" );

        List<Double> outBoundFlightsPrices = crawlerService.getPrices( panelOfOutBoundFlights );

        List<String> outBoundClassTypes = crawlerService.getClassTypes (panelOfOutBoundFlights);

        List<String> outBoundConnections = crawlerService.getConnections(panelOfOutBoundFlights, document);

        List<String> outBoundTimes = crawlerService.getDepartureAndArrivalTimes (panelOfOutBoundFlights);

        List<String> outBoundDepartureTimes = crawlerService.getDepartureTimes (outBoundTimes);
        List<String> outBoundArrivalTimes = crawlerService.getArrivalTimes (outBoundTimes);

        for (int i = 0; i<outBoundFlightsPrices.size(); i++) {
            allFlights.add( new Flight( "Outbound", outBoundConnections.get( i ), outBoundFlightsPrices.get(i),
                    outBoundDepartureTimes.get( i ), outBoundArrivalTimes.get( i ), outBoundClassTypes.get( i ), i) );
        }

        int counter = allFlights.size();

        Element panelOfReturningFlights = document.getElementById( "panel_1" );

        List<Double> inBoundFlightsPrices = crawlerService.getPrices( panelOfReturningFlights );

        List<String> inBoundClassTypes = crawlerService.getClassTypes (panelOfReturningFlights);

        List<String> inBoundConnections = crawlerService.getConnections(panelOfReturningFlights, document);

        List<String> inBoundTimes = crawlerService.getDepartureAndArrivalTimes (panelOfReturningFlights);

        List<String> inBoundDepartureTimes = crawlerService.getDepartureTimes (inBoundTimes);
        List<String> inBoundArrivalTimes = crawlerService.getArrivalTimes (inBoundTimes);


        for (int i = 0; i < inBoundFlightsPrices.size(); i++) {
            allFlights.add( new Flight( "Return", inBoundConnections.get( i ), inBoundFlightsPrices.get(i),
                    inBoundDepartureTimes.get( i ), inBoundArrivalTimes.get( i ), inBoundClassTypes.get( i ), counter+i)  );
        }

        directFlightsOrViaOslo = crawlerService.filterFlights(allFlights);

        System.out.printf("RESULTS:%n%n");
        System.out.printf( "%-10s\t%-17s\t%-12s\t%-19s\t%-15s\t%-10s\t%-15s\t%-12s\t%-5s%n%n", "Flight ID", "Outbound / Return",
                "Date", "Departure airport", "Arrival airport", "Connection", "Departure time",
                "Arrival Time", "Price");


        for (Flight fl: directFlightsOrViaOslo) {
            System.out.format("%-10s\t%-17s\t%-12s\t%-19s\t%-15s\t%-10s\t%-15s\t%-12s\t%-5.2f%n",
                    fl.getFlightId(), fl.getOutboundOrInbound(), fl.getDate(), fl.getDepartureAirport(), fl.getArrivalAirport(),
                    fl.getConnection(), fl.getDeparture_time(), fl.getArrival_time(), fl.getPrice());
        }

        Flight cheapestOutboundFlight = crawlerService.getcheapestOutboundFlight(directFlightsOrViaOslo);
        Flight cheapestReturnFlight = crawlerService.getcheapestReturnFlight(directFlightsOrViaOslo  );

        System.out.printf("%n%nCHEAPEST OUTBOUND FLIGHT:%n");
        System.out.println(cheapestOutboundFlight);
        System.out.printf("%n%nCHEAPEST RETURN FLIGHT:%n");
        System.out.println(cheapestReturnFlight);

        String flightCombination;

        System.out.println("\nEnter IDs for selected flight combination separated by comma (flight Id,flight Id) for taxes breakdown:");
        flightCombination = scanner.nextLine();
        String taxes = crawlerService.returnTaxBreakDown(flightCombination, directFlightsOrViaOslo);
        System.out.println(taxes);

    }
}



////        JSOUP method (without embedded JavaScript engine - JavaScript results are not shown)//
//        Connection.Response searchResultsResponse = Jsoup.connect( BOOK_FLYSAS ).userAgent(USER_AGENT).cookies( cookieMap2 )
//                .timeout( 10000 )
//                .data( dataMap2 )
//                .method( Connection.Method.POST)
//                .referrer("https://www.flysas.com/en/" )
//                .header("Accept", "text/html" )
//                .header( "Accept-Encoding", "gzip, deflate, br" )
//                .header( "Accept-Encoding", "gzip, deflate, br" )
//                .header( "Accept-Language",  "en-GB")
//                .execute();
//
//        Document doc3 = searchResultsResponse.parse();
//
//        System.out.println(doc3);

//        separating the panel of outbound flight from the search results
//        Element panelOfOutboundFlights = doc3.getElementById( "panel_0" );
//
//
//        System.out.println(panelOfOutboundFlights);





