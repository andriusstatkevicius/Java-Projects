This is was a homework assignment from one of the companies as well.
From the flysas website I needed to collect departure airport, arrival airport, connection airport, departure time, arrival time, cheapest price and taxes for all flight combinations 
from ARN (Stockholm) to LHR (London) departing 2018-05-07 and returning 2018-05-13. 
Only data for flights that are direct or have a connection at Oslo should have been accepted.

I was using the Fiddler in order to understand the flow of getting the search results. 
I realized that one would first need to send POST request to the referrer page www.flysas.com/en with all search data (departure / arrival airport, dates, round-trip), 
get all the inputs from the referrer page and then post them into the following url:

https://book.flysas.com/pl/SASC/wds/Override.action?SO_SITE_EXT_PSPURL=https://
classic.sas.dk/SASCredits/SASCreditsPaymentMaster.aspx&SO_SITE_TP_TPC_POST_EOT_WT=50000&SO_SITE_USE_ACK_URL_SERVICE=TRUE&WDS_URL_JSON_POINTS=ebwsprod.
flysas.com%2FEAJI%2FEAJIService.aspx&SO_SITE_EBMS_API_SERVERURL=https%3A%2F%2F1aebwsprod.flysas.com%2FEBMSPointsInternal%2FEBMSPoints.asmx&WDS_SERVICING_FLOW_TE_SEATMAP=TRUE&
WDS_SERVICING_FLOW_TE_XBAG=TRUE&WDS_SERVICING_FLOW_TE_MEAL=TRUE

I tried three different methods: 1) Jsoup library in Java; 
2) HtmlUnit (Headless browser for Java); 
3) HtmlUnitDriver (Headless Browser Driver) based on HtmlUnit (Testing class in my source file). 
I realized that Jsoup does not support JavaScript. Therefore I was getting errors from the resultant page that JavaScript is disabled. 
With HtmlUnit I realized that I needed to take two dynamic inputs (SIP_INTERNAL and ENC) from the referrer URL www.flysas.com/en and post them into the book.flysas.com 
link mentioned above in order to produce necessary results. I did not manage to get these inputs, as I could not get the referrer page by HtmlUnit (probably because it’s a referrer page). 
I tried to simulate a session on Fiddler and take SIP_INTERNAL and ENC from there, hardcode it and it was working with HtmlUnit and my code. 
Therefore in the end I downloaded the source file, saved it locally on my computer and extracted all the data from it in order to show how I would have proceeded further 
if I had succeeded to get all the data. I was using the JSOUP library though to extract all the elements from the saved source file. 
I constructed the lists first and then created flight objects so I could manipulate with them. The number of requests could be reduced by 190 requests (based on Fiddler) 
if one would post directly all the data and cookies into the aforementioned link of https://book.flysas.com . The class that should be run is “App_main”.