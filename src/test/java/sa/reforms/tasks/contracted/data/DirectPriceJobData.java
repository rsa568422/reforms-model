package sa.reforms.tasks.contracted.data;

import sa.reforms.tasks.contracted.jobs.DirectPriceJob;

public class DirectPriceJobData extends ContractedJobData {

    public static DirectPriceJob DP_JOB_PAINTWORK_PLASTIC() {
        return new DirectPriceJob(INSURER_A(), JOB_PAINTWORK_PLASTIC());
    }

}
