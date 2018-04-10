package lt.andrius_statkevicius.sas_crawler;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.TableHeaderUI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Testing {

    //THIRD METHOD THAT I TRIED WHICH WAS NOT SUCCESSFUL EITHER - HTMLUNITDRIVER

    static HashMap cookieMap = new HashMap();
    static Map<String, String> cookieMap2 = new HashMap<>();
    static HashMap<String, String> dataMap = new HashMap();
    static HashMap<String, String> dataMap2 = new HashMap();
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
    public static final String BOOK_FLYSAS = "https://book.flysas.com/pl/SASC/wds/Override.action?SO_SITE_EXT_PSPURL=https://classic.sas.dk/SASCredits/SASCreditsPaymentMaster.aspx&SO_SITE_TP_TPC_POST_EOT_WT=50000&SO_SITE_USE_ACK_URL_SERVICE=TRUE&WDS_URL_JSON_POINTS=ebwsprod.flysas.com%2FEAJI%2FEAJIService.aspx&SO_SITE_EBMS_API_SERVERURL=https%3A%2F%2F1aebwsprod.flysas.com%2FEBMSPointsInternal%2FEBMSPoints.asmx&WDS_SERVICING_FLOW_TE_SEATMAP=TRUE&WDS_SERVICING_FLOW_TE_XBAG=TRUE&WDS_SERVICING_FLOW_TE_MEAL=TRUE";
    public static final String HOME_PAGE = "http://www.flysas.com";

    public static void main(String[] args) throws InterruptedException {


        CrawlerService crawlerService = new CrawlerService();

        HtmlUnitDriver driver = new HtmlUnitDriver( BrowserVersion.FIREFOX_45 );

        driver.setJavascriptEnabled( true );

//        driver.manage().timeouts().implicitlyWait( 1000, TimeUnit.MILLISECONDS );

        driver.get("https://www.flysas.com/en/" );
//        driver.manage().getCookies();

        driver.findElement( By.id( "ENCT" ) ).getAttribute( "value" );

//        driver.findElement(By.xpath("//*[@id='minisearchIframeForm']//*[@name='ACTION']")).sendKeys("IFRAME");
        driver.findElement(By.name("ACTION")).sendKeys("IFRAME");
        driver.findElement(By.xpath("//*[@id='minisearchIframeForm']//*[@name='TYPEOFIFRAME']")).sendKeys("MINISEARCH");
        driver.findElement(By.xpath("//*[@id='minisearchIframeForm']//*[@name='PAGECODE']")).sendKeys("REV_AVD_CO");
        driver.findElement(By.xpath("//*[@id='minisearchIframeForm']//*[@name='SITE']")).sendKeys("REV_AVD_CO");
        driver.findElement(By.xpath("//*[@id='minisearchIframeForm']//*[@name='WDS_CFF_TOGGLE']")).sendKeys("FALSE");
        driver.findElement(By.xpath("//*[@id='minisearchIframeForm']//*[@name='SELECTEDOUTDATE']")).sendKeys("20180507");
        driver.findElement(By.xpath("//*[@id='minisearchIframeForm']//*[@name='SELECTEDHOMEDATE']")).sendKeys("20180513");
//        driver.findElement(By.xpath("//*[@id='minisearchIframeForm']//*[@name='ENTRY_REQUEST']")).sendKeys(CrawlerService.ENTRY_REQUEST);


        Thread.sleep( 1000 );

        System.out.println(driver.getCurrentUrl());
    }

}

