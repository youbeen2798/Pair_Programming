package exception;


import domain.Currency;

public class ConsistentCurrencyException extends RuntimeException {
    public ConsistentCurrencyException(Currency currency) {
        super("Can not exchange the same currency: " + currency);
    }
}
