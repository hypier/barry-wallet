package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.CreditBehavior;
import fun.barryhome.wallet.domain.model.Wallet;
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
    /**
     * 消费金额
     */
    private final BigDecimal tradeAmount;

    public RechargeService(Wallet wallet, BigDecimal tradeAmount) {
        super(wallet);
        this.tradeAmount = tradeAmount;
    }

    /**
     * 设置行为
     */
    @Override
    protected Behavior behavior() {
        return new CreditBehavior(getWallet(), tradeAmount);
    }

    /**
     * 设置检查策略
     */
    @Override
    protected List<CheckPolicy> checkPolicies() {
        return null;
    }
}
