package sa.reforms.tasks.entities;

import sa.reforms.tasks.entities.data.QuantityData;
import sa.reforms.tasks.entities.data.UniqueRankPriceTableJobData;
import sa.reforms.exceptions.InvalidParamsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UniqueRankPriceJobTest {

    private UniqueRankPriceJob uniqueRankPriceJob;

    @BeforeEach
    void setUp() {
        this.uniqueRankPriceJob = UniqueRankPriceTableJobData.UR_JOB_PAINTWORK_PLASTIC();
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
                () -> assertFalse(this.uniqueRankPriceJob.valid(QuantityData.EMPTY())),
                () -> assertTrue(this.uniqueRankPriceJob.valid(Optional.of(QuantityData.CASE_A(Quantity.Unit.EU)))),
                () -> assertTrue(this.uniqueRankPriceJob.valid(Optional.of(QuantityData.CASE_B(Quantity.Unit.EU)))),
                () -> assertTrue(this.uniqueRankPriceJob.valid(Optional.of(QuantityData.CASE_C(Quantity.Unit.EU))))
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