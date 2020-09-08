package fun.barryhome.wallet.service;

import fun.barryhome.wallet.BizException;
import fun.barryhome.wallet.domain.DefaultService;
import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.DebitBehavior;
import fun.barryhome.wallet.domain.model.TradeRecord;
import fun.barryhome.wallet.domain.model.enums.InOutFlag;
import fun.barryhome.wallet.domain.model.enums.TradeType;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import fun.barryhome.wallet.domain.policy.CheckPolicyBuilder;
import fun.barryhome.wallet.domain.policy.NoOverdraftAllowed;
import fun.barryhome.wallet.domain.policy.NoStatusAllowed;

import java.util.List;

/**
 * Created on 2020/9/8 4:28 下午
 * 充值撤销
 * @author barry
 * Description:
 */
public class RechargeRollbackService extends DefaultService {

    public RechargeRollbackService(TradeRecord sourceTrade) {
        super(TradeRecord.builder()
                .wallet(sourceTrade.getWallet())
                .tradeAmount(sourceTrade.getTradeAmount())
                .sourceNumber(sourceTrade.getTradeNumber())
                .build());

        if (!sourceTrade.getInOutFlag().equals(InOutFlag.IN)){
            throw new BizException("不支持此类型");
        }
    }

    @Override
    protected DefaultService.TradeConfig tradeConfig() {
        return new TradeConfig() {
            @Override
            public TradeType tradeType() {
                return TradeType.RECHARGE_ROLLBACK;
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
