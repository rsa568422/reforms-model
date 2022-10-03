package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.ContractedJob;
import sa.reforms.tasks.entities.Quantity;
import sa.reforms.tasks.entities.Task;

import java.util.Set;
import java.util.stream.Collectors;

public class TaskData {

    public static Set<Task> TASKS(ContractedJob job, Quantity.Unit unit) {
        return initTasks(job, unit);
    }

    private static Set<Task> initTasks(ContractedJob job, Quantity.Unit unit) {
        return QuantityData
                .QUANTITIES(unit)
                .stream()
                .map(quantity -> new Task(job, quantity))
                .collect(Collectors.toSet());
    }

}
