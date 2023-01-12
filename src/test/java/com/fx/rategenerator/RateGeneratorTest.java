package com.fx.rategenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

class RateGeneratorTest {
    RateGenerator rateGenerator = Mockito.mock(RateGenerator.class);

    @ParameterizedTest()
    @CsvSource(value = {
            "'106, EUR/USD, 1.1000,1.2000,\n01-06-2020 12:01:01:001'",
            "'107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002'",
            "'108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002'",
            "'109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100'",
            "'110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110'",
    })
    void getRateTestSuccess(String message) {
        rateGenerator.onMessage(message);
        Mockito.verify(rateGenerator, Mockito.times(1)).onMessage(message);
    }

    @ParameterizedTest()
    @CsvSource(value = {
            "''"
    })
    void getRateTestFailure(String message) {
        RateGenerator rateGenerator = new RateGenerator();
        Assertions.assertThrows(RuntimeException.class, () -> rateGenerator.onMessage(message), "The csv input is incorrect");
    }
}
