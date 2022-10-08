package sa.reforms.tasks.contracted;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static sa.reforms.tasks.contracted.data.ContractedTaskData.GET_DEFAULT_PRIZE;

@ExtendWith(MockitoExtension.class)
class ContractedTaskTest {

    @InjectMocks
    private ContractedTask contractedTask;

    @Mock
    private ContractedJob contractedJob;

    @Test
    void test_getPrice() {
        BigDecimal expected = GET_DEFAULT_PRIZE();

        when(this.contractedJob.getPrice()).thenReturn(GET_DEFAULT_PRIZE());

        BigDecimal actual = this.contractedTask.getPrice();

        assertEquals(expected, actual);
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