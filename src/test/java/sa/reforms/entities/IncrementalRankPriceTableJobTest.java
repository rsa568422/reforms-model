package sa.reforms.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sa.reforms.entities.data.IncrementalRankPriceTableJobData;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IncrementalRankPriceTableJobTest {

    private IncrementalRankPriceTableJob incrementalRankPriceTableJob;

    @BeforeEach
    void setUp() {
        this.incrementalRankPriceTableJob = IncrementalRankPriceTableJobData.PT_JOB_PAINTWORK_PLASTIC();
    }

    @ParameterizedTest
    @CsvSource({ "3.0, 20.00", "12.0, 44.50", "50.0, 160.00" })
    void test_getPrize(String stringQuantity, String stringExpected) {
        Optional<Double> quantity = Optional.of(Double.valueOf(stringQuantity));
        BigDecimal expected = new BigDecimal(stringExpected);

        BigDecimal actual = this.incrementalRankPriceTableJob.getPrize(quantity);

        System.out.printf("expected = %s, actual = %s%n", expected.toPlainString(), actual.toPlainString());

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
    void test_toString() {
        String string = this.incrementalRankPriceTableJob.toString();

        assertAll(
                () -> assertFalse(string.contains("ContractedJob")),
                () -> assertTrue(string.contains("PriceTableJob")),
                () -> assertTrue(string.contains("guild")),
                () -> assertTrue(string.contains("name")),
                () -> assertFalse(string.contains("description")),
                () -> assertTrue(string.contains("name:Insurer A"))
        );
    }

}