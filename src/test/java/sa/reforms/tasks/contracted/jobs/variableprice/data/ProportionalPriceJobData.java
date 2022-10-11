package sa.reforms.tasks.contracted.jobs.variableprice.data;

import sa.reforms.tasks.contracted.jobs.variableprice.ProportionalPriceJob;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static sa.reforms.tasks.contracted.Quantity.Unit.M;
import static sa.reforms.tasks.contracted.Quantity.Unit.M2;

public class ProportionalPriceJobData extends VariablePriceJobData {

    public static ProportionalPriceJob GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A() {
        return new ProportionalPriceJob(GET_DEFAULT_INSURER_A(), GET_DEFAULT_JOB_A(), CASE_A(M), GET_DEFAULT_UNIT_PRICE_A());
    }

    public static ProportionalPriceJob GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B() {
        return new ProportionalPriceJob(GET_DEFAULT_INSURER_B(), GET_DEFAULT_JOB_B(), CASE_B(M2), GET_DEFAULT_UNIT_PRICE_B());
    }

    public static BigDecimal GET_DEFAULT_UNIT_PRICE_A() {
        return new BigDecimal("1.5").setScale(2, RoundingMode.CEILING);
    }

    public static BigDecimal GET_DEFAULT_UNIT_PRICE_B() {
        return new BigDecimal("2.5").setScale(2, RoundingMode.CEILING);
    }

    public static BigDecimal GET_DEFAULT_PRICE_A_x(BigDecimal unitPrice) {
        return BigDecimal.valueOf(CASE_A(M).getMeasure()).multiply(unitPrice).setScale(2, RoundingMode.CEILING);
    }

    public static BigDecimal GET_DEFAULT_PRICE_B_x(BigDecimal unitPrice) {
        return BigDecimal.valueOf(CASE_B(M2).getMeasure()).multiply(unitPrice).setScale(2, RoundingMode.CEILING);
    }

}
