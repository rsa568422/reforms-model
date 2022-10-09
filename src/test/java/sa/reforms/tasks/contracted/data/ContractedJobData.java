package sa.reforms.tasks.contracted.data;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.data.InsurerData;
import sa.reforms.entities.data.JobData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContractedJobData extends JobData {

    public static BigDecimal GET_DEFAULT_PRICE_A() {
        return new BigDecimal("20").setScale(2, RoundingMode.CEILING);
    }

    public static BigDecimal GET_DEFAULT_PRICE_B() {
        return new BigDecimal("50.50").setScale(2, RoundingMode.CEILING);
    }

    public static String GET_DEFAULT_NAME_A() {
        return InsurerData.GET_DEFAULT_NAME_A();
    }

    public static String GET_DEFAULT_NAME_B() {
        return InsurerData.GET_DEFAULT_NAME_B();
    }

    public static String GET_DEFAULT_PHONE_A() {
        return InsurerData.GET_DEFAULT_PHONE_A();
    }

    public static String GET_DEFAULT_PHONE_B() {
        return InsurerData.GET_DEFAULT_PHONE_B();
    }

    public static Insurer GET_DEFAULT_INSURER_A() {
        return InsurerData.GET_DEFAULT_INSURER_A();
    }

    public static Insurer GET_DEFAULT_INSURER_B() {
        return InsurerData.GET_DEFAULT_INSURER_B();
    }

    public static Insurer GET_DEFAULT_INSURER_A_WITH_PRONE() {
        return InsurerData.GET_DEFAULT_INSURER_A_WITH_PRONE();
    }

    public static Insurer GET_DEFAULT_INSURER_B_WITH_PRONE() {
        return InsurerData.GET_DEFAULT_INSURER_B_WITH_PRONE();
    }

}
