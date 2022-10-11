package sa.reforms.tasks.contracted.jobs.variableprice.data;

import sa.reforms.tasks.contracted.Quantity;

import sa.reforms.tasks.contracted.data.ContractedJobData;
import sa.reforms.tasks.contracted.data.QuantityData;

public class VariablePriceJobData extends ContractedJobData {

    public static Quantity CASE_A(Quantity.Unit unit) {
        return QuantityData.CASE_A(unit);
    }

    public static Quantity CASE_B(Quantity.Unit unit) {
        return QuantityData.CASE_B(unit);
    }

    public static Quantity CASE_C(Quantity.Unit unit) {
        return QuantityData.CASE_C(unit);
    }

    public static Quantity CASE_EU_A() {
        return QuantityData.CASE_EU_A();
    }

    public static Quantity CASE_EU_B() {
        return QuantityData.CASE_EU_B();
    }

}
