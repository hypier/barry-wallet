package fun.barryhome.wallet.application;


import fun.barryhome.wallet.common.BizException;
import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.repository.trade.TradeRepository;
import fun.barryhome.wallet.repository.wallet.WalletRepository;
import fun.barryhome.wallet.service.RechargeRollbackService;
import fun.barryhome.wallet.service.RechargeService;
import fun.barryhome.wallet.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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
     *
     * @param walletId
     * @param tradeAmount
     */
    @Transactional(rollbackFor = Exception.class)
    public TradeRecord recharge(String walletId, BigDecimal tradeAmount) {
        Wallet wallet = walletRepository.findByWalletId(walletId);
        if (wallet == null) {
            throw new BizException("没有找到钱包");
        }

        RechargeService rechargeService = new RechargeService(wallet, tradeAmount);
        rechargeService.done();

        TradeRecord tradeRecord = rechargeService.getTradeRecord();
        // 保存
        walletRepository.save(tradeRecord.getWallet());

        return tradeRepository.save(tradeRecord);
    }

    /**
     * 充值 - 处理中
     *
     * @param walletId
     * @param tradeAmount
     */
    @Transactional(rollbackFor = Exception.class)
    public TradeRecord rechargeProcess(String walletId, BigDecimal tradeAmount) {
        Wallet wallet = walletRepository.findByWalletId(walletId);
        if (wallet == null) {
            throw new BizException("没有找到钱包");
        }

        RechargeService rechargeService = new RechargeService(wallet, tradeAmount);
        rechargeService.process();

        TradeRecord tradeRecord = rechargeService.getTradeRecord();
        // 保存
        tradeRepository.save(tradeRecord);

        return tradeRecord;
    }

    /**
     * 充值 -- 完成
     *
     * @param tradeNumber
     */
    @Transactional(rollbackFor = Exception.class)
    public TradeRecord rechargeDone(String tradeNumber) {

        TradeRecord trade = tradeRepository.findByTradeNumber(tradeNumber);
        if (trade == null) {
            throw new BizException("没有找到交易记录");
        }

        RechargeService rechargeService = new RechargeService(trade);
        rechargeService.done();

        TradeRecord tradeRecord = rechargeService.getTradeRecord();
        // 保存
        walletRepository.save(tradeRecord.getWallet());

        return tradeRepository.save(tradeRecord);
    }


    /**
     * 转账
     *
     * @param fromWalletId
     * @param toWalletId
     * @param tradeAmount
     */
    @Transactional(rollbackFor = Exception.class)
    public void transfer(String fromWalletId, String toWalletId, BigDecimal tradeAmount) {
        Wallet fromWallet = walletRepository.findByWalletId(fromWalletId);
        if (fromWallet == null) {
            throw new BizException("没有找到钱包");
        }

        Wallet toWallet = walletRepository.findByWalletId(toWalletId);
        if (toWallet == null) {
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

    /**
     * 充值撤销
     *
     * @param tradeNumber
     */
    @Transactional(rollbackFor = Exception.class)
    public TradeRecord rollback(String tradeNumber) {
        TradeRecord sourceTrade = tradeRepository.findByTradeNumber(tradeNumber);
        if (sourceTrade == null) {
            throw new BizException("没有找到交易记录");
        }

        RechargeRollbackService rechargeRollbackService = new RechargeRollbackService(sourceTrade);
        rechargeRollbackService.done();

        // 保存
        walletRepository.save(rechargeRollbackService.getTradeRecord().getWallet());
        tradeRepository.save(rechargeRollbackService.getTradeRecord());

        return rechargeRollbackService.getTradeRecord();
    }


}
