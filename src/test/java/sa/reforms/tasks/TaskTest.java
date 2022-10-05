package sa.reforms.tasks;

import sa.reforms.tasks.contradtedjobs.ContractedJob;
import sa.reforms.tasks.quatities.Quantity;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import static sa.reforms.tasks.contradtedjobs.data.DirectPriceJobData.DP_JOB_PAINTWORK_PLASTIC;
import static sa.reforms.tasks.contradtedjobs.data.ProportionalPriceJobData.PP_JOB_PAINTWORK_PLASTIC;
import static sa.reforms.tasks.quantities.data.QuantityData.*;

@ExtendWith(MockitoExtension.class)
class TaskTest {

    @InjectMocks
    private Task task;

    @Mock
    private ContractedJob contractedJob;

    @Test
    void testGetPrize() {
        Quantity caseA = CASE_A(Quantity.Unit.M);
        Quantity caseB = CASE_B(Quantity.Unit.M2);
        Quantity caseC = CASE_C(Quantity.Unit.H);

        when(this.contractedJob.valid(any())).thenReturn(true);
        when(this.contractedJob.getPrize(Optional.of(caseA.getMeasure()))).thenReturn(getExpected(caseA.getMeasure()));
        when(this.contractedJob.getPrize(Optional.of(caseB.getMeasure()))).thenReturn(getExpected(caseB.getMeasure()));
        when(this.contractedJob.getPrize(Optional.of(caseC.getMeasure()))).thenReturn(getExpected(caseC.getMeasure()));

        assertAll(
                () -> {
                    this.task.setQuantity(CASE_A(Quantity.Unit.M));
                    assertEquals(getExpected(caseA.getMeasure()), this.task.getPrice());
                },
                () -> {
                    this.task.setQuantity(CASE_B(Quantity.Unit.M2));
                    assertEquals(getExpected(caseB.getMeasure()), this.task.getPrice());
                },
                () -> {
                    this.task.setQuantity(CASE_C(Quantity.Unit.M));
                    assertEquals(getExpected(caseC.getMeasure()), this.task.getPrice());
                }
        );
    }

    @Test
    void testGetPrizeWithRealImpl() {
        this.task.setStatus(Task.TaskStatus.DONE);
        Quantity caseA = CASE_A(Quantity.Unit.M);
        Quantity caseB = CASE_B(Quantity.Unit.M2);
        Quantity caseC = CASE_C(Quantity.Unit.H);

        when(this.contractedJob.valid(any())).thenReturn(true);
        when(this.contractedJob.getPrize(Optional.of(caseA.getMeasure()))).thenReturn(getExpected(caseA.getMeasure()));
        when(this.contractedJob.getPrize(Optional.of(caseB.getMeasure()))).thenReturn(getExpected(caseB.getMeasure()));
        when(this.contractedJob.getPrize(Optional.of(caseC.getMeasure()))).thenReturn(getExpected(caseC.getMeasure()));

        assertAll(
                () -> {
                    this.task.setQuantity(CASE_A(Quantity.Unit.M));
                    assertEquals(getExpected(caseA.getMeasure()), this.task.getPrice());
                },
                () -> {
                    this.task.setQuantity(CASE_B(Quantity.Unit.M2));
                    assertEquals(getExpected(caseB.getMeasure()), this.task.getPrice());
                },
                () -> {
                    this.task.setQuantity(CASE_C(Quantity.Unit.M));
                    assertEquals(getExpected(caseC.getMeasure()), this.task.getPrice());
                }
        );
    }
    @Test
    void testDirectPriceJobWithNoEuQuantity() {
        Task taskA = new Task(DP_JOB_PAINTWORK_PLASTIC());
        Exception exception2 = assertThrows(InvalidParamsException.class,
                () -> taskA.setQuantity(CASE_A(Quantity.Unit.M)));

        assertEquals(InvalidParamsException.class, exception2.getClass());
    }

    @Test
    void testGePrizeEmpty() {
        when(this.contractedJob.valid(argThat(Optional::isEmpty))).thenReturn(false);

        Exception exception = assertThrows(InvalidParamsException.class, () -> this.task.setQuantity(null));

        assertEquals(InvalidParamsException.class, exception.getClass());
    }

    @Test
    void testOverrideDefaultMethods() {
        Task task1 = new Task(this.contractedJob);
        Task task2 = new Task(this.contractedJob);

        when(this.contractedJob.valid(any())).thenReturn(true);

        task1.setQuantity(CASE_A(Quantity.Unit.EU));
        task2.setQuantity(CASE_A(Quantity.Unit.EU));

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
        Task task2 = new Task(this.contractedJob);

        when(this.contractedJob.valid(any())).thenReturn(true);

        task1.setQuantity(CASE_A(Quantity.Unit.EU));
        task2.setQuantity(CASE_A(Quantity.Unit.EU));

        task1.setJob(DP_JOB_PAINTWORK_PLASTIC());
        task2.setJob(DP_JOB_PAINTWORK_PLASTIC());

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
    void test_setJob_unit() {
        Task task1 = new Task(PP_JOB_PAINTWORK_PLASTIC());
        task1.setQuantity(CASE_A(Quantity.Unit.M2));

        Exception exception = assertThrows(InvalidParamsException.class, () -> task1.setJob(DP_JOB_PAINTWORK_PLASTIC()));

        assertEquals(InvalidParamsException.class, exception.getClass());
    }

    private static BigDecimal getExpected(Double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.CEILING);
    }

}