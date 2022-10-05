package sa.reforms.tasks.contradtedjobs;

import sa.reforms.tasks.quatities.Quantity;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contradtedjobs.data.IncrementalRankPriceTableJobData.*;
import static sa.reforms.tasks.quantities.data.QuantityData.*;

class IncrementalRankPriceTableJobTest {

    private IncrementalRankPriceTableJob incrementalRankPriceTableJob;

    @BeforeEach
    void setUp() {
        this.incrementalRankPriceTableJob = IR_JOB_PAINTWORK_PLASTIC();
    }

    @Test
    void test_constructor_and_override_methods() {
        IncrementalRankPriceTableJob expected = IR_JOB_PAINTWORK_PLASTIC();
        IncrementalRankPriceTableJob actual = new IncrementalRankPriceTableJob(
                INSURER_A(),
                JOB_PAINTWORK_PLASTIC().getGuild(),
                JOB_PAINTWORK_PLASTIC().getName(),
                RANKS_MAP()
        );

        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.hashCode(), actual.hashCode()),
                () -> assertEquals(expected.toString(), actual.toString())
        );
    }

    @ParameterizedTest
    @CsvSource({ "3.0, 20.00", "12.0, 44.50", "50.0, 160.00" })
    void test_getPrize(String stringQuantity, String stringExpected) {
        Optional<Double> quantity = Optional.of(Double.valueOf(stringQuantity));
        BigDecimal expected = new BigDecimal(stringExpected);

        BigDecimal actual = this.incrementalRankPriceTableJob.getPrize(quantity);

        assertAll(
                () -> assertEquals(0, expected.compareTo(actual)),
                () -> assertEquals(expected.toPlainString(), actual.toPlainString())
        );
    }

    @Test
    void test_getPrize_quantity_empty() {
        Optional<Double> quantity = Optional.empty();
        assertThrows(InvalidParamsException.class, () -> this.incrementalRankPriceTableJob.getPrize(quantity));
    }

    @Test
    void test_getPrize_quantity_invalid() {
        Optional<Double> quantity = Optional.of(-3.0);
        assertThrows(InvalidParamsException.class, () -> this.incrementalRankPriceTableJob.getPrize(quantity));
    }

    @Test
    void test_valid() {
        assertAll(
                () -> assertFalse(this.incrementalRankPriceTableJob.valid(EMPTY())),
                () -> assertTrue(this.incrementalRankPriceTableJob.valid(Optional.of(CASE_A(Quantity.Unit.EU)))),
                () -> assertTrue(this.incrementalRankPriceTableJob.valid(Optional.of(CASE_B(Quantity.Unit.EU)))),
                () -> assertTrue(this.incrementalRankPriceTableJob.valid(Optional.of(CASE_C(Quantity.Unit.EU))))
        );
    }

    @Test
    void test_toString() {
        String string = this.incrementalRankPriceTableJob.toString();

        assertAll(
                () -> assertFalse(string.contains("ContractedJob")),
                () -> assertTrue(string.contains("IncrementalRankPriceTableJob")),
                () -> assertTrue(string.contains("guild")),
                () -> assertTrue(string.contains("name")),
                () -> assertFalse(string.contains("description")),
                () -> assertTrue(string.contains("name:Insurer A"))
        );
    }

}