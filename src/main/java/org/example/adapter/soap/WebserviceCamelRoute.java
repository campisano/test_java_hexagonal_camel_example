package org.example.adapter.soap;

import javax.inject.Singleton;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.soap.SoapDataFormat;
import org.example.adapter.soap.generated.NumberConversionSoapType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class WebserviceCamelRoute extends RouteBuilder {
    public static final String ROUTE_NAME = "direct:webservice";
    private static final String WSDL = "https://www.dataaccess.com/webservicesserver/numberconversion.wso?WSDL";

    private static final Logger LOG = LoggerFactory.getLogger(WebserviceCamelRoute.class);

    @Override
    public void configure() throws Exception {
        var jaxbPackage = NumberConversionSoapType.class.getPackage().getName();
        var soapDF = new SoapDataFormat(jaxbPackage);
        soapDF.setVersion("1.2");

        from(ROUTE_NAME).log(LoggingLevel.INFO, LOG, "Starting webservice call")
                .log(LoggingLevel.DEBUG, LOG,
                        String.format("Original message: %s", body().convertToString().toString())) //
                .marshal(soapDF)
                //
                .log(LoggingLevel.DEBUG, LOG, String.format("Sending message: %s", body().convertToString().toString())) //
                .setHeader(Exchange.HTTP_METHOD, constant("POST")) //
                .setHeader(Exchange.CONTENT_TYPE, constant("application/soap+xml;charset=UTF-8")) //
                .to(WSDL)
                //
                .log(LoggingLevel.DEBUG, LOG,
                        String.format("Retrieved message: %s", body().convertToString().toString())) //
                .unmarshal(soapDF) //
                .log(LoggingLevel.DEBUG, LOG,
                        String.format("Returning message: %s", body().convertToString().toString()));
    }
}
