import lt.andrius_statkevicius.norwegian_crawler.CrawlerService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CrawlerServiceTest {

    CrawlerService testObject = new CrawlerService();

    @Test
//    public void testReturnURL() throws IOException {
////        String url = testObject.returnURL(1);
////        Assert.assertEquals(url, getURL());
////    }

    public String getURL () {
        return "https://www.norwegian.com/en/ipc/availability/avaday?D_City=OSL" +
                "&A_City=RIX&TripType=1&D_Day=01&D_Month=201805" +
                "&D_SelectedDay=01&R_Day=01&R_Month=201805&R_SelectedDay=01" +
                "&dFlight=DY1072OSLRIX&dCabinFareType=1&IncludeTransit=false&AgreementCodeFK=-1" +
                "&CurrencyCode=EUR&rnd=80971&processid=49914&mode=ab";
    }
}
