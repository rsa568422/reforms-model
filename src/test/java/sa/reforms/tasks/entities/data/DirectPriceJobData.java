package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.DirectPriceJob;

public class DirectPriceJobData extends ContractedJobData {

    public static DirectPriceJob DP_JOB_PAINTWORK_PLASTIC() {
        return new DirectPriceJob(INSURER_A(), JOB_PAINTWORK_PLASTIC());
    }

}
