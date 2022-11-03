package domain;

public enum Currency {

    KRW(0.001), DOLLAR(1.0), EUR(0.98);

    private double exchangeRate;

    Currency(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}
