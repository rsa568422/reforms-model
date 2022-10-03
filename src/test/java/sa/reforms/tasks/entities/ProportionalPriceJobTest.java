package sa.reforms.tasks.entities;

import sa.reforms.exceptions.InvalidParamsException;

import sa.reforms.tasks.entities.data.ProportionalPriceJobData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProportionalPriceJobTest {

    private ProportionalPriceJob proportionalPriceJob;

    @BeforeEach
    void setUp() {
        this.proportionalPriceJob = ProportionalPriceJobData.PP_JOB_PAINTWORK_PLASTIC();
    }

    @ParameterizedTest
    @CsvSource({ "3.0, 15.00", "12.0, 60.00", "50.0, 250.00" })
    void test_getPrize(String stringQuantity, String stringExpected) {
        Optional<Double> quantity = Optional.of(Double.valueOf(stringQuantity));
        BigDecimal expected = new BigDecimal(stringExpected);

        BigDecimal actual = this.proportionalPriceJob.getPrize(quantity);

        assertAll(
                () -> assertEquals(0, expected.compareTo(actual)),
                () -> assertEquals(expected.toPlainString(), actual.toPlainString())
        );
    }

    @Test
    void test_getPrize_change() {
        Optional<Double> quantity = Optional.of(10.0);
        BigDecimal expected = new BigDecimal("40.00");

        assertEquals(this.proportionalPriceJob.getUnitPrice(), new BigDecimal("5.00"));

        this.proportionalPriceJob.setUnitPrice(new BigDecimal("4.00"));
        BigDecimal actual = this.proportionalPriceJob.getPrize(quantity);

        assertAll(
                () -> assertEquals(0, expected.compareTo(actual)),
                () -> assertEquals(expected.toPlainString(), actual.toPlainString())
        );
    }

    @Test
    void test_getPrize_quantity_empty() {
        Optional<Double> quantity = Optional.empty();
        assertThrows(InvalidParamsException.class, () -> this.proportionalPriceJob.getPrize(quantity));
    }

    @Test
    void test_getPrize_quantity_invalid() {
        Optional<Double> quantity = Optional.of(-3.0);
        assertThrows(InvalidParamsException.class, () -> this.proportionalPriceJob.getPrize(quantity));
    }

    @Test
    void test_toString() {
        String string = this.proportionalPriceJob.toString();

        assertAll(
                () -> assertFalse(string.contains("ContractedJob")),
                () -> assertTrue(string.contains("ProportionalPriceJob")),
                () -> assertTrue(string.contains("guild")),
                () -> assertTrue(string.contains("name")),
                () -> assertFalse(string.contains("description")),
                () -> assertTrue(string.contains("name:Insurer A"))
        );
    }

}