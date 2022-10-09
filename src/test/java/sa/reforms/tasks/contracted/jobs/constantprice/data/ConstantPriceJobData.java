package sa.reforms.tasks.contracted.jobs.constantprice.data;

import sa.reforms.tasks.contracted.data.ContractedJobData;
import sa.reforms.tasks.contracted.jobs.constantprice.ConstantPriceJob;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConstantPriceJobData extends ContractedJobData {

    public static BigDecimal GET_DEFAULT_PRICE_A() {
        return new BigDecimal("20").setScale(2, RoundingMode.CEILING);
    }

    public static BigDecimal GET_DEFAULT_PRICE_B() {
        return new BigDecimal("50.50").setScale(2, RoundingMode.CEILING);
    }

    public static ConstantPriceJob GET_DEFAULT_CONSTANT_PRICE_JOB_A() {
        return new ConstantPriceJob(GET_DEFAULT_INSURER_A(), GET_DEFAULT_JOB_A(), GET_DEFAULT_PRICE_A());
    }

    public static ConstantPriceJob GET_DEFAULT_CONSTANT_PRICE_JOB_B() {
        return new ConstantPriceJob(GET_DEFAULT_INSURER_B(), GET_DEFAULT_JOB_B(), GET_DEFAULT_PRICE_B());
    }

}
