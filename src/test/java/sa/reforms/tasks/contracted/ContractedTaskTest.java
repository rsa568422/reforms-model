package sa.reforms.tasks.contracted;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static sa.reforms.tasks.contracted.data.ContractedTaskData.*;

@ExtendWith(MockitoExtension.class)
class ContractedTaskTest {

    @InjectMocks
    private ContractedTask contractedTask;

    @Mock
    private ContractedJob contractedJob;

    @Test
    void test_getPrice() {
        assertAll(
                () -> {
                    BigDecimal expected = GET_DEFAULT_PRICE_A();
                    when(this.contractedJob.getPrice()).thenReturn(GET_DEFAULT_PRICE_A());
                    BigDecimal actual = this.contractedTask.getPrice();
                    assertEquals(expected, actual);
                },
                () -> {
                    BigDecimal expected = GET_DEFAULT_PRICE_B();
                    when(this.contractedJob.getPrice()).thenReturn(GET_DEFAULT_PRICE_B());
                    BigDecimal actual = this.contractedTask.getPrice();
                    assertEquals(expected, actual);
                }
        );
    }

    @Test
    void test_getJob() {
        assertAll(
                () -> {
                    ContractedTask task = new ContractedTask(GET_DEFAULT_JOB_A());
                    assertEquals(GET_DEFAULT_TASK_A().getJob(), task.getJob());
                }
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_TASK_A(), GET_DEFAULT_TASK_A()),
                () -> assertEquals(GET_DEFAULT_TASK_B(), GET_DEFAULT_TASK_B()),
                () -> assertNotEquals(GET_DEFAULT_TASK_A(), GET_DEFAULT_TASK_B())
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_TASK_A().hashCode(), GET_DEFAULT_TASK_A().hashCode()),
                () -> assertEquals(GET_DEFAULT_TASK_B().hashCode(), GET_DEFAULT_TASK_B().hashCode()),
                () -> assertNotEquals(GET_DEFAULT_TASK_A().hashCode(), GET_DEFAULT_TASK_B().hashCode())
        );
    }

    @Test
    void test_toString() {
        when(this.contractedJob.toString()).thenReturn("[MOCK_CONTRACTED_JOB]");
        assertEquals(
                "ContractedTask(super=Task(status=PENDING), job=[MOCK_CONTRACTED_JOB])",
                this.contractedTask.toString()
        );
    }

}