package sa.reforms.tasks.contracted.jobs.data;

import sa.reforms.tasks.contracted.jobs.Job;

public class JobData {

    public static Job.Guild GET_GUILD_A() {
        return Job.Guild.PAINTWORK;
    }

    public static Job.Guild GET_GUILD_B() {
        return Job.Guild.BRICKWORK;
    }

    public static String GET_DESCRIPTION_A() {
        return "plastic";
    }

    public static String GET_DESCRIPTION_B() {
        return "plaster";
    }

    public static Job GET_JOB_A(){
        return new Job(GET_GUILD_A(), GET_DESCRIPTION_A());
    }

    public static Job GET_JOB_B(){
        return new Job(GET_GUILD_B(), GET_DESCRIPTION_B());
    }

}
