package sa.reforms.entities.data;

import sa.reforms.entities.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaskData {

    public static Task.Status GET_DEFAULT_STATUS_A() {
        return Task.Status.PENDING;
    }

    public static Task.Status GET_DEFAULT_STATUS_B() {
        return Task.Status.IN_PROGRESS;
    }

    public static BigDecimal GET_DEFAULT_PRICE_A() {
        return new BigDecimal("20").setScale(2, RoundingMode.CEILING);
    }

    public static BigDecimal GET_DEFAULT_PRICE_B() {
        return new BigDecimal("50.50").setScale(2, RoundingMode.CEILING);
    }

}
