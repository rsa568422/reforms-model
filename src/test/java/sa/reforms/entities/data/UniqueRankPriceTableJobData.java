package sa.reforms.entities.data;

import sa.reforms.entities.UniqueRankPriceTableJob;

public class UniqueRankPriceTableJobData extends PriceTableJobData {

    public static UniqueRankPriceTableJob UR_JOB_PAINTWORK_PLASTIC() {
        return new UniqueRankPriceTableJob(InsurerData.INSURER_A, JOB_PAINTWORK_PLASTIC, RANKS_MAP());
    }

}
