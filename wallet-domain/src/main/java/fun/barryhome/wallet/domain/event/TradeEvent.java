package fun.barryhome.wallet.domain.event;

import fun.barryhome.wallet.domain.model.TradeRecord;
import fun.barryhome.wallet.domain.model.enums.TradeStatus;
import fun.barryhome.wallet.domain.model.enums.TradeType;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by heyong on 2017/2/23 12:09.
 */
@ToString
@Getter
public class TradeEvent  {

    private final TradeRecord tradeRecord;
    private final TradeStatus tradeStatus;
    private final TradeType tradeType;

    public TradeEvent(TradeRecord tradeRecord){
        this.tradeRecord = tradeRecord;
        this.tradeStatus = tradeRecord.getTradeStatus();
        this.tradeType = tradeRecord.getTradeType();
    }
}
