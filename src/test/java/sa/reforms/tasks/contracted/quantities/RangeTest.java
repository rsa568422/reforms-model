package sa.reforms.tasks.contracted.quantities;

import sa.reforms.tasks.contracted.quatities.Range;
import sa.reforms.tasks.exceptions.ComparisonException;
import sa.reforms.exceptions.InvalidParamsException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contracted.quantities.data.RangeData.*;

class RangeTest {

    @Test
    void test_constructor() {
        assertAll(
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new Range(3D, 0D));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                },
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new Range(0D, 0D));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                }
        );
    }

    @Test
    void test_contains() {
        Double mark1 = -2D;
        Double mark2 = 2D;
        Double mark3 = 9D;

        assertAll(
                () -> assertTrue(RANK_1().contains(mark1)),
                () -> assertFalse(RANK_1().contains(mark2)),
                () -> assertFalse(RANK_1().contains(mark3)),
                () -> assertFalse(RANK_3().contains(mark1)),
                () -> assertTrue(RANK_3().contains(mark2)),
                () -> assertFalse(RANK_3().contains(mark3))
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(RANK_N(), RANK_N()),
                () -> assertEquals(RANK_P(), RANK_P()),
                () -> assertEquals(RANK_1(), RANK_1()),
                () -> assertEquals(RANK_2(), RANK_2()),
                () -> assertEquals(RANK_3(), RANK_3()),
                () -> assertEquals(RANK_A(), RANK_A())
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(RANK_N().hashCode(), RANK_N().hashCode()),
                () -> assertEquals(RANK_P().hashCode(), RANK_P().hashCode()),
                () -> assertEquals(RANK_1().hashCode(), RANK_1().hashCode()),
                () -> assertEquals(RANK_2().hashCode(), RANK_2().hashCode()),
                () -> assertEquals(RANK_3().hashCode(), RANK_3().hashCode()),
                () -> assertEquals(RANK_A().hashCode(), RANK_A().hashCode())
        );
    }

    @Test
    void test_toString() {
        assertAll(
                () -> assertEquals("(-inf, 0.0]", RANK_N().toString()),
                () -> assertEquals("(0.0, +inf]", RANK_P().toString()),
                () -> assertEquals("(-3.0, 0.0]", RANK_1().toString()),
                () -> assertEquals("(0.0, 3.0]", RANK_2().toString()),
                () -> assertEquals("(-2.0, 2.0]", RANK_3().toString()),
                () -> assertEquals("(-inf, +inf]", RANK_A().toString())
        );
    }

    @Test
    void test_compareTo_rank_N() {
        assertAll(
                () -> assertEquals(0, RANK_N().compareTo(RANK_N())),
                () -> assertEquals(-1, RANK_N().compareTo(RANK_P())),
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_N().compareTo(RANK_1()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("other is completely contained", exception.getMessage());
                },
                () -> assertEquals(-1, RANK_N().compareTo(RANK_2())),
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_N().compareTo(RANK_3()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("both of them are partial contained", exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_N().compareTo(RANK_A()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("this is completely contained", exception.getMessage());
                }
        );
    }

    @Test
    void test_compareTo_rank_P() {
        assertAll(
                () -> assertEquals(1, RANK_P().compareTo(RANK_N())),
                () -> assertEquals(0, RANK_P().compareTo(RANK_P())),
                () -> assertEquals(1, RANK_P().compareTo(RANK_1())),
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_P().compareTo(RANK_2()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("other is completely contained", exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_P().compareTo(RANK_3()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("both of them are partial contained", exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_P().compareTo(RANK_A()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("this is completely contained", exception.getMessage());
                }
        );
    }

    @Test
    void test_compareTo_rank_1() {
        assertAll(
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_1().compareTo(RANK_N()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("this is completely contained", exception.getMessage());
                },
                () -> assertEquals(-1, RANK_1().compareTo(RANK_P())),
                () -> assertEquals(0, RANK_1().compareTo(RANK_1())),
                () -> assertEquals(-1, RANK_1().compareTo(RANK_2())),
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_1().compareTo(RANK_3()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("both of them are partial contained", exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_1().compareTo(RANK_A()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("this is completely contained", exception.getMessage());
                }
        );
    }

    @Test
    void test_compareTo_rank_2() {
        assertAll(
                () -> assertEquals(1, RANK_2().compareTo(RANK_N())),
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_2().compareTo(RANK_P()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("this is completely contained", exception.getMessage());
                },
                () -> assertEquals(1, RANK_2().compareTo(RANK_1())),
                () -> assertEquals(0, RANK_2().compareTo(RANK_2())),
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_2().compareTo(RANK_3()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("both of them are partial contained", exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_2().compareTo(RANK_A()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("this is completely contained", exception.getMessage());
                }
        );
    }

    @Test
    void test_compareTo_rank_3() {
        assertAll(
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_3().compareTo(RANK_N()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("both of them are partial contained", exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_3().compareTo(RANK_P()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("both of them are partial contained", exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_3().compareTo(RANK_1()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("both of them are partial contained", exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_3().compareTo(RANK_2()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("both of them are partial contained", exception.getMessage());
                },
                () -> assertEquals(0, RANK_3().compareTo(RANK_3())),
                () -> {
                    Exception exception = assertThrows(ComparisonException.class,
                            () -> RANK_3().compareTo(RANK_A()));
                    assertEquals(ComparisonException.class, exception.getClass());
                    assertEquals("this is completely contained", exception.getMessage());
                }
        );
    }

}