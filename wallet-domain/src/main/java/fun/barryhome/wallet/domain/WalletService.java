package fun.barryhome.wallet.domain;

import org.springframework.context.ApplicationEventPublisher;

/**
 * Created on 2020/9/7 11:40 上午
 *
 * @author barry
 * Description:
 */
public interface WalletService {

    /**
     * 执行
     */
    void done();

    /**
     * 发送事件
     * @param applicationEventPublisher
     */
    void sendEvent(ApplicationEventPublisher applicationEventPublisher);
}
