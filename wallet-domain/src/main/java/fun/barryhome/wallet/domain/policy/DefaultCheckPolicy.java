package fun.barryhome.wallet.domain.policy;

import fun.barryhome.wallet.domain.model.Wallet;

/**
 * Created on 2020/9/7 11:28 上午
 *
 * @author barry
 * Description:
 */
public abstract class DefaultCheckPolicy implements CheckPolicy {
    /**
     * 钱包
     */
    protected Wallet wallet;

    public DefaultCheckPolicy(Wallet wallet) {
        this.wallet = wallet;
    }
}
