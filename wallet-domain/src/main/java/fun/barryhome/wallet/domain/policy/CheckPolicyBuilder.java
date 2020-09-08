package fun.barryhome.wallet.domain.policy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/9/8 8:43 上午
 *
 * @author barry
 * Description:
 */
public class CheckPolicyBuilder {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<CheckPolicy> checkPolicies;

        public Builder add(CheckPolicy checkPolicy) {
            if (checkPolicies == null) {
                checkPolicies = new ArrayList<>();
            }

            checkPolicies.add(checkPolicy);

            return this;
        }

        public List<CheckPolicy> build() {
            return checkPolicies;
        }
    }
}
