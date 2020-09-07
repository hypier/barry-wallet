package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.DebitBehavior;
import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import fun.barryhome.wallet.domain.policy.NoOverdraftAllowed;
import fun.barryhome.wallet.domain.policy.NoStatusAllowed;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private final BigDecimal amount;

    public ConsumeService(Wallet wallet, BigDecimal amount) {
        super(wallet);
        this.amount = amount;
    }


    /**
     * 设置行为
     */
    @Override
    protected Behavior behavior() {
        return new DebitBehavior(getWallet(), amount);
    }

    /**
     * 设置检查策略
     */
    @Override
    protected List<CheckPolicy> checkPolicies() {
        List<CheckPolicy> checkPolicies = new ArrayList<>();
        checkPolicies.add(new NoOverdraftAllowed(getWallet(), amount));
        checkPolicies.add(new NoStatusAllowed(getWallet()));

        return checkPolicies;
    }
}
