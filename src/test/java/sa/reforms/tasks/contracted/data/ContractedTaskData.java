package sa.reforms.tasks.contracted.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContractedTaskData {

    public static BigDecimal GET_DEFAULT_PRIZE() {
        return new BigDecimal("50.50").setScale(2, RoundingMode.CEILING);
    }

}
