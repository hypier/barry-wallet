package fun.barryhome.wallet.service;

import fun.barryhome.wallet.common.enums.TradeType;
import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.domain.DefaultService;
import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.DebitBehavior;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import fun.barryhome.wallet.domain.policy.CheckPolicyBuilder;
import fun.barryhome.wallet.domain.policy.NoOverdraftAllowed;
import fun.barryhome.wallet.domain.policy.NoStatusAllowed;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created on 2020/9/7 10:19 上午
 * 消费服务
 *
 * @author barry
 * Description:
 */
public class ConsumeService extends DefaultService {

    public ConsumeService(TradeRecord tradeRecord) {
        super(tradeRecord);
    }

    public ConsumeService(Wallet wallet, BigDecimal tradeAmount) {
        super(TradeRecord.builder().wallet(wallet).tradeAmount(tradeAmount).build());
    }

    @Override
    protected TradeConfig tradeConfig() {
        return new TradeConfig() {
            @Override
            public TradeType tradeType() {
                return TradeType.CONSUME;
            }

            @Override
            public Behavior behavior() {
                return new DebitBehavior(getWallet(), getTradeAmount());
            }

            @Override
            public List<CheckPolicy> checkPolicies() {
                return CheckPolicyBuilder.builder()
                        .add(new NoOverdraftAllowed(getWallet(), getTradeAmount()))
                        .add(new NoStatusAllowed(getWallet()))
                        .build();
            }
        };
    }

}
