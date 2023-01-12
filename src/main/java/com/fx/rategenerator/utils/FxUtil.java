package com.fx.rategenerator.utils;

import com.fx.rategenerator.enums.TransactionType;
import com.fx.rategenerator.models.FxTransaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FxUtil {

    private FxUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final double SELL_MARGIN = -0.1;
    private static final double BUY_MARGIN = 0.1;
    private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss:SSS";

    private static double calculateMargin(double rate, double marginInPercentage, TransactionType type) {
        if (type.equals(TransactionType.SELL)) {
            return rate - (rate * marginInPercentage / 100);
        } else if (type.equals(TransactionType.BUY)) {
            return rate + (rate * marginInPercentage / 100);
        }
        // If neither SELL nor BUY then return the actual rate
        return rate;
    }

    public static FxTransaction getTransaction(String message) {
        if (message.contains("\n")) {
            message = handleMultiLineMessage(message);
        }
        return fxTransactionMapper(message);
    }

    private static String handleMultiLineMessage(String multiLineMsg) {
        String[] strings = multiLineMsg.split("\n");
        return String.join(" ", strings);
    }

    private static FxTransaction fxTransactionMapper(String msg) {
        String[] msgData = msg.split(",");
        FxTransaction fxTransaction = new FxTransaction();
        if (msgData.length == 5) {
            String[] trimmedMsgData = Arrays.stream(msgData).map(String::trim).toArray(unused -> msgData);

            fxTransaction.setId(trimmedMsgData[0]);
            fxTransaction.setCurrencyPair(trimmedMsgData[1]);

            double sellPriceWithMargin = calculateMargin(Double.parseDouble(trimmedMsgData[2]), SELL_MARGIN, TransactionType.SELL);
            fxTransaction.setSellPrice(sellPriceWithMargin);

            double buyPriceWithMargin = calculateMargin(Double.parseDouble(trimmedMsgData[3]), BUY_MARGIN, TransactionType.BUY);
            fxTransaction.setBuyPrice(buyPriceWithMargin);
            fxTransaction.setTransactionTimeStamp(LocalDateTime.parse(trimmedMsgData[4],
                    DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        }
        return fxTransaction;
    }


}
