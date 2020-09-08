package fun.barryhome.wallet.service;

import fun.barryhome.wallet.domain.event.TradeEventSender;
import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.model.enums.WalletStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2020/9/7 4:33 下午
 *
 * @author barry
 * Description:
 */
@SpringBootTest
class RechargeServiceTest {
    @Autowired
    private TradeEventSender tradeEventSender;

    private Wallet initWallet(){
        return Wallet.builder()
                .walletId(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(100))
                .walletStatus(WalletStatus.AVAILABLE)
                .build();
    }

    @Test
    void exec() {
        RechargeService rechargeService = new RechargeService(initWallet(), BigDecimal.valueOf(20));
        rechargeService.done();
        tradeEventSender.send(rechargeService.getTradeRecord());

        System.out.println(rechargeService.getTradeRecord());
    }
}