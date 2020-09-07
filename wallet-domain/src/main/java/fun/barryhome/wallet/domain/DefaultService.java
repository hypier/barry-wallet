package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created on 2020/9/7 10:28 上午
 *
 * @author barry
 * Description:
 */
@Data
public abstract class DefaultService implements WalletService {

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PROTECTED)
    private Wallet wallet;

    public DefaultService(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * 设置行为
     */
    protected abstract Behavior behavior();

    /**
     * 设置检查策略
     */
    protected abstract List<CheckPolicy> checkPolicies();

    /**
     * 执行操作
     */
    @Override
    public void run() {
        if (checkPolicies() != null && checkPolicies().size() > 0) {
            checkPolicies().forEach(CheckPolicy::check);
        }

        behavior().exec();
    }
}
