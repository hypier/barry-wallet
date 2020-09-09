package fun.barryhome.wallet.repository;


import fun.barryhome.wallet.common.model.TradeRecord;

/**
 * Created on 2020/9/8 5:59 下午
 *
 * @author barry
 * Description:
 */
public interface TradeRepository {
    /**
     * 查询交易
     * @param tradeNumber
     * @return
     */
    TradeRecord findByTradeNumber(String tradeNumber);

    /**
     * 保存交易
     * @param tradeRecord
     */
    TradeRecord save(TradeRecord tradeRecord);

    /**
     * 批量保存
     * @param tradeRecords
     * @return
     */
    void save(Iterable<TradeRecord> tradeRecords);
}
