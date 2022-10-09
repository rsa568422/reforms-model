package sa.reforms.tasks.contracted.jobs.variableprice.data;

import sa.reforms.tasks.contracted.jobs.variableprice.DirectPriceJob;

public class DirectPriceJobData extends VariablePriceJobData {

    public static DirectPriceJob GET_DEFAULT_DIRECT_PRICE_JOB_A() {
        return new DirectPriceJob(GET_DEFAULT_INSURER_A(), GET_DEFAULT_JOB_A(), CASE_EU_A());
    }

    public static DirectPriceJob GET_DEFAULT_DIRECT_PRICE_JOB_B() {
        return new DirectPriceJob(GET_DEFAULT_INSURER_B(), GET_DEFAULT_JOB_B(), CASE_EU_B());
    }

}
