package org.example.application.port;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ConverterService {
    String toDollar(BigDecimal value);

    String toWords(BigInteger value);
}
