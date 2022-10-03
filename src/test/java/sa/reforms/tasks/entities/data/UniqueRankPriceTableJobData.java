package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.UniqueRankPriceTableJob;

public class UniqueRankPriceTableJobData extends PriceTableJobData {

    public static UniqueRankPriceTableJob UR_JOB_PAINTWORK_PLASTIC() {
        return new UniqueRankPriceTableJob(INSURER_A(), JOB_PAINTWORK_PLASTIC(), RANKS_MAP());
    }

}
