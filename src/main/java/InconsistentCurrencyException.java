public class InconsistentCurrencyException extends RuntimeException {
    public InconsistentCurrencyException(Currency currency, Currency currency1) {
        super("Not match currency: " + currency + " & " + currency1);
    }
}
