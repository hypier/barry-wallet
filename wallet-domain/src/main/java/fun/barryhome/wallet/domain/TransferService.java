package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.CreditBehavior;
import fun.barryhome.wallet.domain.behavior.DebitBehavior;
import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import fun.barryhome.wallet.domain.policy.NoOverdraftAllowed;
import fun.barryhome.wallet.domain.policy.NoStatusAllowed;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created on 2020/9/7 2:41 下午
 * 转账
 * @author barry
 * Description:
 */
public class TransferService implements WalletService{

    private final BigDecimal tradeAmount;

    private final Wallet fromWallet;

    private final Wallet toWallet;

    public TransferService(Wallet fromWallet, Wallet toWallet, BigDecimal tradeAmount){
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.tradeAmount = tradeAmount;
    }


    @Override
    public void exec() {
        new DefaultService(fromWallet) {
            @Override
            protected Behavior behavior() {
                return new DebitBehavior(getWallet(), tradeAmount);
            }

            @Override
            protected List<CheckPolicy> checkPolicies() {
                addPolicy(new NoOverdraftAllowed(getWallet(), tradeAmount));
                return addPolicy(new NoStatusAllowed(getWallet()));
            }
        }.exec();

        new DefaultService(toWallet) {
            @Override
            protected Behavior behavior() {
                return new CreditBehavior(getWallet(), tradeAmount);
            }

            @Override
            protected List<CheckPolicy> checkPolicies() {
                return null;
            }
        }.exec();
    }
}
