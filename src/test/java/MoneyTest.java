import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    Money KRW_money;
    Money DOLLAR_money;

    @BeforeEach
    void setUp() {
        KRW_money = new Money(1000L, Currency.KRW);
        DOLLAR_money = new Money(5L, Currency.DOLLAR);

    }

    @Test
    @DisplayName("addMoney 원화 성공 테스트")
    void money_KRW_add_success() {
        Money KRW_money2 = new Money(1000L, Currency.KRW);
        Money resultMoney = KRW_money.add(KRW_money2);
//        assertThat(resultMoney.equals(money));
        assertThat(resultMoney.getAmount()).isEqualTo(2000L);
    }


    @Test
    @DisplayName("addMoney 달러 성공 테스트")
    void money_DOLLAR_add_success() {
        Money DOLLAR_money2 = new Money(5L, Currency.DOLLAR);
        Money resultMoney = DOLLAR_money.add(DOLLAR_money2);
        assertThat(resultMoney.getAmount()).isEqualTo(10L);
    }

    @Test
    @DisplayName("equals 성공 테스트")
    void money_KRW_equals_success() {
        Money money2 = new Money(1000L, Currency.KRW);
        assertThat(money2.equals(KRW_money)).isTrue();
    }

    @Test
    @DisplayName("money 음수 실패 테스트")
    void money_KRW_invalid_success() {
        assertThatThrownBy(() -> new Money(-1000L, Currency.KRW))
            .isInstanceOf(InvalidMoneyException.class)
            .hasMessageContainingAll("Invalid");
    }




//    @Test
//    @DisplayName("addMoney 실패 테스트")
//    void money_add_fail() {
//        long amount = 1000L;
//        money.add(amount);
//        assertFalse(money.getAmount() == 3000);
//    }
//
//    @Test
//    @DisplayName("Money Equal Test")
//    void money_equal_success() {
//        long amount = 1000L;
//        Money money2 = new Money(1000L);
//
//        //  assertThat()
//        assertThat(money.moneyEquals(money2)).isEqualTo(true);
//    }


}
