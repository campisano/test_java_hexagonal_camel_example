package org.example.adapter.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.camel.FluentProducerTemplate;
import org.example.adapter.soap.WebserviceCamelRoute;
import org.example.adapter.soap.generated.NumberToDollars;
import org.example.adapter.soap.generated.NumberToDollarsResponse;
import org.example.adapter.soap.generated.NumberToWords;
import org.example.adapter.soap.generated.NumberToWordsResponse;
import org.example.application.port.ConverterService;

@Singleton
public class ConverterServiceCamel implements ConverterService {
    @Inject
    FluentProducerTemplate ftp;

    @Override
    public String toDollar(BigDecimal value) {
        var ntd = new NumberToDollars();
        ntd.setDNum(value);

        var result = ftp.to(WebserviceCamelRoute.ROUTE_NAME) //
                .withBody(ntd) //
                .request(NumberToDollarsResponse.class);

        return result.getNumberToDollarsResult();
    }

    @Override
    public String toWords(BigInteger value) {
        var ntw = new NumberToWords();
        ntw.setUbiNum(value);

        var result = ftp.to(WebserviceCamelRoute.ROUTE_NAME) //
                .withBody(ntw) //
                .request(NumberToWordsResponse.class);

        return result.getNumberToWordsResult();
    }
}
