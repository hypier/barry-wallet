package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.LockBehavior;
import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import fun.barryhome.wallet.domain.policy.NoStatusAllowed;

import java.util.List;

/**
 * Created on 2020/9/7 3:17 下午
 * 锁定
 * @author barry
 * Description:
 */
public class LockService extends DefaultService {

    public LockService(Wallet wallet) {
        super(wallet);
    }

    /**
     * 设置行为
     */
    @Override
    protected Behavior behavior() {
        return new LockBehavior(getWallet());
    }

    /**
     * 设置检查策略
     */
    @Override
    protected List<CheckPolicy> checkPolicies() {
        return addPolicy(new NoStatusAllowed(getWallet()));
    }
}
