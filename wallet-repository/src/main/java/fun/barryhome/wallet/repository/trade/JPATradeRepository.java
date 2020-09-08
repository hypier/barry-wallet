package fun.barryhome.wallet.repository.trade;

import fun.barryhome.wallet.model.TradeDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2020/9/8 11:00 下午
 *
 * @author barry
 * Description:
 */
public interface JPATradeRepository extends JpaRepository<TradeDo, String> {
}
