package sa.reforms.tasks.contracted.data;

import sa.reforms.insurers.Insurer;

import sa.reforms.insurers.data.InsurerData;
import sa.reforms.tasks.contracted.jobs.data.JobData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContractedJobData extends JobData {

    public static BigDecimal GET_DEFAULT_PRICE_A() {
        return new BigDecimal("20").setScale(2, RoundingMode.CEILING);
    }

    public static BigDecimal GET_DEFAULT_PRICE_B() {
        return new BigDecimal("50.50").setScale(2, RoundingMode.CEILING);
    }

    public static Insurer GET_DEFAULT_INSURER_A() {
        return InsurerData.GET_INSURER_A();
    }

    public static Insurer GET_DEFAULT_INSURER_B() {
        return InsurerData.GET_INSURER_B();
    }

}
