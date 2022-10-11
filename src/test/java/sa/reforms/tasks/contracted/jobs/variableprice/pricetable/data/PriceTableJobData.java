package sa.reforms.tasks.contracted.jobs.variableprice.pricetable.data;

import sa.reforms.tasks.contracted.jobs.variableprice.data.VariablePriceJobData;
import sa.reforms.tasks.contracted.jobs.variableprice.pricetable.Range;

public class PriceTableJobData extends VariablePriceJobData {

    public static Range RANK_1() {
        return new Range(0D, 5D);
    }

    public static Range RANK_2() {
        return new Range(5D, 20D);
    }

    public static Range RANK_3() {
        return new Range(20D, Double.MAX_VALUE);
    }

}
