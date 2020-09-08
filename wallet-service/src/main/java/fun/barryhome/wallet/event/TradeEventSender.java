package fun.barryhome.wallet.event;

import fun.barryhome.wallet.common.model.TradeEvent;
import fun.barryhome.wallet.common.model.TradeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Created on 2020/9/8 12:07 下午
 * 交易事件发送
 * @author barry
 * Description:
 */
@Component
public class TradeEventSender {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发送事件
     *
     * @param tradeRecord
     */
    public void send(TradeRecord tradeRecord) {
        applicationEventPublisher.publishEvent(new TradeEvent(tradeRecord));
    }

    public void send(TradeRecord[] tradeRecords) {
        for (TradeRecord t : tradeRecords) {
            applicationEventPublisher.publishEvent(new TradeEvent(t));
        }

    }
}
