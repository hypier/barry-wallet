# 企业中台化落地：从战略分析到战术实践及架构演进过程

标签（空格分隔）： 中台 架构设计

---

[toc]

> 谈及**中台**，大都雾里看花，抱有一份敬畏之心，恐误导众人。但愿通过自己的思考与一同思考实践的朋友们一些启发，让中台建设得到它应有的收益，总结出更多的成功经验。

最近接触到一些公司说在做`中台`，交流之后大都是应该使用什么样的技术，如何解决数据一致性问题等。其中公司发展时间有长有短，有十几二十年的传统企业，也有三四个月才起步的创业团队。交流下来心中不免有些担忧，不太清楚所谓中台是追求一种技术实现还是一个流行噱头。
经过较长时间的思考、学习和实践，我发现了解得越多越不敢讲自己做的称之为`中台`。它是一种企业级业务构架设计方法论，如何做好还得**从企业的愿景发出分析企业发展目标，合理利用资源对系统架构进行持续性的演进**。

每个企业的愿景和目标都不一样，对信息化诉求不一样，所构建出的`中台系统`自然也不一样，但是对企业经营有有效的提升是显尔易见的，所以设定好可量化的指标尤为重要。

## 背景

本文通过一个简单的例子来讲述如何进行中台化落地，企业实际过程远比这复杂得多。这是一家新零售企业，通过数字化转型获得新的业务增长点。“数字钱包”是公司的一个重点产品，项目特点是和其它业务相对独立且前端功能多样化，经商量解决引入中台化的思想来规划这个项目。

## 一、 战略分析

架构设计就是为未来而设计的，首先要清楚这个产品的愿景是什么，做这个事是目的是什么，要达成什么目标等。
战略分析是至上而下的，经历公司的发展历史，了解公司现在的发展状况，清楚公司未来的发展方向。此过程需要公司领导及各业务负责人参与沟通，达成一致意见。

### 1. 业务愿景分析

* 增加企业经营效益：通过钱包预付款功能，增加资金沉淀，增强资金利用效率，同时降低顾客的促销成本
* 增强顾客粘性：增加顾客复购机会，增强顾客用户体验

### 2. 业务模式分析

此产品主要用于公司各种类型资金交易的解决方案。目前包括：

* 会员钱包功能：与会员系统打通，与会员系统强绑定，实现会员专属虚拟账户功能
* 消费卡功能：主要用于线下实体卡业务，会员和非会员均可使用
    * 记名：用于会员
    * 不记名：用于赠送、福利卡
    * 亲属，用于家庭共用卡
* 积分功能：用于购物返利，活动奖励等

### 3. 业务场景分析

![业务构架图](https://oscimg.oschina.net/oscnet/up-05b7f45eacfb85802b08ae2229dcda2baad.png)

主要为线上线下结合，不同的终端不一样的使用场景

* 线上微信、APP实现可信任的快速支付服务
* 线下门店、合作商户实现凭密码安全消费服务

### 4. 业务功能分析
不同的业务模式和业务场景有不一样的业务功能，这里需要去切分和隔离

* 会员钱包：充值、消费等
* 消费卡：充值、消费、密码、转账、挂失、开卡、销户等
* 积分：赠送、消费、兑换等

### 5. 系统建设目标

* 为满足销售任务的达成需要快速响应前端各业务场景的需求变化
* 系统需要易于复制业务模式的创新尝试

## 二、 战术设计

战术设计就是根据战略目标制定具体的作战步骤。

* 作战步骤需要紧贴公司战略步骤制定，根据当下的实际的资源情况进行合理的配置
* 战略目标较为宽大，且较为耗时耗力，需要先选择一个较为容易实现的目标，取得阶段性的成果
* 设定目标后还需要设计一个可量化的指标，得以评估中台化改造的收益，是否带来正向结果

本项目所处在钱包功能急将上线解决业务功能闭环的阶段，需要快速出成果故在后续系统结构不做大的改变的情况下，考虑到线下操作都是由公司员工完成，实施风险相对可控，故先完成线下基础版本。
由于第一阶段功能较简单，架构关键点在于如何保持系统的灵活扩展性，故前期的架构设计是重点，而后的功能实现就能顺理成章了。
可量化指标是实现新老功能的迁移，实现多端操作的整合。

## 三、 战术落地

### 1. 逻辑结构分析

![领域结构图](https://oscimg.oschina.net/oscnet/up-aae9285275bfb392a45878dd8174ea7a6c0.png)

#### （1）领域驱动设计
根据战略愿景的诉求，系统设计上要求保持灵活性，易于功能扩展和业务形态快速复制性。
我们采用DDD(`领域驱动设计`)思想来分析业务：

* 将系统中的**钱包账户**和**交易流水**划分为两个`领域实体`，形成`聚合`
* 使用`命令模式`驱动业务操作，以交易流水实体为`聚合根`驱动钱包账户实体的变化
* 使用`领域事件`来联动系统内与系统外相关功能

此阶段可以以`事件风暴`的形式，与领域专家一起使用`通用语言`来展开讨论，以达到业务、技术认识一致性

#### （2）抽象能力
系统需要保持满足的复用能力，可以方便快速的迭代出新的业务功能、业务规则和业务场景。故需要识别出这其中的业务共性和可变性，通过多种程序设计模式保持系统的灵活性

![业务抽象逻辑](https://oscimg.oschina.net/oscnet/up-0f1539ba9a273114870179d0e95a1342eaa.png)

这个项目的业务共性就是所有的业务操作都是可以以**交易流水**为驱动，引发一个业务变化
可变性就是不同的业务变化，如金额增加、金额减少、账户锁定、密码变动等
可变的内容抽象为`业务行为`和`业务规则`，不可变的就是`交易处理`、`交易完成`和`交易事件发布`


#### （3）系统扩展性
![系统结构图](https://oscimg.oschina.net/oscnet/up-514667711360a511a94ab3a97744ae0b56e.png)
这里主要指系统间的扩展性。需要定义好相互通讯的协议和标准，通过定义好的流程将数字钱包系统与其它系统融合成一个整体。

### 2. 逻辑结构设计

![程序结构图](https://oscimg.oschina.net/oscnet/up-417eaca013ba9ea5c3c8532aad6675523f7.png)

上图为系统架构的核心逻辑，主要有3大部分组成

#### （1）WalletService 核心交易服务接口

所有交易操作的执行器

```java
public interface WalletService {
    void done();
}
```

> **DefaultService** 默认的抽象类，主要实现`CheckPolicy`和`Behavior`接口的主线流程调用

**现实类调用：**
```java
public class ConsumeService extends DefaultService {
    public ConsumeService(Wallet wallet, BigDecimal tradeAmount) {
        super(TradeRecord.builder().wallet(wallet).tradeAmount(tradeAmount).build());
    }
}

```

#### （2）Behavior 交易行为接口
```java
public interface Behavior {
    void doAction();
    InOutFlag getInOutFlag();
}

public class CreditBehavior extends DefaultBehavior {
    private final BigDecimal tradeAmount;

    public CreditBehavior(Wallet wallet, BigDecimal tradeAmount) {
        super(wallet);
        this.tradeAmount = tradeAmount;
    }

    @Override
    public void doAction() {
        super.doAction();
        wallet.setBalance(wallet.getBalance().add(tradeAmount));
    }

    @Override
    public InOutFlag getInOutFlag() {
        return InOutFlag.IN;
    }
}
```

根据设计方案将所有钱包账户操作都定义为行为，此处实现的是具体的账户操作逻辑，实现类继承至抽象类进行简单的封闭。
`getInOutFlag()`是对行为产生的资金进出结果的配置

#### （3）CheckPolicy 交易规则接口
```java
public interface CheckPolicy {
    void check();
}

public class NoOverdraftAllowed implements CheckPolicy {
    private final Wallet wallet;
    private final BigDecimal tradeAmount;

    public NoOverdraftAllowed(Wallet wallet, BigDecimal tradeAmount) {
        this.wallet = wallet;
        this.tradeAmount = tradeAmount;
    }

    @Override
    public void check() {
        if (wallet.getBalance().compareTo(tradeAmount) < 0){
            throw new BizException("余额不足");
        }
    }
}
```
实现类用于判断相关操作是否存在余额不足（透支）情况，如果有则中止执行

### 3. 设计模式的运用

#### （1） 模板模式
``` java
public abstract class DefaultService implements WalletService {

    protected abstract static class TradeConfig {
        public abstract TradeType tradeType();
        public abstract Behavior behavior();
        public abstract List<CheckPolicy> checkPolicies();
    }

    protected abstract TradeConfig tradeConfig();

    @Override
    public void done() {
        check();
        tradeConfig().behavior().doAction();
        tradeRecord.setBalance(tradeRecord.getWallet().getBalance());
        tradeRecord.setTradeStatus(TradeStatus.SUCCEED);
    }
}

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
在主交易流程中，将共有的流程放在`done()`中执行，将可变的部分抽象成配置模板供现实类现实


**模板模式的优点：**
> - 扩展性好，对不变的代码进行封装，对可变的进行扩展；
- 可维护性好，因为将公共代码进行了提取，使用的时候直接调用即可；
- 现实类在有限的空间扩展，不影响主流程的实现；

#### （2） 策略模式

```java
public class RechargeRollbackService extends DefaultService {
    private final TradeRecord sourceTrade;

    public RechargeRollbackService(TradeRecord sourceTrade) {
        // ...
    }

    @Override
    protected DefaultService.TradeConfig tradeConfig() {
        return new TradeConfig() {
            @Override
            public TradeType tradeType() {
                return TradeType.RECHARGE_ROLLBACK;
            }

            @Override
            public Behavior behavior() {
                return new DebitBehavior(getWallet(), getTradeAmount());
            }

            @Override
            public List<CheckPolicy> checkPolicies() {
                return CheckPolicyBuilder.builder()
                        .add(new NoRechargeTypeAllowed(sourceTrade))
                        .add(new NoTimeoutAllowed(sourceTrade))
                        .add(new NoOverdraftAllowed(getWallet(), getTradeAmount()))
                        .add(new NoAvailableStatusAllowed(getWallet()))
                        .build();
            }
        };
    }
}

```
在交易行为和交易规则的设计中使用了策略模式，可根据不同业务操作设计不同的策略

**策略模式的优点**
> - 扩展性好，可以在不修改对象结构的情况下，为新的算法进行添加新的类进行实现；
- 灵活性好，可以对算法进行自由切换；
- 结构清晰，代码可读性高；



#### （3） 组合模式

```java
public abstract class DefaultTowPCService extends DefaultService implements TwoPCWalletService {

    public DefaultTowPCService(TradeRecord tradeRecord) {
        super(tradeRecord);
    }

    @Override
    public void process() {
        check();
        getTradeRecord().setTradeStatus(TradeStatus.PROCESSING);
    }
}

public class RechargeService extends DefaultTowPCService {
    public RechargeService(Wallet wallet, BigDecimal tradeAmount){
        super(TradeRecord.builder().wallet(wallet).tradeAmount(tradeAmount).build());
    }
}
```

**TwoPCWalletService**，**DefaultTowPCService** 用于在原有接口基础上扩展的二阶段提交功能，此处为了保持**DefaultService**功能的单一性，并没有在原有类上进行功能扩展，而是使用`组合模式`进行功能扩展
此现实类就现实了`WalletService`和`TwoPCWalletService`两个接口

**策略模式的优点**
> - 扩展性好，可以在不修改对象结构的情况下，为新的功能增加新的现实；
- 变动性小，不需要现实的类，不修改代码

## 四、 系统架构演进

### 1. 结构说明
![程序层次结构图](https://oscimg.oschina.net/oscnet/up-cc29a7bd957c81d2f0c48a9962f375cfd82.png)

- **wallet-common**为内部各层的共用实体对象
- **wallet-domain**为抽象的业务基础逻辑与规则，一般不具体直接业务场景支持。需要与**wallet-service**结合实现完成的业务逻辑。在团队开发层面，这一个层次的独立可以有效控制基础规则的代码稳定性
- **wallet-repository**专注数据对象的持久化，与业务逻辑进行隔离
- **wallet-service**为符合业务场景的业务功能实现，主要依靠**wallet-domain**与**wallet-repository**的相互组合来完成
- **wallet-client**用于对外的前端接口层
- **wallet-provider**用于对内部微服务的接口层

### 2. 第一阶段 单一结构模式
![单一运行结构图](https://oscimg.oschina.net/oscnet/up-b0f23aae769134269f1b1c10c8991aeed01.png)

这一阶段，业务模式单一，业务功能单一，业务量较少，开发人员也较少，将所有模块打包运行在一个jvm中

### 3. 第二阶段 多业务结构模式
![多业务运行结构图](https://oscimg.oschina.net/oscnet/up-f12755923a03b863f7f61a2414f6242651c.png)

这一阶段，随着业务模式和业务量的增加，模式间的业务功能也不相同或有互斥性，单一结构已不能满足，故将基础模块封闭为`SDK`，每种业务模式单独一套系统独立维护

### 4. 第三阶段 业务中台化
![业务中台结构图](https://oscimg.oschina.net/oscnet/up-73ab1f8ff0706ca9421be4a560c7da1da98.png)

随着业务模式、业务量、业务功能需求持续增加。由于各业务线独立运营，导致SDK版本不一致，增加了维护成本。多团队维护架构导致功能重复且实现过程参差不齐，带来一定维护成本且系统间无法实现复用，同时每条业务线独立运营也带来用人成本的增加。
这一阶段的目标是将大部分的共用功能下沉形成标准化逻辑，统一维护版本，减少人力成本，故架构演进采用中台化的思想

- 业务能力(**wallet-domain**)：将业务高度抽象形成一个一个基础能力
- 业务域(**wallet-service**)：将业务能力根据业务场景进行组合编排形成功能域
- 持久层(**wallet-repository**)：根据不同的情况，将数据持久化到中台或前台
- 前台触点(**wallet-client**)：根据不同的场景定制不用的前台API

## 五、综述
本文通过一个较简单的例子讲述中台架构演进的过程，实际场景远比此复杂。但最重要的不是最终的系统架构，而是对系统演进的思考和实施过程，因为中台的形态也是随时间不断变化的。

## 六、源代码
文中代码由于篇幅原因有一定省略并不是完整逻辑，如有兴趣请Fork源代码
[https://gitee.com/hypier/barry-wallet](https://gitee.com/hypier/barry-wallet)

## 七、请关注我的公众号
 ![请关注我的公众号](https://oscimg.oschina.net/oscnet/up-8969dabd3beeba071b59e61139a2bb8b22f.JPEG)