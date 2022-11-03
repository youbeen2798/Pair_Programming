public class Money {
    private long amount;
    private final Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    public Money(long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money add(Money money) {
        long amount = this.amount + money.getAmount();
        Money result = new Money(amount, currency);
        return result;
    }

    public long getAmount() {
        return this.amount;
    }

    public boolean moneyEquals(Money money) {
        return this.getAmount() == (money.getAmount());
    }

}
