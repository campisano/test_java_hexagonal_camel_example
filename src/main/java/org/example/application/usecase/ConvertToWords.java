package org.example.application.usecase;

import java.math.BigInteger;

import org.example.application.port.ConverterService;

public class ConvertToWords {
    private ConverterService converterService;

    public ConvertToWords(ConverterService converterService) {
        this.converterService = converterService;
    }

    public String execute(BigInteger value) {
        return converterService.toWords(value);
    }
}
