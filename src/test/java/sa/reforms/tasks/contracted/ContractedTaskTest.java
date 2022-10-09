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
    void test_equals() {

    }

    @Test
    void test_hashCode() {

    }

    @Test
    void test_getJob() {

    }

    @Test
    void test_toString() {

    }

}