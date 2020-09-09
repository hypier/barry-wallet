package fun.barryhome.wallet.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * Created on 2020/9/8 6:30 下午
 *
 * @author barry
 * Description:
 */
@SpringBootTest
class TradeManagerTest {

    @Autowired
    private TradeManager tradeManager;

    @Test
    void recharge() {
        tradeManager.recharge("4bfe7223-ec2e-4788-8921-c1d9f1c7808o", BigDecimal.valueOf(20));
    }

    @Test
    void rechargeProcess() {
        tradeManager.rechargeProcess("4bfe7223-ec2e-4788-8921-c1d9f1c7808o", BigDecimal.valueOf(20));
    }

    @Test
    void rechargeDone() {
        tradeManager.rechargeDone("d524f0dc-54a7-43ff-84d6-cf9b08eea07c");
    }
    @Test
    void transfer() {
        tradeManager.transfer("4bfe7223-ec2e-4788-8921-c1d9f1c78081","4bfe7223-ec2e-4788-8921-c1d9f1c7808o",
                 BigDecimal.valueOf(50));
    }

    @Test
    void rollback() {
        tradeManager.rollback("5bfe8c20-6465-421f-b0ea-58a14c834175");
    }
}