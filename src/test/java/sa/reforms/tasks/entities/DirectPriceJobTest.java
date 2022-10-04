package sa.reforms.tasks.entities;

import sa.reforms.entities.Job;
import sa.reforms.tasks.entities.data.DirectPriceJobData;
import sa.reforms.tasks.entities.data.InsurerData;
import sa.reforms.tasks.entities.data.JobData;
import sa.reforms.tasks.entities.data.QuantityData;
import sa.reforms.exceptions.InvalidParamsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DirectPriceJobTest {

    private DirectPriceJob directPriceJob;

    @BeforeEach
    void setUp() {
        this.directPriceJob = DirectPriceJobData.DP_JOB_PAINTWORK_PLASTIC();
    }

    @ParameterizedTest
    @CsvSource({ "3.0, 3.00", "12.0, 12.00", "50.0, 50.00" })
    void test_getPrize(String stringQuantity, String stringExpected) {
        Optional<Double> quantity = Optional.of(Double.valueOf(stringQuantity));
        BigDecimal expected = new BigDecimal(stringExpected);

        BigDecimal actual = this.directPriceJob.getPrize(quantity);

        assertAll(
                () -> assertEquals(0, expected.compareTo(actual)),
                () -> assertEquals(expected.toPlainString(), actual.toPlainString())
        );
    }

    @Test
    void test_getPrize_quantity_empty() {
        Optional<Double> quantity = Optional.empty();
        assertThrows(InvalidParamsException.class, () -> this.directPriceJob.getPrize(quantity));
    }

    @Test
    void test_valid() {
        assertAll(
                () -> assertFalse(this.directPriceJob.valid(QuantityData.EMPTY())),
                () -> assertTrue(this.directPriceJob.valid(Optional.of(QuantityData.CASE_A(Quantity.Unit.EU)))),
                () -> assertTrue(this.directPriceJob.valid(Optional.of(QuantityData.CASE_B(Quantity.Unit.EU)))),
                () -> assertTrue(this.directPriceJob.valid(Optional.of(QuantityData.CASE_C(Quantity.Unit.EU))))
        );
    }

    @Test
    void test_constructors() {
        Job job = JobData.JOB_PAINTWORK_PLASTIC();
        DirectPriceJob jobA = new DirectPriceJob(InsurerData.INSURER_A(), job.getGuild(), job.getName());
        DirectPriceJob jobB = new DirectPriceJob(InsurerData.INSURER_A(), JobData.JOB_PAINTWORK_PLASTIC());

        assertEquals(jobA.toString(), jobB.toString());
    }

    @Test
    void test_get_insurer() {
        DirectPriceJob job = DirectPriceJobData.DP_JOB_PAINTWORK_PLASTIC();
        assertEquals(InsurerData.INSURER_A(), job.getInsurer());
    }

    @Test
    void test_toString() {
        String string = this.directPriceJob.toString();

        assertAll(
                () -> assertFalse(string.contains("ContractedJob")),
                () -> assertTrue(string.contains("DirectPriceJob")),
                () -> assertTrue(string.contains("guild")),
                () -> assertTrue(string.contains("name")),
                () -> assertFalse(string.contains("description")),
                () -> assertTrue(string.contains("name:Insurer A"))
        );
    }

}