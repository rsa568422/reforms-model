package sa.reforms.tasks.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sa.reforms.tasks.entities.data.UniqueRankPriceTableJobData;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UniqueRankPriceTableJobTest {

    private UniqueRankPriceTableJob uniqueRankPriceTableJob;

    @BeforeEach
    void setUp() {
        this.uniqueRankPriceTableJob = UniqueRankPriceTableJobData.UR_JOB_PAINTWORK_PLASTIC();
    }

    @ParameterizedTest
    @CsvSource({ "3.0, 20.00", "12.0, 24.50", "50.0, 105.00" })
    void test_getPrize(String stringQuantity, String stringExpected) {
        Optional<Double> quantity = Optional.of(Double.valueOf(stringQuantity));
        BigDecimal expected = new BigDecimal(stringExpected);

        BigDecimal actual = this.uniqueRankPriceTableJob.getPrize(quantity);

        assertAll(
                () -> assertEquals(0, expected.compareTo(actual)),
                () -> assertEquals(expected.toPlainString(), actual.toPlainString())
        );
    }

    @Test
    void test_getPrize_quantity_empty() {
        Optional<Double> quantity = Optional.empty();
        assertThrows(InvalidParamsException.class, () -> this.uniqueRankPriceTableJob.getPrize(quantity));
    }

    @Test
    void test_getPrize_quantity_invalid() {
        Optional<Double> quantity = Optional.of(-3.0);
        assertThrows(InvalidParamsException.class, () -> this.uniqueRankPriceTableJob.getPrize(quantity));
    }

    @Test
    void test_toString() {
        String string = this.uniqueRankPriceTableJob.toString();

        assertAll(
                () -> assertFalse(string.contains("ContractedJob")),
                () -> assertTrue(string.contains("UniqueRankPriceTableJob")),
                () -> assertTrue(string.contains("guild")),
                () -> assertTrue(string.contains("name")),
                () -> assertFalse(string.contains("description")),
                () -> assertTrue(string.contains("name:Insurer A"))
        );
    }

}