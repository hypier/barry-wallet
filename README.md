# 数字钱包系统

> 系统主要功能为账户资金交易，可用于会员虚拟钱包，实体消费卡，积分等有交易需求的系统

## 介绍
系统设计灵活易于扩展，采用多种设计模式，可简单适配不同业务场景，[点击详情介绍](doc/architecture.md)

![程序结构图](https://oscimg.oschina.net/oscnet/up-417eaca013ba9ea5c3c8532aad6675523f7.png)

## 特性

* 交易方式 `WalletService`：简单实现多种交易方式需求
* 交易行为 `Behavior`：用行为定义交易后产生的结果变化，如增加/减少金额，属性变化等
* 交易策略 `CheckPolicy`：不同的策略组合实现不同的交易行为需求

## 使用指南

### 1. 增加交易方式

在**wallet-service**项目的`service`中增加现实类

1. 继承`DefaultService`抽象类
2. 在构造方法中构建交易对象`TradeRecord`
3. 在`tradeType()`中配置交易类型
4. 在`behavior()`中创建交易行为对象
5. 在`checkPolicies()`中组合该交易类型的交易规则策略

```java
public class LockService extends DefaultService {

    public LockService(Wallet wallet) {
        super(TradeRecord.builder().wallet(wallet).build());
    }

    @Override
    protected TradeConfig tradeConfig() {
        return new TradeConfig() {
            @Override
            public TradeType tradeType() {
                return TradeType.LOCK;
            }

            @Override
            public Behavior behavior() {
                return new LockBehavior(getWallet());
            }

            @Override
            public List<CheckPolicy> checkPolicies() {
                return CheckPolicyBuilder.builder()
                        .add(new NoAvailableStatusAllowed(getWallet()))
                        .build();
            }
        };
    }
}

```

### 2. 增加交易行为

在**wallet-domain**项目的`behavior`中增加行为现实类

1. 继承`DefaultBehavior`抽象类
2. 重写`doAction()`，增加操作逻辑
3. 设置`getInOutFlag()`资金进出状态

```java
public class LockBehavior extends DefaultBehavior {

    public LockBehavior(Wallet wallet) {
        super(wallet);
    }

    /**
     * 执行
     */
    @Override
    public void doAction() {
        super.doAction();
        wallet.setWalletStatus(WalletStatus.LOCKED);
    }

    /**
     * 进出状态
     *
     * @return
     */
    @Override
    public InOutFlag getInOutFlag() {
        return InOutFlag.NONE;
    }
}
```

### 3. 增加交易规则策略

在**wallet-domain**项目的`policy`中增加行为现实类

1. 实现`CheckPolicy`接口
2. 在`check()`中设置具体业务规则

```java
public class NoAvailableStatusAllowed implements CheckPolicy {

    /**
     * 钱包
     */
    private final Wallet wallet;

    public NoAvailableStatusAllowed(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * 检查
     *
     */
    @Override
    public void check() {

        if (!WalletStatus.AVAILABLE.equals(wallet.getWalletStatus())){
            throw new BizException("钱包状态不可用");
        }
    }
}

```

## License 授权协议

这个项目 MIT 协议， 请点击 [LICENSE.md](LICENSE.md) 了解更多细节。