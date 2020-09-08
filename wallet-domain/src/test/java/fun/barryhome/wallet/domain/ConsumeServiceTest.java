package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.model.TradeRecord;
import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.model.enums.WalletStatus;
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
                .walletStatus(WalletStatus.AVAILABLE)
                .build();
    }

    @Test
    public void ConsumeTest(){
        TradeRecord tradeRecord = TradeRecord.builder()
                .wallet(initWallet())
                .tradeAmount(BigDecimal.valueOf(20))
                .build();

        ConsumeService consumeService = new ConsumeService(tradeRecord);
        consumeService.done();

        System.out.println(tradeRecord.getWallet());
    }
}