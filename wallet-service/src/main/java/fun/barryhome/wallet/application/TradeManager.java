package fun.barryhome.wallet.application;


import fun.barryhome.wallet.common.BizException;
import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.repository.TradeRepository;
import fun.barryhome.wallet.repository.WalletRepository;
import fun.barryhome.wallet.service.RechargeService;
import fun.barryhome.wallet.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        if (wallet == null){
            throw new BizException("没有找到钱包");
        }

        RechargeService rechargeService = new RechargeService(wallet, tradeAmount);
        rechargeService.done();

        walletRepository.save(rechargeService.getTradeRecord().getWallet());
        tradeRepository.save(rechargeService.getTradeRecord());

        return rechargeService.getTradeRecord();
    }

    /**
     * 转账
     * @param fromWalletId
     * @param toWalletId
     * @param tradeAmount
     */
    @Transactional(rollbackFor = Exception.class)
    public void transfer(String fromWalletId, String toWalletId, BigDecimal tradeAmount){
        Wallet fromWallet = walletRepository.findByWalletId(fromWalletId);
        if (fromWallet == null){
            throw new BizException("没有找到钱包");
        }

        Wallet toWallet = walletRepository.findByWalletId(toWalletId);
        if (toWallet == null){
            throw new BizException("没有找到钱包");
        }

        TransferService transferService = new TransferService(fromWallet, toWallet, tradeAmount);
        transferService.done();

        List<Wallet> wallets = new ArrayList<>();
        wallets.add(fromWallet);
        wallets.add(toWallet);

        walletRepository.save(wallets);
        tradeRepository.save(transferService.getTradeRecords());
    }
}
