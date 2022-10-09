package sa.reforms.tasks.data;

import sa.reforms.entities.data.TaskData;
import sa.reforms.tasks.NoContractTask;

public class NoContractTaskData extends TaskData {

    public static String GET_DEFAULT_DESCRIPTION_A() {
        return "TASK_A";
    }

    public static String GET_DEFAULT_DESCRIPTION_B() {
        return "TASK_B";
    }

    public static NoContractTask NO_CONTRACT_TASK_A() {
        NoContractTask task = new NoContractTask(GET_DEFAULT_PRICE_A(), GET_DEFAULT_DESCRIPTION_A());
        task.setStatus(GET_DEFAULT_STATUS_A());
        return task;
    }

    public static NoContractTask NO_CONTRACT_TASK_B() {
        NoContractTask task = new NoContractTask(GET_DEFAULT_PRICE_B(), GET_DEFAULT_DESCRIPTION_B());
        task.setStatus(GET_DEFAULT_STATUS_B());
        return task;
    }

}
