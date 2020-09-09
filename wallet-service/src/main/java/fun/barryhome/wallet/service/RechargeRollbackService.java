package fun.barryhome.wallet.service;

import fun.barryhome.wallet.common.enums.TradeType;
import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.domain.DefaultService;
import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.DebitBehavior;
import fun.barryhome.wallet.domain.policy.*;

import java.util.List;

/**
 * Created on 2020/9/8 4:28 下午
 * 充值撤销
 * @author barry
 * Description:
 */
public class RechargeRollbackService extends DefaultService {

    private final TradeRecord sourceTrade;

    public RechargeRollbackService(TradeRecord sourceTrade) {
        super(TradeRecord.builder()
                .wallet(sourceTrade.getWallet())
                .tradeAmount(sourceTrade.getTradeAmount())
                .sourceNumber(sourceTrade.getTradeNumber())
                .build());
        this.sourceTrade = sourceTrade;
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
                        .add(new NoRechargeTypeAllowed(sourceTrade))
                        .add(new NoTimeoutAllowed(sourceTrade))
                        .add(new NoOverdraftAllowed(getWallet(), getTradeAmount()))
                        .add(new NoAvailableStatusAllowed(getWallet()))
                        .build();
            }
        };
    }
}
