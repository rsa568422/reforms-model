package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.ProportionalJob;

import java.math.BigDecimal;

public class ProportionalJobData extends ContractedJobData {

    public static ProportionalJob PP_JOB_PAINTWORK_PLASTIC() {
        return new ProportionalJob(InsurerData.INSURER_A, JOB_PAINTWORK_PLASTIC, new BigDecimal("5.00"));
    }

}
