package fun.barryhome.wallet.application;


import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.repository.TradeRepository;
import fun.barryhome.wallet.repository.WalletRepository;
import fun.barryhome.wallet.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created on 2020/9/8 6:06 下午
 *
 * @author barry
 * Description:
 */
@Component
public class TradeManager {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private WalletRepository walletRepository;

    /**
     * 充值
     * @param walletId
     * @param tradeAmount
     */
    @Transactional(rollbackFor = Exception.class)
    public TradeRecord recharge(String walletId, BigDecimal tradeAmount) {
        Wallet wallet = walletRepository.findByWalletId(walletId);
        RechargeService rechargeService = new RechargeService(wallet, tradeAmount);
        rechargeService.done();

        walletRepository.save(rechargeService.getTradeRecord().getWallet());
        tradeRepository.save(rechargeService.getTradeRecord());

        return rechargeService.getTradeRecord();
    }
}
