package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BankTest {
    private Money krwMoney;
    private Money dollarMoney;
    private Bank bank;

    @BeforeEach
    void setUp() {
        krwMoney = mock(Money.class);
        dollarMoney = mock(Money.class);
        bank = new Banker();
    }

    @Test
    @DisplayName("통화는 달러화와 원화만이 존재하고, 환율은 1달러 <-> 1,000원")
    void bank_exchange_dollarToKRW() {
        // 1달러
        Money dollarMoney2 = new Money(1, Currency.DOLLAR);

        Money result = bank.exchange(dollarMoney2, Currency.KRW);
        assertThat(result.getAmount()).isEqualTo(1000);
    }

}