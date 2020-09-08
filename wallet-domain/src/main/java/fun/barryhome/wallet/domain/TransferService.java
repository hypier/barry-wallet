package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.behavior.CreditBehavior;
import fun.barryhome.wallet.domain.behavior.DebitBehavior;
import fun.barryhome.wallet.domain.model.TradeRecord;
import fun.barryhome.wallet.domain.model.Wallet;
import fun.barryhome.wallet.domain.model.enums.InOutFlag;
import fun.barryhome.wallet.domain.model.enums.TradeType;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import fun.barryhome.wallet.domain.policy.CheckPolicyBuilder;
import fun.barryhome.wallet.domain.policy.NoOverdraftAllowed;
import fun.barryhome.wallet.domain.policy.NoStatusAllowed;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created on 2020/9/7 2:41 下午
 * 转账
 *
 * @author barry
 * Description:
 */
public class TransferService implements WalletService {

    private final BigDecimal tradeAmount;
    private final Wallet fromWallet;
    private final Wallet toWallet;
    private DefaultService fromService;
    private DefaultService toService;

    public TransferService(Wallet fromWallet, Wallet toWallet, BigDecimal tradeAmount) {
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.tradeAmount = tradeAmount;
    }


    @Override
    public void done() {
        fromService = new DefaultService(TradeRecord.builder().wallet(fromWallet).tradeAmount(tradeAmount).build()) {

            @Override
            protected TradeConfig tradeConfig() {
                return new TradeConfig() {
                    @Override
                    public TradeType tradeType() {
                        return TradeType.TRANSFER;
                    }

                    @Override
                    public Behavior behavior() {
                        return new DebitBehavior(fromWallet, tradeAmount);
                    }

                    @Override
                    public List<CheckPolicy> checkPolicies() {
                        return CheckPolicyBuilder.builder()
                                .add(new NoOverdraftAllowed(fromWallet, getTradeRecord().getTradeAmount()))
                                .add(new NoStatusAllowed(fromWallet))
                                .build();
                    }
                };
            }
        };
        toService = new DefaultService(TradeRecord.builder().wallet(toWallet).tradeAmount(tradeAmount).build()) {

            @Override
            protected TradeConfig tradeConfig() {
                return new TradeConfig() {
                    @Override
                    public TradeType tradeType() {
                        return TradeType.TRANSFER;
                    }

                    @Override
                    public Behavior behavior() {
                        return new CreditBehavior(toWallet, tradeAmount);
                    }

                    @Override
                    public List<CheckPolicy> checkPolicies() {
                        return null;
                    }
                };
            }
        };

        fromService.done();
        toService.done();
    }


    /**
     * 发送事件
     *
     * @param applicationEventPublisher
     */
    @Override
    public void sendEvent(ApplicationEventPublisher applicationEventPublisher) {
        fromService.sendEvent(applicationEventPublisher);
        toService.sendEvent(applicationEventPublisher);
    }
}
