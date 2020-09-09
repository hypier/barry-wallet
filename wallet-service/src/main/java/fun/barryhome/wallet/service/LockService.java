package fun.barryhome.wallet.service;

import fun.barryhome.wallet.common.enums.TradeType;
import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.domain.DefaultService;
import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.LockBehavior;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import fun.barryhome.wallet.domain.policy.CheckPolicyBuilder;
import fun.barryhome.wallet.domain.policy.NoAvailableStatusAllowed;

import java.util.List;

/**
 * Created on 2020/9/8 5:05 下午
 * 锁定
 * @author barry
 * Description:
 */
public class LockService extends DefaultService {

    public LockService(Wallet wallet) {
        super(TradeRecord.builder().wallet(wallet).build());
    }

    @Override
    protected TradeConfig tradeConfig() {
        return new TradeConfig() {
            @Override
            public TradeType tradeType() {
                return TradeType.LOCK;
            }

            @Override
            public Behavior behavior() {
                return new LockBehavior(getWallet());
            }

            @Override
            public List<CheckPolicy> checkPolicies() {
                return CheckPolicyBuilder.builder()
                        .add(new NoAvailableStatusAllowed(getWallet()))
                        .build();
            }
        };
    }
}
