package sa.reforms.tasks.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskTest {

    @InjectMocks
    private Task task;

    @Mock
    private ContractedJob contractedJob;

    @Test
    void test() {
        BigDecimal expected = new BigDecimal("50.50").setScale(2, RoundingMode.CEILING);

        when(this.contractedJob.getPrize(any())).thenReturn(new BigDecimal("50.50").setScale(2, RoundingMode.CEILING));

        BigDecimal actual = this.task.getPrice();

        assertEquals(expected, actual);
    }

}