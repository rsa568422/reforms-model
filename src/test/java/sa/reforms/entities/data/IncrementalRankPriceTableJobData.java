package sa.reforms.entities.data;

import sa.reforms.entities.IncrementalRankPriceTableJob;

public class IncrementalRankPriceTableJobData extends PriceTableJobData {

    public static IncrementalRankPriceTableJob PT_JOB_PAINTWORK_PLASTIC() {
        return new IncrementalRankPriceTableJob(InsurerData.INSURER_A, JOB_PAINTWORK_PLASTIC, RANKS_MAP());
    }

}
