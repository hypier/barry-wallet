package fun.barryhome.wallet.application;

import fun.barryhome.wallet.domain.model.TradeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created on 2020/9/8 11:42 上午
 *
 * @author barry
 * Description:
 */
@Component
public class TradeEventProcessor {

    @EventListener(condition = "# tradeEvent.tradeStatus.name() == 'SUCCEED'")
    public void TradeSucceed(TradeEvent tradeEvent)  {
       System.out.println(tradeEvent);
    }
}
