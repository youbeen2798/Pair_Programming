import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

  Money money;

  @BeforeEach
  void setUp(){
    money = new Money(1000L);
  }
  @Test
  @DisplayName("addMoney 성공 테스트")
  void money_add_success(){
    long amount = 1000L;

    money.add(amount);
    assertThat(money.getAmount()).isEqualTo(2000L);
  }

  @Test
  @DisplayName("addMoney 실패 테스트")
  void money_add_fail(){
    long amount = 1000L;
    money.add(amount);
    assertFalse(money.getAmount() == 3000);
  }

  @Test
  @DisplayName("Money Equal Test")
  void money_equal_success(){
    long amount1 = 1000L;
    long amount2 = 2000L;

  //  assertThat()
  }


}
