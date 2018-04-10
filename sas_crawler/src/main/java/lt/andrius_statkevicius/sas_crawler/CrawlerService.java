package lt.andrius_statkevicius.sas_crawler;

import lt.andrius_statkevicius.sas_crawler.entities.Flight;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DecimalFormat;
import java.util.*;

public class CrawlerService {

    public HashMap<String, String> getDataMapForReferrerPage() {

        HashMap<String, String> result = new HashMap<String, String>();

        result.put( "__EVENTTARGET", "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$Searchbtn$ButtonLink" );
        result.put( "__EVENTARGUMENT", "" );
        result.put( "ctl00$FullRegion$TopRegion$_siteHeader$hdnProfilingConsent", "" );
        result.put( "ctl00$FullRegion$TopRegion$_siteHeader$hdnTermsConsent", "" );
        result.put( "ctl00$FullRegion$TopRegion$_siteHeader$_ssoLogin$MainFormBorderPanel$uid", "" );
        result.put( "ctl00$FullRegion$TopRegion$_siteHeader$_ssoLogin$MainFormBorderPanel$pwd", "" );
        result.put( "ctl00$FullRegion$TopRegion$_siteHeader$_ssoLogin$MainFormBorderPanel$hdnShowModal", "" );
        result.put( "ctl00$FullRegion$TopRegion$_siteHeader$_ssoLogin$MainFormBorderPanel$hdnIsEb0", "" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$ceptravelTypeSelector$TripTypeSelector", "roundtrip" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$hiddenIntercont", "False" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$hiddenDomestic", "SE,GB" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$hiddenFareType", "" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtFrom", "Stockholm, Sweden - Arlanda (ARN)" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$hiddenFrom", "ARN" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtTo", "London, United Kingdom - Heathrow (LHR)" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$hiddenTo", "LHR" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtFromTOJ", "Type a country, city or airport" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$hiddenFromTOJ", "" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepCalendar$hiddenOutbound", "2018-05-07" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepCalendar$hiddenReturn", "2018-05-13" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepCalendar$hdnSelectedOutboundMonth", "" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepCalendar$hdnSelectedReturnMonth", "" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepCalendar$hiddenReturnCalVisible", "" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepCalendar$hiddenStoreCalDates", "Tue Apr 03 2018 00:00:00 GMT+0300 (FLE Daylight Time),Tue Apr 03 2018 00:00:00 GMT+0300 (FLE Daylight Time),Thu Mar 28 2019 00:00:00 GMT+0200 (FLE Standard Time)" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepCalendar$selectOutbound", "2018-05-01" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepCalendar$selectReturn", "2018-05-01" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$FlexDateSelector", "Show selected dates" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepPassengerTypes$passengerTypeAdult", "1" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepPassengerTypes$passengerTypeChild211", "0" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$cepPassengerTypes$passengerTypeInfant", "0" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$hdnsetDefaultValue", "true" );
        result.put( "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$hdncalendarDropdown", "true" );
        result.put( "__PREVIOUSPAGE", "3aoIK5urOF6qLmjEUVWoe7Zlok_H7Ef8UkS2oCFR_Ccg24aQSIRhidbF3PGeuRmIFTuiGxx8ealPNKfgqBWh77mCC2k1" );
        result.put( "__VIEWSTATE", "/wEPDwUJNjIyMTczODM5D2QWAmYPZBYCAgEQZGQWAgIBD2QWAgICD2QWAgIDD2QWAgICD2QWBAIBD2QWAgIBD2QWAmYPZBYEZg8WAh4FY2xhc3MFCWFjdGl2ZUNFUBYWZg8VAQtCb29rIGEgdHJpcGQCBQ8PZBYCHgtDRVBQYWdlRGF0YQUXRVBpU2VydmVyLkNvcmUuUGFnZURhdGFkAgsPD2QWAh4TTm90aWZpY2F0aW9uQ29udHJvbAUuQVNQLnNhc190ZW1wbGF0ZXNfdXRpbF9ub3RpZmljYXRpb25kaWFsb2dfYXNjeBYIAgUPZBYCAgEPEA8WAh4LXyFEYXRhQm91bmRnZGQWAGQCCQ8PFgQeC19pc0VkaXRhYmxlaB4JX3BhZ2VMaW5rKClsRVBpU2VydmVyLkNvcmUuUGFnZVJlZmVyZW5jZSwgRVBpU2VydmVyLCBWZXJzaW9uPTYuMS4zNzkuMCwgQ3VsdHVyZT1uZXV0cmFsLCBQdWJsaWNLZXlUb2tlbj04ZmU4M2RlYTczOGI0NWI3BjIxNTQ1NGRkAgoPZBYCZg9kFgQCAQ8WAh4Fc3R5bGUFDmRpc3BsYXk6IGJsb2NrFgICAQ8WAh4JaW5uZXJodG1sBT88c3R5bGU+DQouYWN0aXZlQ0VQe3otaW5kZXg6MTAwOyBwb3NpdGlvbjpyZWxhdGl2ZX0NCjwvc3R5bGU+DQpkAgMPEA8WAh8DZ2RkFgBkAgsPFQI6aHR0cDovL3d3dy5mbHlzYXMuY29tL2RlZmF1bHQuYXNweD9pZD02JmFtcDtlcHNsYW5ndWFnZT1lbgVSZXNldGQCDQ8PFgIeB1Zpc2libGVnFgIfAgUuQVNQLnNhc190ZW1wbGF0ZXNfdXRpbF9ub3RpZmljYXRpb25kaWFsb2dfYXNjeBYMAgMPZBYCZg8WAh8IZxYCAgEPZBYCZg9kFgJmD2QWBgIBD2QWAmYPFgIeBFRleHQFtgE8bGFiZWwgZm9yPSJjdGwwMF9GdWxsUmVnaW9uX01haW5SZWdpb25fQ29udGVudFJlZ2lvbl9Db250ZW50RnVsbFJlZ2lvbl9Db250ZW50TGVmdFJlZ2lvbl9DRVBHcm91cDFfQ0VQQWN0aXZlX2NlcE5EUFJldkJvb2tpbmdBcmVhX2NlcHRyYXZlbFR5cGVTZWxlY3Rvcl9yb3VuZHRyaXAiPlJvdW5kIHRyaXA8L2xhYmVsPmQCAw9kFgJmDxYCHwkFsAE8bGFiZWwgZm9yPSJjdGwwMF9GdWxsUmVnaW9uX01haW5SZWdpb25fQ29udGVudFJlZ2lvbl9Db250ZW50RnVsbFJlZ2lvbl9Db250ZW50TGVmdFJlZ2lvbl9DRVBHcm91cDFfQ0VQQWN0aXZlX2NlcE5EUFJldkJvb2tpbmdBcmVhX2NlcHRyYXZlbFR5cGVTZWxlY3Rvcl9vbmV3YXkiPk9uZSB3YXk8L2xhYmVsPmQCBQ9kFgJmDxYCHwkFwgE8bGFiZWwgZm9yPSJjdGwwMF9GdWxsUmVnaW9uX01haW5SZWdpb25fQ29udGVudFJlZ2lvbl9Db250ZW50RnVsbFJlZ2lvbl9Db250ZW50TGVmdFJlZ2lvbl9DRVBHcm91cDFfQ0VQQWN0aXZlX2NlcE5EUFJldkJvb2tpbmdBcmVhX2NlcHRyYXZlbFR5cGVTZWxlY3Rvcl9vcGVuamF3Ij5SZXR1cm4gZnJvbSBhbm90aGVyIGNpdHk8L2xhYmVsPmQCBg8WAh8IZxYCAgEPEA8WAh8DZ2QPFgJmAgEWAhAFF1Nob3cgYSBtb250aGx5IGNhbGVuZGFyBRdTaG93IGEgbW9udGhseSBjYWxlbmRhcmcQBRNTaG93IHNlbGVjdGVkIGRhdGVzBRNTaG93IHNlbGVjdGVkIGRhdGVzZ2RkAgcPFgIfCGhkAggPZBYCZg9kFgYCAQ8QDxYCHwNnZGRkZAICDxAPFgIfA2dkZGRkAgMPEA8WAh8DZ2RkZGQCCQ9kFgJmD2QWBAIBDxYCHwYFDmRpc3BsYXk6IGJsb2NrFgICAQ8WAh8HBT88c3R5bGU+DQouYWN0aXZlQ0VQe3otaW5kZXg6MTAwOyBwb3NpdGlvbjpyZWxhdGl2ZX0NCjwvc3R5bGU+DQpkAgMPEA8WAh8DZ2RkZGQCCg8VAiwvdGVtcGxhdGVzL0NFUC5hc3B4P2lkPTIxNTQ1NCZlcHNsYW5ndWFnZT1lbgVSZXNldGQCDw8PZBYCHwIFLkFTUC5zYXNfdGVtcGxhdGVzX3V0aWxfbm90aWZpY2F0aW9uZGlhbG9nX2FzY3gWCgIED2QWBGYPZBYEZg8VARtUcmF2ZWwgUGFzcyBudW1iZXIgaW4gdXNlIDpkAgMPZBYCZg8VARlDaGFuZ2UgVHJhdmVsIFBhc3MgbnVtYmVyZAICD2QWBAIBDxAPFgIfCQUYQm9vayBhcyBhIHRyYXZlbCBtYW5hZ2VyZGRkZAIDDxAPFgIfCQURT25seSBtZSB0cmF2ZWxpbmdkZGRkAggPZBYKZg8VAQZTZWxlY3RkAgMPEBYCHwNnZBQrAQBkAgYPEBYCHwNnZBQrAQBkAgoPEBYCHwNnZBQrAQBkAg0PEBYCHwNnZBQrAQBkAg4PZBYCAgIPFQwJVHJhdmVsZXJzEFNlbGVjdCB0cmF2ZWxlcnMFQ2xvc2UQU2VsZWN0IHRyYXZlbGVycw1pcyB0cmF2ZWxsaW5nA1llcwJObxNOdW1iZXIgb2YgdHJhdmVsZXJzCFRyYXZlbGVyDk5vbmUgdG8gc2VsZWN0BlNlbGVjdAJPS2QCEA9kFgJmD2QWBAIBDxYCHwYFDmRpc3BsYXk6IGJsb2NrFgICAQ8WAh8HBT88c3R5bGU+DQouYWN0aXZlQ0VQe3otaW5kZXg6MTAwOyBwb3NpdGlvbjpyZWxhdGl2ZX0NCjwvc3R5bGU+DQpkAgMPEA8WAh8DZ2RkFgBkAhEPFQIsL3RlbXBsYXRlcy9DRVAuYXNweD9pZD0yMTU0NTQmZXBzbGFuZ3VhZ2U9ZW4FUmVzZXRkAhEPD2QWAh8CBS5BU1Auc2FzX3RlbXBsYXRlc191dGlsX25vdGlmaWNhdGlvbmRpYWxvZ19hc2N4FgYCBg9kFgRmDxUBG1RyYXZlbCBQYXNzIG51bWJlciBpbiB1c2UgOmQCAw9kFgJmDxUBGUNoYW5nZSBUcmF2ZWwgUGFzcyBudW1iZXJkAg4PZBYCZg9kFgQCAQ8WAh8GBQ5kaXNwbGF5OiBibG9jaxYCAgEPFgIfBwU/PHN0eWxlPg0KLmFjdGl2ZUNFUHt6LWluZGV4OjEwMDsgcG9zaXRpb246cmVsYXRpdmV9DQo8L3N0eWxlPg0KZAIDDxAPFgIfA2dkZBYAZAIPDxUCLC90ZW1wbGF0ZXMvQ0VQLmFzcHg/aWQ9MjE1NDU0JmVwc2xhbmd1YWdlPWVuBVJlc2V0ZAITDw9kFgIfAgUuQVNQLnNhc190ZW1wbGF0ZXNfdXRpbF9ub3RpZmljYXRpb25kaWFsb2dfYXNjeBYEAgwPZBYCZg9kFgICAw8QDxYCHwNnZGQWAGQCDQ9kFgJmDxUBDU1vZGlmeSBzZWFyY2hkAhUPD2QWAh8CBS5BU1Auc2FzX3RlbXBsYXRlc191dGlsX25vdGlmaWNhdGlvbmRpYWxvZ19hc2N4FgQCCw9kFgJmD2QWAgIDDxAPFgIfA2dkZBYAZAIMD2QWAmYPFQENTW9kaWZ5IHNlYXJjaGQCGQ9kFgJmD2QWBGYPFgIfCGgWAmYPFQEAZAIBDxUBAGQCHw9kFgRmDxUCdGN0bDAwX0Z1bGxSZWdpb25fTWFpblJlZ2lvbl9Db250ZW50UmVnaW9uX0NvbnRlbnRGdWxsUmVnaW9uX0NvbnRlbnRMZWZ0UmVnaW9uX0NFUEdyb3VwMV9DRVBBY3RpdmVfQ01QQ29kZV9idG5Cb29rTm93dGN0bDAwX0Z1bGxSZWdpb25fTWFpblJlZ2lvbl9Db250ZW50UmVnaW9uX0NvbnRlbnRGdWxsUmVnaW9u" );
        result.put( "__VIEWSTATEGENERATOR", "CA0B0334" );

        return result;
    }

    public HashMap<String, String> getDataMap2() {

        HashMap<String, String> result = new HashMap<>();

        result.put( "__EVENTTARGET", "btnSubmitAmadeus" );
        result.put( "__EVENTARGUMENT", ""
        );
        result.put( "LANGUAGE", "GB" );
        result.put( "SITE", "SKBKSKBK" );
        result.put( "EMBEDDED_TRANSACTION", "FlexPricerAvailability"
        );
        result.put( "SIP_INTERNAL", "44454641554C545F4E44505F4345505F49443D32313230383826504152414D455445525F434845434B53554D3D3633264345505F49443D3231353435342652454449524543545F55524C3D25326664656661756C742E617370782533666964253364362532366570736C616E6775616765253364656E265354415254504147455F49443D36264D41524B45543D3432265744535F50524F4D4F434F44455F524547494F4E5F454E41424C45523D446F6D65737469632D74727565253361496E737465725363616E642D74727565253361496E746572636F6E742D747275652533614575726F70652D74727565265245565F5744535F4F42464545533D253363253366786D6C2B76657273696F6E25336427312E30272B656E636F64696E672533642769736F2D383835392D3127253366253365253363534F5F474C253365253363474C4F42414C5F4C4953542533652533634E414D45253365534954455F4C4953545F4F425F4645455F434F44455F544F5F4558454D50542533632532664E414D452533652533634C4953545F454C454D454E54253365253363434F4445253365543031253363253266434F44452533652533634C4953545F56414C55452533655430312533632532664C4953545F56414C55452533652533632532664C4953545F454C454D454E542533652533634C4953545F454C454D454E54253365253363434F4445253365543032253363253266434F44452533652533634C4953545F56414C55452533655430322533632532664C4953545F56414C55452533652533632532664C4953545F454C454D454E54253365253363253266474C4F42414C5F4C495354253365253363253266534F5F474C253365" );
        result.put( "WDS_FLOW", "REVENUE"
        );
        result.put( "WDS_FACADE_CALLBACK", "https://www.flysas.com/AmadeusFacade/default.aspx?epslanguage=en"
        );
        result.put( "SO_SITE_ATC_ALLOW_LSA_INDIC", "TRUE"
        );
        result.put( "SO_SITE_ADVANCED_CATEGORIES", "TRUE"
        );
        result.put( "SO_SITE_TK_OFFICE_ID", "LUXSK08RV"
        );
        result.put( "SO_SITE_QUEUE_OFFICE_ID", "LUXSK08RV"
        );
        result.put( "SO_SITE_CSSR_TAXES", "FALSE"
        );
        result.put( "SO_SITE_OFFICE_ID", "LUXSK08RV"
        );
        result.put( "SO_SITE_ETKT_Q_AND_CAT", "32C0"
        );
        result.put( "SO_SITE_FP_CAL_DISP_NA_DATE", "TRUE"
        );
        result.put( "SO_SITE_ETKT_Q_OFFICE_ID", "LUXSK08RV"
        );
        result.put( "SO_GL", "<SO_GL><GLOBAL_LIST><NAME>SITE_QUEUE_DEFINITION_LIST</NAME><LIST_ELEMENT><CODE>0</CODE><LIST_VALUE>SRV</LIST_VALUE><LIST_VALUE>LUXSK08RV</LIST_VALUE><LIST_VALUE>34</LIST_VALUE><LIST_VALUE>0</LIST_VALUE></LIST_ELEMENT><LIST_ELEMENT><CODE>1</CODE><LIST_VALUE>CAN</LIST_VALUE><LIST_VALUE>LUXSK08RV</LIST_VALUE><LIST_VALUE>31</LIST_VALUE><LIST_VALUE>0</LIST_VALUE></LIST_ELEMENT><LIST_ELEMENT><CODE>2</CODE><LIST_VALUE>RIR</LIST_VALUE><LIST_VALUE>LUXSK08RV</LIST_VALUE><LIST_VALUE>30</LIST_VALUE><LIST_VALUE>0</LIST_VALUE></LIST_ELEMENT><LIST_ELEMENT><CODE>3</CODE><LIST_VALUE>REI</LIST_VALUE><LIST_VALUE>LUXSK08RV</LIST_VALUE><LIST_VALUE>30</LIST_VALUE><LIST_VALUE>0</LIST_VALUE></LIST_ELEMENT><LIST_ELEMENT><CODE>4</CODE><LIST_VALUE>AWA</LIST_VALUE><LIST_VALUE>LUXSK08RV</LIST_VALUE><LIST_VALUE>8</LIST_VALUE><LIST_VALUE>1</LIST_VALUE></LIST_ELEMENT><LIST_ELEMENT><CODE>6</CODE><LIST_VALUE>RIP</LIST_VALUE><LIST_VALUE>LUXSK08RV</LIST_VALUE><LIST_VALUE>30</LIST_VALUE><LIST_VALUE>0</LIST_VALUE></LIST_ELEMENT></GLOBAL_LIST><GLOBAL_LIST><NAME>SITE_LIST_OB_FEE_CODE_TO_EXEMPT</NAME><LIST_ELEMENT><CODE>T01</CODE><LIST_VALUE>T01</LIST_VALUE></LIST_ELEMENT><LIST_ELEMENT><CODE>T02</CODE><LIST_VALUE>T02</LIST_VALUE></LIST_ELEMENT></GLOBAL_LIST></SO_GL>"
        );
        result.put( "SO_SITE_FD_SOLDOUT_FLIGHT", "TRUE"
        );
        result.put( "SO_SITE_QUEUE_CATEGORY", "8C50"
        );
        result.put( "SO_SITE_QUEUE_CATEGORY", "8C50"
        );
        result.put( "SO_SITE_ALLOW_LSA_INDICATOR", "TRUE"
        );
        result.put( "WDS_SERVICING_FLOW_TE_MEAL", "TRUE"
        );
        result.put( "WDS_AVD_SEL_FLIGHTS", "FALSE"
        );
        result.put( "WDS_CAL_RANGE", "15"
        );
        result.put( "WDS_SERVICING_FLOW_TE_FBAG", "TRUE"
        );
        result.put( "WDS_SHOW_INVINFO", "FALSE"
        );
        result.put( "WDS_BOOKING_FLOW_TE_MEAL", "TRUE"
        );
        result.put( "WDS_PROMOCODE", "TRUE"
        );
        result.put( "WDS_ACTIVATE_APP_FOR_CC_MOP", "TRUE"
        );
        result.put( "PRICING_TYPE", "C"
        );
        result.put( "WDS_SHOW_TAXES", "TRUE"
        );
        result.put( "B_LOCATION_1", "ARN"
        );
        result.put( "WDS_FO_IATA", "47490822"
        );
        result.put( "WDS_SHOW_ADDCAL", "TRUE"
        );
        result.put( "WDS_USE_FQN", "TRUE"
        );
        result.put( "WDS_ACTIVATE_APP_FOR_ALL_MOP", "FALSE"
        );
        result.put( "COMMERCIAL_FARE_FAMILY_1", "SKSTDA"
        );
        result.put( "WDS_CHECKIN_NOTIF", "FALSE"
        );
        result.put( "TRIP_TYPE", "R"
        );
        result.put( "TRIP_TYPE", "R"
        );
        result.put( "WDS_HELPCONTACTURL", "http://classic.sas.se/en/misc/Arkiv/contact-sia-/"
        );
        result.put( "WDS_SAS_CREDITS", "TRUE"
        );
        result.put( "WDS_ANCILLARIES", "FALSE"
        );
        result.put( "WDS_BOOKING_FLOW_TE_FBAG", "TRUE"
        );
        result.put( "WDS_CC_LIST", "AX-SAS/ERETAIL_LU-true:CA-SAS/ERETAIL_LU-true:VI-SAS/ERETAIL_LU-true:DC-SAS/ERETAIL_LU-false:DS-SAS/ERETAIL_LU-true:TP-SAS/ERETAIL_LU-false"
        );
        result.put( "WDS_SASCPCTRANGE", "43137"
        );
        result.put( "WDS_SHOW_AB", "TRUE"
        );
        result.put( "WDS_FOID_EXCL_LIST", "DK"
        );
        result.put( "DATE_RANGE_VALUE_1", "1"
        );
        result.put( "WDS_SERVICING_FLOW_TE_SEATMAP", "TRUE"
        );
        result.put( "DATE_RANGE_VALUE_2", "1"
        );
        result.put( "WDS_BOOKING_FLOW_TE_XBAG", "TRUE"
        );
        result.put( "WDS_INTBANK_LIST", "PLU-SAScLU;paypal"
        );
        result.put( "WDS_POINTS_EARNED", "SHOW"
        );
        result.put( "WDS_ORIGIN_SITE", "LU" );
        result.put( "WDS_SHOW_CMP_CODE", "TRUE"
        );
        result.put( "TRAVELLER_TYPE_1", "ADT"
        );
        result.put( "WDS_NEWSLETTER_FCO", "FALSE"
        );
        result.put( "B_LOCATION_2", "LHR"
        );
        result.put( "WDS_BOOKING_FLOW_TE_SEATMAP", "TRUE"
        );
        result.put( "WDS_TIME_OPTION", "TRUE" );
        result.put( "WDS_FRAS", "TRUE"
        );
        result.put( "DISPLAY_TYPE", "2"
        );
        result.put( "WDS_MOBILE_NEW_DESIGN", "TRUE"
        );
        result.put( "WDS_SERVICING_FLOW_TE_XBAG", "TRUE"
        );
        result.put( "WDS_SHOW_MINISEARCH", "LINK"
        );
        result.put( "B_DATE_1", "201805070000"
        );
        result.put( "B_DATE_2", "201805130000"
        );
        result.put( "E_LOCATION_2", "ARN"
        );
        result.put( "E_LOCATION_1", "LHR"
        );
        result.put( "WDS_EBMS_CAMPAIGN", "TRUE"
        );
        result.put( "WDS_EBMS_CAMPAIGN", "TRUE"
        );
        result.put( "DATE_RANGE_QUALIFIER_2", "C"
        );
        result.put( "DATE_RANGE_QUALIFIER_1", "C"
        );
        result.put( "ENCT", "1"
        );
        result.put( "ENC", "C16D544BAAE6044E0F05D22462F93ABF2016D3D16835DC1BD966EE476EEA95BE347E1B9DDE09436FAA12FE02EE1BB2B6723CDBF97A27DEDCB97EB08BDF6A478B2A0111FCE71421C05DBC9606E9548566821D54662375D7AC81DDA437353974A479F97CE986BFD45AD828EE94111443CD79F97CE986BFD45AD828EE94111443CD" );
        result.put( "__PREVIOUSPAGE", "EOuVgEVGcPaooWlcQzY7uwfysikykaVpb-H5wZ3xp_fcVkbM_4Y3Yh3_OEwpzEWi5gOj_s80sjeP-1yYWe-Fp-6rsY8xAKiOA8--sL0aS3jICz0W0"
        );
        result.put( "__VIEWSTATE", "/wEPDwUKMTE1MTc0MDk0N2RkuN2qfxyKJHLW+uU0D7+B8ZTdGMU="
        );
        result.put( "__VIEWSTATEGENERATOR", "BAA3076B"
        );

        return result;
    }

    public Map<String, String> getCookies() {
        Map<String, String> result = new HashMap<>();

        result.put( "_ga", "GA1.2.56741547.1522259225" );
        result.put( "_gat", "1" );
        result.put( "_gid", "GA1.2.172139468.1522669087" );
        result.put( "_qPageNum_groupm_mediacom_ScandinavianAirlinesSAS%20%20", "0" );
        result.put( "_qsst", "1522316460551" );
        result.put( "_qst", "%5B1%2C0%5D" );
        result.put( "_qubitTracker_s", "1522316460548.275747" );
        result.put( "_qubitTracker", "1522316460548.275747" );
        result.put( "_uetsid", "_uet41a8afee" );
        result.put( "_vwo_uuid_v2", "DC37DBCE16D0544D4375616C179087F65|b583c542e9679d1ceea0e1b7f2f593ae" );
        result.put( "D_HID", "4408E6D2-4ADB-33B8-9B38-85B1DD8E3F48" );
        result.put( "D_IID", "DFA0CD18-B1EF-3C78-A5A4-F921660E5919" );
        result.put( "D_SID", "5.20.130.59:DpxH1RqGjZEWvAUG/HNkyAA+ZYFbC/f5/h9W1/xULy0" );
        result.put( "D_UID", "3FBC18FB-45A1-31FF-B402-9C8A6C8987B2" );
        result.put( "D_ZID", "558B0CE7-94FB-3B2C-862F-472365FA994B" );
        result.put( "D_ZUID", "055FC848-1EA9-341C-89FC-EA85662459AF" );
        result.put( "JSESSIONID_SASC", "gyqLatC3gLqrk2ofXXUPhbrP3-Cc2GhMZlnNl6Tqlh0jCweojLvo!-1564805365!265704293" );

        return result;
    }

    public String buildCookiesHeader(String[] cookieMap2Names, String[] cookieMap2Values) {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < cookieMap2Names.length; index++) {

            String name = cookieMap2Names[index];
            String value = cookieMap2Values[index];

            if (index == 0) {
                sb.append( name + "=" + value );
            } else {
                sb.append( "; " + name + "=" + value );
            }

        }
        return sb.toString();
    }

    public List<Double> getPrices(Element panelOfOutBoundFlights) {

        List<Double> result = new ArrayList<>();

        Elements outboundData = panelOfOutBoundFlights.getElementsByClass( "choice" );

        for (Element out : outboundData) {
            Elements priceData = out.getElementsByClass( "number tobeupdated1" );
            if (priceData.hasText()) {
                result.add( Double.parseDouble( priceData.text().replace( ",", "." ) ) );
            }
        }

        return result;

    }

    public List<String> getConnections(Element panelOfOutBoundFlights, Document document) {
        List<String> results = new ArrayList<>();
        List<String> flightPanelIds = new ArrayList<>();

        Elements outboundData = panelOfOutBoundFlights.getElementsByClass( "choice" );
        for (Element out : outboundData) {
            Elements priceData = out.getElementsByClass( "number tobeupdated1" );
            if (priceData.hasText()) {
                String id = priceData.attr( "id" );
                flightPanelIds.add( id.substring( id.indexOf( "_" ), id.lastIndexOf( "_" ) ) );
            }
        }

        for (int i = 0; i < flightPanelIds.size(); i++) {
            Element outBoundInfo = document.getElementById( "toggleId" + flightPanelIds.get( i ) );
            Elements locations = outBoundInfo.getElementsByClass( "location" );
            if (locations.size() == 2) {
                results.add( "Direct" );
            } else if (locations.text().contains( "Oslo" )) {
                results.add( "Oslo" );
            } else {
                results.add( "invalid" );
            }
        }

        return results;
    }

    public List<String> getDepartureAndArrivalTimes(Element panelOfOutBoundFlights) {
        List<String> result = new ArrayList<>();

        List<String> flightPanelIds = new ArrayList<>();

        Elements outboundData = panelOfOutBoundFlights.getElementsByClass( "choice" );
        for (Element out : outboundData) {
            Elements priceData = out.getElementsByClass( "number tobeupdated1" );
            if (priceData.hasText()) {
                String id = priceData.attr( "id" );
                flightPanelIds.add( id.substring( id.indexOf( "_" ), id.lastIndexOf( "_" ) ) );
            }
        }

        for (int i = 0; i < flightPanelIds.size(); i++) {
            Element times = panelOfOutBoundFlights.getElementById( "idLine" + flightPanelIds.get( i ) );
            String time = times.select( "td.time" ).text();
            result.add( time );
        }

        return result;
    }

    public List<String> getDepartureTimes(List<String> outBoundTimes) {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < outBoundTimes.size(); i++) {
            String time = outBoundTimes.get( i );
            String departureTime = time.substring( 0, time.indexOf( "-" ) ).trim();
            result.add( departureTime );
        }

        return result;
    }

    public List<String> getArrivalTimes(List<String> outBoundTimes) {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < outBoundTimes.size(); i++) {
            String time = outBoundTimes.get( i );
            String arrivalTime = time.substring( time.indexOf( "-" ) + 1, time.length() ).trim();
            result.add( arrivalTime );
        }

        return result;

    }

    public List<String> getClassTypes(Element panelOfOutBoundFlights) {

        List<String> result = new ArrayList<>();

        Elements outboundData = panelOfOutBoundFlights.getElementsByClass( "choice" );

        for (Element out : outboundData) {
            Elements prices = out.getElementsByClass( "number tobeupdated1" );
            if (prices.hasText()) {
                String classType = prices.attr( "id" );
                result.add( classType.substring( classType.lastIndexOf( "_" ) + 1, classType.length() ) );
            }
        }

        return result;

    }

    public List<Flight> filterFlights(List<Flight> flights) {

        List<Flight> results = new ArrayList<>();

        for (Flight fl : flights) {
            if ("Oslo".equals( fl.getConnection() ) || "Direct".equals( fl.getConnection() )) {
                results.add( fl );
            }
        }
        return results;
    }

    public Flight getcheapestOutboundFlight(List<Flight> directFlightsOrViaOslo) {
        Flight result = null;
        double max = Double.MAX_VALUE;
        for (Flight fl : directFlightsOrViaOslo) {
            if ("Outbound".equals( fl.getOutboundOrInbound() ) && fl.getPrice() < max) {
                max = fl.getPrice();
                result = fl;
            }
        }
        return result;
    }

    public Flight getcheapestReturnFlight(List<Flight> directFlightsOrViaOslo) {
        Flight result = null;
        double max = Double.MAX_VALUE;
        for (Flight fl : directFlightsOrViaOslo) {
            if ("Return".equals( fl.getOutboundOrInbound() ) && fl.getPrice() < max) {
                max = fl.getPrice();
                result = fl;
            }
        }
        return result;
    }

    public String returnTaxBreakDown(String flightCombination, List<Flight> directFlightsOrViaOslo) {

        Flight first = null;
        Flight second = null;

        double passengerServiceCharge = 21.97;
        double passengerCharge1;
        double airPassengerDuty;
        double aviationTax = 5.85;
        double passengerCharge2 = 15.2;
        double domesticInternationalFees;

        int firstFlight = Integer.parseInt( flightCombination.substring( 0, flightCombination.indexOf( "," ) ) );
        int secondFlight = Integer.parseInt( flightCombination.substring( flightCombination.indexOf( "," )+1,
                flightCombination.length() ) );

        for (Flight fl : directFlightsOrViaOslo) {
            if (firstFlight == fl.getFlightId()) {
                first = fl;
            }
            if (secondFlight == fl.getFlightId()) {
                second = fl;
            }
        }

        passengerCharge1 = calculatePassengerCharge( first, second );
        airPassengerDuty = calculateAirPassengerDuty( first,second );
        domesticInternationalFees = calculateDomesticIntFees (first,second);

        StringBuilder sb = new StringBuilder(  );

        double total = passengerServiceCharge + passengerCharge1 + airPassengerDuty + aviationTax + passengerCharge2 +
                domesticInternationalFees;

        DecimalFormat f = new DecimalFormat( "##.00" );

        sb.append( "\nBreakdown of Taxes For Selected Flights: \n" )
                .append( "\nPassenger service charge: " + passengerServiceCharge )
                .append( "\nPassenger charge: " + passengerCharge1  )
                .append( "\nAir Passenger Duty (APD): " +airPassengerDuty )
                .append( "\nAviation tax: " + aviationTax )
                .append( "\nPassenger Charge: " + passengerCharge2 )
                .append( "\nDomestic / International fees: " + domesticInternationalFees )
                .append ("\nTotal taxes: "+f.format( total ));


        return sb.toString();


    }

    private double calculateDomesticIntFees(Flight first, Flight second) {
        double result = 0;

        if ("ECONBG".equals( first.getClass_type() )|| "ECOA".equals( first.getClass_type() )) {
            if ("Oslo".equals( first.getConnection()) && "Oslo".equals( second.getConnection() ) ) {
                result = 94.92;
            } else if ("Oslo".equals( first.getConnection()) || "Oslo".equals( second.getConnection() ) ) {
                result = 77.67;
            } else {
                result = 60.42;
            }
        }
        if ("PREMN".equals( first.getClass_type() )|| "PREMB".equals( first.getClass_type())) {
            if ("Oslo".equals( first.getConnection()) && "Oslo".equals( second.getConnection() ) ) {
                result = 101.16;
            } else if ("Oslo".equals( first.getConnection()) || "Oslo".equals( second.getConnection() ) ) {
                result = 83.91;
            } else {
                result = 66.66;
            }
        }
        return result;

    }

    private double calculateAirPassengerDuty(Flight first, Flight second) {
        double result;

        if ("PREMB".equals( second.getClass_type() )) {
            result = 29.59;
        } else {
            result = 14.8;
        }

        return result;
    }

    private double calculatePassengerCharge(Flight first, Flight second) {
        double result = 0;
        if ("Oslo".equals( first.getConnection() ) && "Oslo".equals( second.getConnection() )) {
         result = 3.53 * 2;
        } else if ("Oslo".equals( first.getConnection() ) || "Oslo".equals( second.getConnection() )) {
            result = 3.53;
        } else {
            result = 0;
        }

        return result;

    }
}
