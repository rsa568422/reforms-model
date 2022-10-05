package sa.reforms.tasks.contradtedjobs.data;

import sa.reforms.tasks.contradtedjobs.ProportionalPriceJob;

import java.math.BigDecimal;

public class ProportionalPriceJobData extends ContractedJobData {

    public static ProportionalPriceJob PP_JOB_PAINTWORK_PLASTIC() {
        return new ProportionalPriceJob(INSURER_A(), JOB_PAINTWORK_PLASTIC(), new BigDecimal("5.00"));
    }

}
