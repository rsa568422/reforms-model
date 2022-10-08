package sa.reforms.tasks.contracted;

import sa.reforms.exceptions.InvalidParamsException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contracted.Quantity.Unit.*;
import static sa.reforms.tasks.contracted.data.QuantityData.*;

class QuantityTest {

    @Test
    void test_constructor() {
        assertAll(
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new Quantity(-3D, M));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                },
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new Quantity(0D, EU));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                },
                () -> assertDoesNotThrow(() -> new Quantity(1D, M)),
                () -> assertDoesNotThrow(() -> new Quantity(3D, M2)),
                () -> assertDoesNotThrow(() -> new Quantity(8.75D, H)),
                () -> assertDoesNotThrow(() -> new Quantity(Double.MAX_VALUE, EU))
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(CASE_A(M), CASE_A(M)),
                () -> assertEquals(CASE_B(M2), CASE_B(M2)),
                () -> assertEquals(CASE_C(H), CASE_C(H)),
                () -> assertEquals(CASE_A(EU), CASE_A(EU)),
                () -> assertNotEquals(CASE_A(M), CASE_A(M2)),
                () -> assertNotEquals(CASE_B(M2), CASE_B(H)),
                () -> assertNotEquals(CASE_C(H), CASE_C(EU)),
                () -> assertNotEquals(CASE_A(M), CASE_B(M)),
                () -> assertNotEquals(CASE_B(M2), CASE_C(M2)),
                () -> assertNotEquals(CASE_C(H), CASE_A(H)),
                () -> assertNotEquals(CASE_A(EU), CASE_C(EU))
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(CASE_A(M).hashCode(), CASE_A(M).hashCode()),
                () -> assertEquals(CASE_B(M2).hashCode(), CASE_B(M2).hashCode()),
                () -> assertEquals(CASE_C(H).hashCode(), CASE_C(H).hashCode()),
                () -> assertEquals(CASE_A(EU).hashCode(), CASE_A(EU).hashCode()),
                () -> assertNotEquals(CASE_A(M).hashCode(), CASE_A(M2).hashCode()),
                () -> assertNotEquals(CASE_B(M2).hashCode(), CASE_B(H).hashCode()),
                () -> assertNotEquals(CASE_C(H).hashCode(), CASE_C(EU).hashCode()),
                () -> assertNotEquals(CASE_A(M).hashCode(), CASE_B(M).hashCode()),
                () -> assertNotEquals(CASE_B(M2).hashCode(), CASE_C(M2).hashCode()),
                () -> assertNotEquals(CASE_C(H).hashCode(), CASE_A(H).hashCode()),
                () -> assertNotEquals(CASE_A(EU).hashCode(), CASE_C(EU).hashCode())
        );
    }

    @Test
    void test_getMeasure() {
        assertAll(
                () -> assertEquals(3D, CASE_A(M).getMeasure()),
                () -> assertEquals(12D, CASE_B(M2).getMeasure()),
                () -> assertEquals(50D, CASE_C(H).getMeasure()),
                () -> assertEquals(3D, CASE_A(EU).getMeasure())
        );
    }

    @Test
    void test_getUnit() {
        assertAll(
                () -> assertEquals(M, CASE_A(M).getUnit()),
                () -> assertEquals(M2, CASE_B(M2).getUnit()),
                () -> assertEquals(H, CASE_C(H).getUnit()),
                () -> assertEquals(EU, CASE_A(EU).getUnit())
        );
    }

    @Test
    void test_toString() {
        assertAll(
                () -> assertEquals("Quantity(measure=3.0, unit=M)", CASE_A(M).toString()),
                () -> assertEquals("Quantity(measure=12.0, unit=M2)", CASE_B(M2).toString()),
                () -> assertEquals("Quantity(measure=50.0, unit=H)", CASE_C(H).toString()),
                () -> assertEquals("Quantity(measure=3.0, unit=EU)", CASE_A(EU).toString())
        );
    }

}