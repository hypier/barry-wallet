package fun.barryhome.wallet.convertor;

import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.model.TradeDo;
import org.springframework.beans.BeanUtils;

/**
 * Created on 2020/9/8 11:04 下午
 *
 * @author barry
 * Description:
 */
public class TradeConvertor {

    public static TradeDo toDto(TradeRecord tradeRecord){
        TradeDo tradeDo = TradeDo.builder().build();
        BeanUtils.copyProperties(tradeRecord, tradeDo);
        tradeDo.setWalletId(tradeRecord.getWallet().getWalletId());
        if (tradeRecord.getVersion() == null){
            tradeDo.setVersion(0L);
        }
        return tradeDo;
    }

    public static TradeRecord toEntity(TradeDo tradeDo){
        TradeRecord tradeRecord = TradeRecord.builder().build();
        BeanUtils.copyProperties(tradeDo, tradeRecord);
        tradeRecord.setWallet(Wallet.builder().walletId(tradeDo.getWalletId()).build());
        return tradeRecord;
    }
}
