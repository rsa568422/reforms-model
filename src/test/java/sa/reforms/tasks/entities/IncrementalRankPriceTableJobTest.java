package sa.reforms.tasks.entities;

import sa.reforms.tasks.entities.data.IncrementalRankPriceTableJobData;
import sa.reforms.exceptions.InvalidParamsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sa.reforms.tasks.entities.data.QuantityData;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IncrementalRankPriceTableJobTest {

    private IncrementalRankPriceTableJob incrementalRankPriceTableJob;

    @BeforeEach
    void setUp() {
        this.incrementalRankPriceTableJob = IncrementalRankPriceTableJobData.IR_JOB_PAINTWORK_PLASTIC();
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
                () -> assertFalse(this.incrementalRankPriceTableJob.valid(QuantityData.EMPTY())),
                () -> assertTrue(this.incrementalRankPriceTableJob.valid(Optional.of(QuantityData.CASE_A(Quantity.Unit.EU)))),
                () -> assertTrue(this.incrementalRankPriceTableJob.valid(Optional.of(QuantityData.CASE_B(Quantity.Unit.EU)))),
                () -> assertTrue(this.incrementalRankPriceTableJob.valid(Optional.of(QuantityData.CASE_C(Quantity.Unit.EU))))
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