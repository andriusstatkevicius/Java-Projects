package lt.andrius_statkevicius.laivu_musis.services;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public abstract class WebService {

    public static final String SERVER_URL = "http://miskoverslas.lt/laivu_musis/";

    protected static String convertInputStreamToString(InputStream inputStream) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        return writer.toString();
    }


}
