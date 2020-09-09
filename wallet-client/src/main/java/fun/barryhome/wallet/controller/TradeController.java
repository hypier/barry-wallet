package fun.barryhome.wallet.controller;

import fun.barryhome.wallet.application.TradeManager;
import fun.barryhome.wallet.common.model.TradeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created on 2020/9/9 4:09 下午
 *
 * @author barry
 * Description:
 */
@RestController
public class TradeController {

    @Autowired
    private TradeManager tradeManager;

    @PostMapping(path = "/recharge", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public TradeRecord recharge(@RequestParam("walletId") String walletId, @RequestParam("tradeAmount") BigDecimal tradeAmount){
        return tradeManager.recharge(walletId, tradeAmount);
    }

    @PostMapping(path = "/rollback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public TradeRecord rollback(@RequestParam("tradeNumber") String tradeNumber){
        return tradeManager.rollback(tradeNumber);
    }

    @PostMapping(path = "transfer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void transfer(@RequestParam("fromWalletId") String fromWalletId, @RequestParam("toWalletId") String toWalletId,
                         @RequestParam("tradeAmount") BigDecimal tradeAmount) {
        tradeManager.transfer(fromWalletId, toWalletId, tradeAmount);
    }
}
