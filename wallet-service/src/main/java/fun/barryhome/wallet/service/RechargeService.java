package fun.barryhome.wallet.service;

import fun.barryhome.wallet.common.enums.TradeType;
import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.domain.DefaultService;
import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.CreditBehavior;
import fun.barryhome.wallet.domain.policy.CheckPolicy;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created on 2020/9/7 4:27 下午
 * 充值
 *
 * @author barry
 * Description:
 */
public class RechargeService extends DefaultService {

    public RechargeService(TradeRecord tradeRecord) {
        super(tradeRecord);
    }

    public RechargeService(Wallet wallet, BigDecimal tradeAmount){
        super(TradeRecord.builder().wallet(wallet).tradeAmount(tradeAmount).build());
    }

    @Override
    protected TradeConfig tradeConfig() {
        return new TradeConfig() {

            @Override
            public TradeType tradeType() {
                return TradeType.RECHARGE;
            }

            @Override
            public Behavior behavior() {
                return new CreditBehavior(getWallet(), getTradeAmount());
            }

            @Override
            public List<CheckPolicy> checkPolicies() {
                return null;
            }
        };
    }

}
