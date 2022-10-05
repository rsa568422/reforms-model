package sa.reforms.tasks;

import sa.reforms.entities.Job;
import sa.reforms.tasks.quatities.Quantity;
import sa.reforms.tasks.contradtedjobs.UniqueRankPriceJob;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.data.QuantityData.*;
import static sa.reforms.tasks.data.UniqueRankPriceTableJobData.*;

class UniqueRankPriceJobTest {

    private UniqueRankPriceJob uniqueRankPriceJob;

    @BeforeEach
    void setUp() {
        this.uniqueRankPriceJob = UR_JOB_PAINTWORK_PLASTIC();
    }

    @Test
    void test_constructor_and_override_methods() {
        Job job = JOB_PAINTWORK_PLASTIC();
        UniqueRankPriceJob expected = UR_JOB_PAINTWORK_PLASTIC();
        UniqueRankPriceJob actual = new UniqueRankPriceJob(INSURER_A(), job.getGuild(), job.getName(), RANKS_MAP());
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.hashCode(), actual.hashCode()),
                () -> assertEquals(expected.toString(), actual.toString())
        );
    }

    @ParameterizedTest
    @CsvSource({ "3.0, 20.00", "12.0, 24.50", "50.0, 105.00" })
    void test_getPrize(String stringQuantity, String stringExpected) {
        Optional<Double> quantity = Optional.of(Double.valueOf(stringQuantity));
        BigDecimal expected = new BigDecimal(stringExpected);

        BigDecimal actual = this.uniqueRankPriceJob.getPrize(quantity);

        assertAll(
                () -> assertEquals(0, expected.compareTo(actual)),
                () -> assertEquals(expected.toPlainString(), actual.toPlainString())
        );
    }

    @Test
    void test_getPrize_quantity_empty() {
        Optional<Double> quantity = Optional.empty();
        assertThrows(InvalidParamsException.class, () -> this.uniqueRankPriceJob.getPrize(quantity));
    }

    @Test
    void test_getPrize_quantity_invalid() {
        Optional<Double> quantity = Optional.of(-3.0);
        assertThrows(InvalidParamsException.class, () -> this.uniqueRankPriceJob.getPrize(quantity));
    }

    @Test
    void test_valid() {
        assertAll(
                () -> assertFalse(this.uniqueRankPriceJob.valid(EMPTY())),
                () -> assertTrue(this.uniqueRankPriceJob.valid(Optional.of(CASE_A(Quantity.Unit.EU)))),
                () -> assertTrue(this.uniqueRankPriceJob.valid(Optional.of(CASE_B(Quantity.Unit.EU)))),
                () -> assertTrue(this.uniqueRankPriceJob.valid(Optional.of(CASE_C(Quantity.Unit.EU))))
        );
    }

    @Test
    void test_toString() {
        String string = this.uniqueRankPriceJob.toString();

        assertAll(
                () -> assertFalse(string.contains("ContractedJob")),
                () -> assertTrue(string.contains("UniqueRankPriceJob")),
                () -> assertTrue(string.contains("guild")),
                () -> assertTrue(string.contains("name")),
                () -> assertFalse(string.contains("description")),
                () -> assertTrue(string.contains("name:Insurer A"))
        );
    }

}