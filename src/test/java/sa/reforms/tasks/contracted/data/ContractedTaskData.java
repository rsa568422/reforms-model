package sa.reforms.tasks.contracted.data;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.data.InsurerData;
import sa.reforms.entities.data.TaskData;
import sa.reforms.tasks.contracted.ContractedJob;
import sa.reforms.tasks.contracted.ContractedTask;

import static sa.reforms.tasks.contracted.jobs.constantprice.data.ConstantPriceJobData.GET_DEFAULT_CONSTANT_PRICE_JOB_A;
import static sa.reforms.tasks.contracted.jobs.variableprice.data.ProportionalPriceJobData.GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A;

public class ContractedTaskData extends TaskData {

    public static ContractedTask GET_DEFAULT_TASK_A() {
        return new ContractedTask(GET_DEFAULT_JOB_A());
    }

    public static ContractedTask GET_DEFAULT_TASK_B() {
        return new ContractedTask(GET_DEFAULT_JOB_B());
    }

    public static ContractedJob GET_DEFAULT_JOB_A() {
        return GET_DEFAULT_CONSTANT_PRICE_JOB_A();
    }

    public static ContractedJob GET_DEFAULT_JOB_B() {
        return GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A();
    }

    public static String GET_DEFAULT_NAME_A() {
        return InsurerData.GET_DEFAULT_NAME_A();
    }

    public static String GET_DEFAULT_NAME_B() {
        return InsurerData.GET_DEFAULT_NAME_B();
    }

    public static String GET_DEFAULT_PHONE_A() {
        return InsurerData.GET_DEFAULT_PHONE_A();
    }

    public static String GET_DEFAULT_PHONE_B() {
        return InsurerData.GET_DEFAULT_PHONE_B();
    }

    public static Insurer GET_DEFAULT_INSURER_A() {
        return InsurerData.GET_DEFAULT_INSURER_A();
    }

    public static Insurer GET_DEFAULT_INSURER_B() {
        return InsurerData.GET_DEFAULT_INSURER_B();
    }

    public static Insurer GET_DEFAULT_INSURER_A_WITH_PRONE() {
        return InsurerData.GET_DEFAULT_INSURER_A_WITH_PHONE();
    }

    public static Insurer GET_DEFAULT_INSURER_B_WITH_PRONE() {
        return InsurerData.GET_DEFAULT_INSURER_B_WITH_PHONE();
    }

}
