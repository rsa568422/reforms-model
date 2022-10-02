package sa.reforms.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sa.reforms.entities.data.PriceTableJobData;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PriceTableJobTest {

    private PriceTableJob priceTableJob;

    @BeforeEach
    void setUp() {
        this.priceTableJob = PriceTableJobData.PT_JOB_PAINTWORK_PLASTIC();
    }

    @Test
    void test_getPrize_quantity_3() {
        Optional<Double> quantity = Optional.of(3.0);
        BigDecimal expected = new BigDecimal("20.00");

        BigDecimal actual = this.priceTableJob.getPrize(quantity);

        assertAll(
                () -> assertEquals(0, expected.compareTo(actual)),
                () -> assertEquals(expected.toPlainString(), actual.toPlainString())
        );
    }

    @Test
    void test_getPrize_quantity_12() {
        Optional<Double> quantity = Optional.of(12.0);
        BigDecimal expected = new BigDecimal("54.00");

        BigDecimal actual = this.priceTableJob.getPrize(quantity);

        assertAll(
                () -> assertEquals(0, expected.compareTo(actual)),
                () -> assertEquals(expected.toPlainString(), actual.toPlainString())
        );
    }

    @Test
    void test_getPrize_quantity_50() {
        Optional<Double> quantity = Optional.of(50.0);
        BigDecimal expected = new BigDecimal("200.00");

        BigDecimal actual = this.priceTableJob.getPrize(quantity);

        assertAll(
                () -> assertEquals(0, expected.compareTo(actual)),
                () -> assertEquals(expected.toPlainString(), actual.toPlainString())
        );
    }

    @Test
    void test_getPrize_quantity_empty() {
        Optional<Double> quantity = Optional.empty();
        assertThrows(InvalidParamsException.class, () -> this.priceTableJob.getPrize(quantity));
    }

    @Test
    void test_getPrize_quantity_invalid() {
        Optional<Double> quantity = Optional.of(-3.0);
        assertThrows(InvalidParamsException.class, () -> this.priceTableJob.getPrize(quantity));
    }

    @Test
    void test_toString() {
        String string = this.priceTableJob.toString();

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