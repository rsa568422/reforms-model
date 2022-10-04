package sa.reforms.tasks.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sa.reforms.exceptions.InvalidParamsException;
import sa.reforms.tasks.entities.data.DirectPriceJobData;
import sa.reforms.tasks.entities.data.QuantityData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskTest {

    @InjectMocks
    private Task task;

    @Mock
    private ContractedJob contractedJob;

    @Test
    void testGetPrize() {
        Quantity caseA = QuantityData.CASE_A(Quantity.Unit.M);
        Quantity caseB = QuantityData.CASE_B(Quantity.Unit.M2);
        Quantity caseC = QuantityData.CASE_C(Quantity.Unit.H);
        when(this.contractedJob.getPrize(Optional.of(caseA.getMeasure()))).thenReturn(getExpected(caseA.getMeasure()));
        when(this.contractedJob.getPrize(Optional.of(caseB.getMeasure()))).thenReturn(getExpected(caseB.getMeasure()));
        when(this.contractedJob.getPrize(Optional.of(caseC.getMeasure()))).thenReturn(getExpected(caseC.getMeasure()));

        assertAll(
                () -> {
                    this.task.setQuantity(QuantityData.CASE_A(Quantity.Unit.M));
                    assertEquals(getExpected(caseA.getMeasure()), this.task.getPrice());
                },
                () -> {
                    this.task.setQuantity(QuantityData.CASE_B(Quantity.Unit.M2));
                    assertEquals(getExpected(caseB.getMeasure()), this.task.getPrice());
                },
                () -> {
                    this.task.setQuantity(QuantityData.CASE_C(Quantity.Unit.M));
                    assertEquals(getExpected(caseC.getMeasure()), this.task.getPrice());
                }
        );
    }

    @Test
    void testGetPrizeWithRealImpl() {
        this.task.setStatus(Task.TaskStatus.DONE);
        Quantity caseA = QuantityData.CASE_A(Quantity.Unit.M);
        Quantity caseB = QuantityData.CASE_B(Quantity.Unit.M2);
        Quantity caseC = QuantityData.CASE_C(Quantity.Unit.H);
        when(this.contractedJob.getPrize(Optional.of(caseA.getMeasure()))).thenReturn(getExpected(caseA.getMeasure()));
        when(this.contractedJob.getPrize(Optional.of(caseB.getMeasure()))).thenReturn(getExpected(caseB.getMeasure()));
        when(this.contractedJob.getPrize(Optional.of(caseC.getMeasure()))).thenReturn(getExpected(caseC.getMeasure()));

        assertAll(
                () -> {
                    this.task.setQuantity(QuantityData.CASE_A(Quantity.Unit.M));
                    assertEquals(getExpected(caseA.getMeasure()), this.task.getPrice());
                },
                () -> {
                    this.task.setQuantity(QuantityData.CASE_B(Quantity.Unit.M2));
                    assertEquals(getExpected(caseB.getMeasure()), this.task.getPrice());
                },
                () -> {
                    this.task.setQuantity(QuantityData.CASE_C(Quantity.Unit.M));
                    assertEquals(getExpected(caseC.getMeasure()), this.task.getPrice());
                }
        );
    }
    @Test
    void testDirectPriceJobWithNoEuQuantity() {
        Exception exception1 = assertThrows(InvalidParamsException.class,
                () -> new Task(DirectPriceJobData.DP_JOB_PAINTWORK_PLASTIC(), QuantityData.CASE_A(Quantity.Unit.M)));

        Task taskA = new Task(DirectPriceJobData.DP_JOB_PAINTWORK_PLASTIC(), QuantityData.CASE_A(Quantity.Unit.EU));
        Exception exception2 = assertThrows(InvalidParamsException.class,
                () -> taskA.setQuantity(QuantityData.CASE_A(Quantity.Unit.M)));

        assertEquals(InvalidParamsException.class, exception1.getClass());
        assertEquals(InvalidParamsException.class, exception2.getClass());
    }

    @Test
    void testGePrizeNegative() {
        Quantity negative = QuantityData.NEGATIVE(Quantity.Unit.EU);
        when(this.contractedJob.getPrize(Optional.of(negative.getMeasure()))).thenThrow(InvalidParamsException.class);

        this.task.setQuantity(negative);
        Exception exception = assertThrows(InvalidParamsException.class, () -> this.task.getPrice());

        assertEquals(InvalidParamsException.class, exception.getClass());
        verify(this.contractedJob).getPrize(Optional.of(negative.getMeasure()));
    }

    @Test
    void testGePrizeEmpty() {
        Optional<Quantity> empty = QuantityData.EMPTY();
        when(this.contractedJob.getPrize(empty.map(Quantity::getMeasure))).thenThrow(InvalidParamsException.class);

        this.task.setQuantity(null);
        Exception exception = assertThrows(InvalidParamsException.class, () -> this.task.getPrice());

        assertEquals(InvalidParamsException.class, exception.getClass());
        verify(this.contractedJob).getPrize(empty.map(Quantity::getMeasure));
    }

    @Test
    void testOverrideDefaultMethods() {
        Task task1 = new Task(this.contractedJob);
        Task task2 = new Task(this.contractedJob, null);

        assertEquals(task1, task2);
        assertEquals(task1.toString(), task2.toString());
        assertEquals(task1.hashCode(), task2.hashCode());

        assertEquals(task2, task1);
        assertEquals(task2.toString(), task1.toString());
        assertEquals(task2.hashCode(), task1.hashCode());

        assertEquals(task1, task1);
        assertEquals(task1.toString(), task1.toString());
        assertEquals(task1.hashCode(), task1.hashCode());

        assertEquals(task2, task2);
        assertEquals(task2.toString(), task2.toString());
        assertEquals(task2.hashCode(), task2.hashCode());

        assertEquals(task1.getStatus(), task1.getStatus());
        assertEquals(task1.getStatus(), task2.getStatus());
        assertEquals(task2.getStatus(), task1.getStatus());
        assertEquals(task2.getStatus(), task2.getStatus());
    }

    @Test
    void testOverrideDefaultMethodsWithRealImpl() {
        Task task1 = new Task(this.contractedJob);
        Task task2 = new Task(this.contractedJob, null);

        task1.setJob(DirectPriceJobData.DP_JOB_PAINTWORK_PLASTIC());
        task2.setJob(DirectPriceJobData.DP_JOB_PAINTWORK_PLASTIC());

        assertEquals(task1, task2);
        assertEquals(task1.toString(), task2.toString());
        assertEquals(task1.hashCode(), task2.hashCode());

        assertEquals(task2, task1);
        assertEquals(task2.toString(), task1.toString());
        assertEquals(task2.hashCode(), task1.hashCode());

        assertEquals(task1, task1);
        assertEquals(task1.toString(), task1.toString());
        assertEquals(task1.hashCode(), task1.hashCode());

        assertEquals(task2, task2);
        assertEquals(task2.toString(), task2.toString());
        assertEquals(task2.hashCode(), task2.hashCode());

        assertEquals(task1.getStatus(), task1.getStatus());
        assertEquals(task1.getStatus(), task2.getStatus());
        assertEquals(task2.getStatus(), task1.getStatus());
        assertEquals(task2.getStatus(), task2.getStatus());
    }

    private static BigDecimal getExpected(Double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.CEILING);
    }

}