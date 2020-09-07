package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.DebitBehavior;
import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import fun.barryhome.wallet.domain.policy.NoOverdraftAllowed;
import fun.barryhome.wallet.domain.policy.NoStatusAllowed;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created on 2020/9/7 10:19 上午
 * 消费服务
 * @author barry
 * Description:
 */
public class ConsumeService extends DefaultService {
    /**
     * 消费金额
     */
    private final BigDecimal tradeAmount;

    public ConsumeService(Wallet wallet, BigDecimal tradeAmount) {
        super(wallet);
        this.tradeAmount = tradeAmount;
    }

    /**
     * 设置行为
     */
    @Override
    protected Behavior behavior() {
        return new DebitBehavior(getWallet(), tradeAmount);
    }

    /**
     * 设置检查策略
     */
    @Override
    protected List<CheckPolicy> checkPolicies() {
        addPolicy(new NoOverdraftAllowed(getWallet(), tradeAmount));
        return addPolicy(new NoStatusAllowed(getWallet()));
    }
}
