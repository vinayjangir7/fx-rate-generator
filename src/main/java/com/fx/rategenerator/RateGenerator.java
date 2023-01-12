package com.fx.rategenerator;

import com.fx.rategenerator.interfaces.MessageStream;
import com.fx.rategenerator.models.FxTransaction;
import com.fx.rategenerator.utils.FxUtil;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RateGenerator implements MessageStream {

    Map<String, FxTransaction> rateMap = new ConcurrentHashMap<>();

    @Override
    public void onMessage(String message) {
        FxTransaction fxTransaction = FxUtil.getTransaction(message);
        Optional.of(fxTransaction)
                .filter(fx -> Objects.nonNull(fx.getId()))
                .filter(fx -> Objects.nonNull(fx.getCurrencyPair()))
                .filter(fx -> Objects.nonNull(fx.getTransactionTimeStamp()))
                .ifPresent(fx -> rateMap.put(fxTransaction.getCurrencyPair(), fxTransaction));
        System.out.println(getLatestRate(fxTransaction.getCurrencyPair()));
    }

    /*Method for rest end point with currencyPair as a query param and
      returns the latest price for the currency pair
     */
    public FxTransaction getLatestRate(String currencyPair) {
        currencyPair = Optional.ofNullable(currencyPair).orElseGet(String::new);
        /*Making it final to use inside stream as it only allows final values*/
        String finalCurrencyPair = currencyPair;
        return Optional.ofNullable(rateMap.get(currencyPair))
                .orElseThrow(() -> new RuntimeException("No rate present for the Currency pair " + finalCurrencyPair));
    }
}