package sa.reforms.tasks.entities.data;

import sa.reforms.entities.Job;

import sa.reforms.enums.Guild;

public class JobData {

    public static Job JOB_PAINTWORK_PLASTIC(){
        return new Job(Guild.PAINTWORK, "plastic");
    }

}
