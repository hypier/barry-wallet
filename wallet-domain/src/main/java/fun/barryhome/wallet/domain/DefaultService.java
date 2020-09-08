package fun.barryhome.wallet.domain;

import fun.barryhome.wallet.domain.behavior.Behavior;
import fun.barryhome.wallet.domain.model.TradeEvent;
import fun.barryhome.wallet.domain.model.TradeRecord;
import fun.barryhome.wallet.domain.model.enums.TradeStatus;
import fun.barryhome.wallet.domain.model.enums.TradeType;
import fun.barryhome.wallet.domain.policy.CheckPolicy;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created on 2020/9/7 10:28 上午
 *
 * @author barry
 * Description:
 */
@Data
public abstract class DefaultService implements WalletService {

    abstract static class TradeConfig {
        /**
         * 交易类型
         *
         */
        public abstract TradeType tradeType();

        /**
         * 设置行为
         */
        public abstract Behavior behavior();

        /**
         * 设置检查策略
         */
        public abstract List<CheckPolicy> checkPolicies();
    }

    @Setter(AccessLevel.PRIVATE)
    private TradeRecord tradeRecord;

    public DefaultService(TradeRecord tradeRecord) {
        this.tradeRecord = tradeRecord;
        initTradeRecord();
    }

    public DefaultService() {
    }

    protected abstract TradeConfig tradeConfig();

    /**
     * 初始化 tradeRecord
     */
    private void initTradeRecord() {

        tradeRecord.setTradeType(tradeConfig().tradeType());
        tradeRecord.setInOutFlag(tradeConfig().behavior().getInOutFlag());
        tradeRecord.setTradeStatus(TradeStatus.PROCESSING);

        if (Strings.isEmpty(tradeRecord.getTradeNumber())) {
            tradeRecord.setTradeNumber(UUID.randomUUID().toString());
        }

        if (tradeRecord.getTradeAmount() == null) {
            tradeRecord.setTradeAmount(BigDecimal.ZERO);
        }
    }

    /**
     * 检查策略
     */
    private void check() {
        List<CheckPolicy> checkPolicies = tradeConfig().checkPolicies();
        if (checkPolicies != null && checkPolicies.size() > 0) {
            checkPolicies.forEach(CheckPolicy::check);
        }
    }

    /**
     * 执行操作
     */
    @Override
    public void done() {
        check();

        tradeConfig().behavior().doAction();

        tradeRecord.setBalance(tradeRecord.getWallet().getBalance());
        tradeRecord.setTradeStatus(TradeStatus.SUCCEED);
    }

    /**
     * 发送事件
     *
     * @param applicationEventPublisher
     */
    @Override
    public void sendEvent(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new TradeEvent(tradeRecord));
    }

    /**
     * 执行操作并发送事件
     * @param applicationEventPublisher
     */
    public void doneAndSentEvent(ApplicationEventPublisher applicationEventPublisher){
        done();
        sendEvent(applicationEventPublisher);
    }
}
