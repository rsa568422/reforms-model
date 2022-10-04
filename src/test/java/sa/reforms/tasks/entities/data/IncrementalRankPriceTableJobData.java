package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.IncrementalRankPriceTableJob;

public class IncrementalRankPriceTableJobData extends PriceTableJobData {

    public static IncrementalRankPriceTableJob IR_JOB_PAINTWORK_PLASTIC() {
        return new IncrementalRankPriceTableJob(INSURER_A(), JOB_PAINTWORK_PLASTIC(), RANKS_MAP());
    }

}
