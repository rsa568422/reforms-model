package sa.reforms.entities.data;

import sa.reforms.entities.Job;

public class JobData {

    public static Job.Guild GET_DEFAULT_GUILD_A() {
        return Job.Guild.PAINTWORK;
    }

    public static Job.Guild GET_DEFAULT_GUILD_B() {
        return Job.Guild.BRICKWORK;
    }

    public static String GET_DEFAULT_DESCRIPTION_A() {
        return "plastic";
    }

    public static String GET_DEFAULT_DESCRIPTION_B() {
        return "plaster";
    }

    public static Job GET_DEFAULT_JOB_A(){
        return new Job(GET_DEFAULT_GUILD_A(), GET_DEFAULT_DESCRIPTION_A());
    }

    public static Job GET_DEFAULT_JOB_B(){
        return new Job(GET_DEFAULT_GUILD_B(), GET_DEFAULT_DESCRIPTION_B());
    }

}
