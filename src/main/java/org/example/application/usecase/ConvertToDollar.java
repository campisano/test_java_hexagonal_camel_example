package org.example.application.usecase;

import java.math.BigDecimal;

import org.example.application.port.ConverterService;

public class ConvertToDollar {
    private ConverterService converterService;

    public ConvertToDollar(ConverterService converterService) {
        this.converterService = converterService;
    }

    public String execute(BigDecimal value) {
        return converterService.toDollar(value);
    }
}
