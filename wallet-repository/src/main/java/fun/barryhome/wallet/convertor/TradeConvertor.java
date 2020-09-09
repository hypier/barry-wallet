package fun.barryhome.wallet.convertor;

import fun.barryhome.wallet.common.model.TradeRecord;
import fun.barryhome.wallet.common.model.Wallet;
import fun.barryhome.wallet.model.TradeDo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/9/8 11:04 下午
 *
 * @author barry
 * Description:
 */
public class TradeConvertor {

    public static TradeDo toDto(TradeRecord tradeRecord) {
        if (tradeRecord == null) {
            return null;
        }

        TradeDo tradeDo = TradeDo.builder().build();
        BeanUtils.copyProperties(tradeRecord, tradeDo);
        tradeDo.setWalletId(tradeRecord.getWallet().getWalletId());

        return tradeDo;
    }

    public static List<TradeDo> toDto(Iterable<TradeRecord> tradeRecords) {
        if (tradeRecords == null){
            return null;
        }

        List<TradeDo> list = new ArrayList<>();
        tradeRecords.forEach(t -> list.add(toDto(t)));

        return list;
    }

    public static TradeRecord toEntity(TradeDo tradeDo) {
        if (tradeDo == null) {
            return null;
        }

        TradeRecord tradeRecord = TradeRecord.builder().build();
        BeanUtils.copyProperties(tradeDo, tradeRecord);
        tradeRecord.setWallet(Wallet.builder().walletId(tradeDo.getWalletId()).build());
        return tradeRecord;
    }
}
