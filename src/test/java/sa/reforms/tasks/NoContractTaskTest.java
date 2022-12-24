package sa.reforms.tasks;

import org.junit.jupiter.api.Test;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.data.NoContractTaskData.*;

class NoContractTaskTest {

    @Test
    void test_constructor() {
        assertAll(
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new NoContractTask(new BigDecimal("-5"), GET_DEFAULT_DESCRIPTION_A()));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                },
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new NoContractTask(BigDecimal.ZERO, GET_DEFAULT_DESCRIPTION_B()));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                },
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new NoContractTask(GET_PRICE_B(), ""));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                },
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new NoContractTask(GET_PRICE_A(), "   "));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                },
                () -> assertDoesNotThrow(() -> new NoContractTask(GET_PRICE_B(), GET_DEFAULT_DESCRIPTION_B()))
        );
    }

    @Test
    void test_getPrice() {
        assertAll(
                () -> assertEquals(GET_PRICE_A(), NO_CONTRACT_TASK_A().getPrice()),
                () -> assertEquals(GET_PRICE_B(), NO_CONTRACT_TASK_B().getPrice())
        );
    }

    @Test
    void test_setPrice() {
        assertAll(
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_A();
                    task.setPrice(GET_PRICE_B());
                    assertEquals(GET_PRICE_B(), task.getPrice());
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_B();
                    task.setPrice(GET_PRICE_A());
                    assertEquals(GET_PRICE_A(), task.getPrice());
                }
        );
    }

    @Test
    void test_getDescription() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_DESCRIPTION_A(), NO_CONTRACT_TASK_A().getDescription()),
                () -> assertEquals(GET_DEFAULT_DESCRIPTION_B(), NO_CONTRACT_TASK_B().getDescription())
        );
    }

    @Test
    void test_setDescription() {
        assertAll(
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_A();
                    task.setDescription(GET_DEFAULT_DESCRIPTION_B());
                    assertEquals(GET_DEFAULT_DESCRIPTION_B(), task.getDescription());
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_B();
                    task.setDescription(GET_DEFAULT_DESCRIPTION_A());
                    assertEquals(GET_DEFAULT_DESCRIPTION_A(), task.getDescription());
                }
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(NO_CONTRACT_TASK_A(), NO_CONTRACT_TASK_A()),
                () -> assertEquals(NO_CONTRACT_TASK_B(), NO_CONTRACT_TASK_B()),
                () -> assertNotEquals(NO_CONTRACT_TASK_A(), NO_CONTRACT_TASK_B()),
                () -> assertNotEquals(NO_CONTRACT_TASK_B(), NO_CONTRACT_TASK_A()),
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_A();
                    task.setPrice(GET_PRICE_B());
                    assertNotEquals(NO_CONTRACT_TASK_A(), task);
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_A();
                    task.setDescription(GET_DEFAULT_DESCRIPTION_B());
                    assertNotEquals(NO_CONTRACT_TASK_A(), task);
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_A();
                    task.setStatus(GET_STATUS_B());
                    assertNotEquals(NO_CONTRACT_TASK_A(), task);
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_A();
                    task.setPrice(GET_PRICE_B());
                    assertNotEquals(NO_CONTRACT_TASK_A(), task);
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_B();
                    task.setStatus(GET_STATUS_A());
                    assertNotEquals(NO_CONTRACT_TASK_B(), task);
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_B();
                    task.setPrice(GET_PRICE_A());
                    assertNotEquals(NO_CONTRACT_TASK_B(), task);
                }
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(NO_CONTRACT_TASK_A().hashCode(), NO_CONTRACT_TASK_A().hashCode()),
                () -> assertEquals(NO_CONTRACT_TASK_B().hashCode(), NO_CONTRACT_TASK_B().hashCode()),
                () -> assertNotEquals(NO_CONTRACT_TASK_A().hashCode(), NO_CONTRACT_TASK_B().hashCode()),
                () -> assertNotEquals(NO_CONTRACT_TASK_B().hashCode(), NO_CONTRACT_TASK_A().hashCode()),
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_A();
                    task.setPrice(GET_PRICE_B());
                    assertNotEquals(NO_CONTRACT_TASK_A().hashCode(), task.hashCode());
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_A();
                    task.setDescription(GET_DEFAULT_DESCRIPTION_B());
                    assertNotEquals(NO_CONTRACT_TASK_A().hashCode(), task.hashCode());
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_A();
                    task.setStatus(GET_STATUS_B());
                    assertNotEquals(NO_CONTRACT_TASK_A().hashCode(), task.hashCode());
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_B();
                    task.setPrice(GET_PRICE_A());
                    assertNotEquals(NO_CONTRACT_TASK_B().hashCode(), task.hashCode());
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_B();
                    task.setDescription(GET_DEFAULT_DESCRIPTION_A());
                    assertNotEquals(NO_CONTRACT_TASK_B().hashCode(), task.hashCode());
                },
                () -> {
                    NoContractTask task = NO_CONTRACT_TASK_B();
                    task.setStatus(GET_STATUS_A());
                    assertNotEquals(NO_CONTRACT_TASK_B().hashCode(), task.hashCode());
                }
        );
    }

    @Test
    void test_toString() {
        assertAll(
                () -> assertEquals("NoContractTask(super=Task(status=PENDING), price=20.00, description=TASK_A)",
                        NO_CONTRACT_TASK_A().toString()),
                () -> assertEquals("NoContractTask(super=Task(status=IN_PROGRESS), price=50.50, description=TASK_B)",
                        NO_CONTRACT_TASK_B().toString())
        );
    }

}