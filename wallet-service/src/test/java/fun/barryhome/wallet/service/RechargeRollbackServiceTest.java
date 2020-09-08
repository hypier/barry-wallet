package fun.barryhome.wallet.service;

import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.domain.event.TradeEventSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2020/9/8 4:59 下午
 *
 * @author barry
 * Description:
 */
@SpringBootTest
class RechargeRollbackServiceTest {

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
        //tradeEventSender.send(rechargeService.getTradeRecord());
        System.out.println(rechargeService.getTradeRecord());

        RechargeRollbackService rollbackService = new RechargeRollbackService(rechargeService.getTradeRecord());
        rollbackService.done();
        //tradeEventSender.send(rollbackService.getTradeRecord());
        System.out.println(rollbackService.getTradeRecord());
    }
}