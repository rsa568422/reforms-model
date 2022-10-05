package sa.reforms.tasks.contradtedjobs;

import sa.reforms.tasks.quatities.Quantity;
import sa.reforms.entities.Job;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contradtedjobs.data.DirectPriceJobData.*;
import static sa.reforms.tasks.quantities.data.QuantityData.*;

class DirectPriceJobTest {

    private DirectPriceJob directPriceJob;

    @BeforeEach
    void setUp() {
        this.directPriceJob = DP_JOB_PAINTWORK_PLASTIC();
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
                () -> assertFalse(this.directPriceJob.valid(EMPTY())),
                () -> assertTrue(this.directPriceJob.valid(Optional.of(CASE_A(Quantity.Unit.EU)))),
                () -> assertTrue(this.directPriceJob.valid(Optional.of(CASE_B(Quantity.Unit.EU)))),
                () -> assertTrue(this.directPriceJob.valid(Optional.of(CASE_C(Quantity.Unit.EU))))
        );
    }

    @Test
    void test_constructors() {
        Job job = JOB_PAINTWORK_PLASTIC();
        DirectPriceJob jobA = new DirectPriceJob(INSURER_A(), job.getGuild(), job.getName());
        DirectPriceJob jobB = new DirectPriceJob(INSURER_A(), JOB_PAINTWORK_PLASTIC());

        assertEquals(jobA.toString(), jobB.toString());
    }

    @Test
    void test_get_insurer() {
        DirectPriceJob job = DP_JOB_PAINTWORK_PLASTIC();
        assertEquals(INSURER_A(), job.getInsurer());
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