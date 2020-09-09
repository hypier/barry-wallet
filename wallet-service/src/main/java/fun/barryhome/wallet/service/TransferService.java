package fun.barryhome.wallet.service;

import fun.barryhome.wallet.common.enums.TradeType;
import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.domain.DefaultService;
import fun.barryhome.wallet.domain.WalletService;
import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.CreditBehavior;
import fun.barryhome.wallet.domain.behavior.DebitBehavior;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import fun.barryhome.wallet.domain.policy.CheckPolicyBuilder;
import fun.barryhome.wallet.domain.policy.NoOverdraftAllowed;
import fun.barryhome.wallet.domain.policy.NoStatusAllowed;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/9/7 2:41 下午
 * 转账
 *
 * @author barry
 * Description:
 */
public class TransferService implements WalletService {

    private final BigDecimal tradeAmount;
    private final Wallet fromWallet;
    private final Wallet toWallet;

    @Getter
    private List<TradeRecord> tradeRecords;

    public TransferService(Wallet fromWallet, Wallet toWallet, BigDecimal tradeAmount) {
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.tradeAmount = tradeAmount;
    }


    @Override
    public void done() {
        DefaultService fromService = new DefaultService(TradeRecord.builder().wallet(fromWallet).tradeAmount(tradeAmount).build()) {

            @Override
            protected TradeConfig tradeConfig() {
                return new TradeConfig() {
                    @Override
                    public TradeType tradeType() {
                        return TradeType.TRANSFER;
                    }

                    @Override
                    public Behavior behavior() {
                        return new DebitBehavior(fromWallet, tradeAmount);
                    }

                    @Override
                    public List<CheckPolicy> checkPolicies() {
                        return CheckPolicyBuilder.builder()
                                .add(new NoOverdraftAllowed(fromWallet, tradeAmount))
                                .add(new NoStatusAllowed(fromWallet))
                                .build();
                    }
                };
            }
        };
        DefaultService toService = new DefaultService(TradeRecord.builder().wallet(toWallet).tradeAmount(tradeAmount).build()) {

            @Override
            protected TradeConfig tradeConfig() {
                return new TradeConfig() {
                    @Override
                    public TradeType tradeType() {
                        return TradeType.TRANSFER;
                    }

                    @Override
                    public Behavior behavior() {
                        return new CreditBehavior(toWallet, tradeAmount);
                    }

                    @Override
                    public List<CheckPolicy> checkPolicies() {
                        return null;
                    }
                };
            }
        };

        tradeRecords = new ArrayList<>();
        tradeRecords.add(fromService.getTradeRecord());
        tradeRecords.add(toService.getTradeRecord());

        fromService.done();
        toService.done();
    }

}
