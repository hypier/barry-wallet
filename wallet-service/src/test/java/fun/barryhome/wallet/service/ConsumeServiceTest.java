package fun.barryhome.wallet.service;

import fun.barryhome.wallet.common.enums.WalletStatus;
import fun.barryhome.wallet.common.model.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;


/**
 * Created on 2020/9/7 12:07 下午
 *
 * @author barry
 * Description:
 */

@SpringBootTest
class ConsumeServiceTest {


    private Wallet initWallet(){
        return Wallet.builder()
                .walletId(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(100))
                .walletStatus(WalletStatus.AVAILABLE)
                .build();
    }

    @Test
    public void exec(){

        ConsumeService consumeService = new ConsumeService(initWallet(), BigDecimal.valueOf(20));
        consumeService.done();

        System.out.println(consumeService.getTradeRecord());
    }
}