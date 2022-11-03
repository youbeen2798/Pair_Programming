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
    @DisplayName("통화는 달러화와 원화만이 존재, 환율은 1달러 <-> 1,000원")
    void bank_exchange_dollarToKRW() {
        // 1달러
        when(dollarMoney.getCurrency()).thenReturn(Currency.DOLLAR);
        when(dollarMoney.getAmount()).thenReturn(1.0);

        Money result = bank.exchange(dollarMoney, Currency.KRW);
        assertThat(result.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("1,000원 -환전-> 1$")
    void bank_exchange_KRWToDollar() {
        // 1달러
        when(krwMoney.getCurrency()).thenReturn(Currency.KRW);
        when(krwMoney.getAmount()).thenReturn(1000.0);

        Money result = bank.exchange(krwMoney, Currency.DOLLAR);
        assertThat(result.getAmount()).isEqualTo(1);
        assertThat(result.equals(new Money(1, Currency.DOLLAR))).isTrue();
    }

    @Test
    @DisplayName("5.25$ -> 5,250원")
    void bank_exchange_decimal_dollarToKRW() {
        when(dollarMoney.getCurrency()).thenReturn(Currency.DOLLAR);
        when(dollarMoney.getAmount()).thenReturn(5.25);

        Money result = bank.exchange(dollarMoney, Currency.KRW);
        assertThat(result.getAmount()).isEqualTo(5250);
        assertThat(result.equals(new Money(5250, Currency.KRW))).isTrue();
    }

    @Test
    @DisplayName("달러 -> 원화: 5원 이상 -> 10원으로 반올림")
    void bank_exchange_dollarToKRW_check_KRW_AmountUnit() {
        when(dollarMoney.getCurrency()).thenReturn(Currency.DOLLAR);
        when(dollarMoney.getAmount()).thenReturn(1.005);

        Money result = bank.exchange(dollarMoney, Currency.KRW);
        assertThat(result.getAmount()).isEqualTo(1010);
        assertThat(result.equals(new Money(1010, Currency.KRW))).isTrue();
    }
}