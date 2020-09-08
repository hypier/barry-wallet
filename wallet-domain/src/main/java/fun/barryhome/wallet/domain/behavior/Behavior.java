package fun.barryhome.wallet.domain.behavior;

import fun.barryhome.wallet.common.enums.InOutFlag;

/**
 * Created on 2020/9/7 9:57 上午
 *
 * @author barry
 * Description:
 */
public interface Behavior {
    /**
     * 执行
     */
    void doAction();

    /**
     * 进出状态
     * @return
     */
    InOutFlag getInOutFlag();
}
