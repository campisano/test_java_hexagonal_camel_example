package org.example.adapter;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.example.application.port.ConverterService;
import org.example.application.usecase.ConvertToDollar;
import org.example.application.usecase.ConvertToWords;

@Singleton
public class InjectionProvider {
    @Produces
    final ConvertToDollar convertToDollar;

    @Produces
    final ConvertToWords convertToWords;

    @Inject
    public InjectionProvider(ConverterService converterService) {
        convertToDollar = new ConvertToDollar(converterService);
        convertToWords = new ConvertToWords(converterService);
    }
}
