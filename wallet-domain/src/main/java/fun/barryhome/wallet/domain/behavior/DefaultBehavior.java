package fun.barryhome.wallet.domain.behavior;

import fun.barryhome.wallet.BizException;
import fun.barryhome.wallet.domain.model.Wallet;

/**
 * Created on 2020/9/7 10:40 上午
 *
 * @author barry
 * Description:
 */

public abstract class DefaultBehavior implements Behavior {
    /**
     * 钱包
     */
    protected Wallet wallet;

    public DefaultBehavior(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * 执行
     */
    @Override
    public void doAction() {
        if (wallet == null){
            throw new BizException("钱包未初始化");
        }
    }
}
