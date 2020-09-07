import fun.barryhome.wallet.domain.ConsumeService;
import fun.barryhome.wallet.domain.model.Wallet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Created on 2020/9/7 11:43 上午
 *
 * @author barry
 * Description:
 */
public class WalletTest {

    @Test
    public void ConsumeTest(){
        Wallet wallet = new Wallet();
        BigDecimal tradeAmount = BigDecimal.valueOf(20);
        ConsumeService consumeService = new ConsumeService(wallet, tradeAmount);
        consumeService.run();
    }
}
