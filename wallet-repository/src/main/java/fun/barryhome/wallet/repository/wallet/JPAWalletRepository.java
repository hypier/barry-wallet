package fun.barryhome.wallet.repository.wallet;

import fun.barryhome.wallet.model.WalletDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2020/9/8 11:01 下午
 *
 * @author barry
 * Description:
 */
public interface JPAWalletRepository extends JpaRepository<WalletDo, String> {
}
