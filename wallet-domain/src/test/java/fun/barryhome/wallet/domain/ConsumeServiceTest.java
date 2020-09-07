package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.model.WalletStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;


/**
 * Created on 2020/9/7 12:07 下午
 *
 * @author barry
 * Description:
 */
class ConsumeServiceTest {

    private Wallet initWallet(){
        return Wallet.builder()
                .walletId(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(100))
                .walletStatus(WalletStatus.Available)
                .build();
    }

    @Test
    public void ConsumeTest(){
        Wallet wallet = initWallet();

        BigDecimal tradeAmount = BigDecimal.valueOf(20);
        ConsumeService consumeService = new ConsumeService(wallet, tradeAmount);
        consumeService.exec();

        System.out.println(wallet);
    }
}