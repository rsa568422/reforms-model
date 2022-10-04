package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.UniqueRankPriceJob;

public class UniqueRankPriceTableJobData extends PriceTableJobData {

    public static UniqueRankPriceJob UR_JOB_PAINTWORK_PLASTIC() {
        return new UniqueRankPriceJob(INSURER_A(), JOB_PAINTWORK_PLASTIC(), RANKS_MAP());
    }

}
