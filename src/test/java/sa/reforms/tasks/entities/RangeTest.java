package sa.reforms.tasks.entities;

import org.junit.jupiter.api.Test;
import sa.reforms.tasks.entities.data.RangeData;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

    @Test
    void contains() {
        Double mark1 = -2D;
        Double mark2 = 2D;
        Double mark3 = 9D;

        assertAll(
                () -> assertFalse(RangeData.RANK_1().contains(mark1)),
                () -> assertTrue(RangeData.RANK_1().contains(mark2)),
                () -> assertFalse(RangeData.RANK_1().contains(mark3)),
                () -> assertTrue(RangeData.RANK_N().contains(mark1)),
                () -> assertTrue(RangeData.RANK_N().contains(mark2)),
                () -> assertFalse(RangeData.RANK_N().contains(mark3))
        );
    }

    @Test
    void testEquals() {
        assertAll(
                () -> assertEquals(RangeData.RANK_1(), RangeData.RANK_1()),
                () -> assertEquals(RangeData.RANK_2(), RangeData.RANK_2()),
                () -> assertEquals(RangeData.RANK_3(), RangeData.RANK_3()),
                () -> assertEquals(RangeData.RANK_N(), RangeData.RANK_N()),
                () -> assertEquals(RangeData.RANK_F(), RangeData.RANK_F())
        );
    }

    @Test
    void testHashCode() {
        assertAll(
                () -> assertEquals(RangeData.RANK_1().hashCode(), RangeData.RANK_1().hashCode()),
                () -> assertEquals(RangeData.RANK_2().hashCode(), RangeData.RANK_2().hashCode()),
                () -> assertEquals(RangeData.RANK_3().hashCode(), RangeData.RANK_3().hashCode()),
                () -> assertEquals(RangeData.RANK_N().hashCode(), RangeData.RANK_N().hashCode()),
                () -> assertEquals(RangeData.RANK_F().hashCode(), RangeData.RANK_F().hashCode())
        );
    }

    @Test
    void testToString() {
        assertAll(
                () -> assertEquals(RangeData.RANK_1().toString(), RangeData.RANK_1().toString()),
                () -> assertEquals(RangeData.RANK_2().toString(), RangeData.RANK_2().toString()),
                () -> assertEquals(RangeData.RANK_3().toString(), RangeData.RANK_3().toString()),
                () -> assertEquals(RangeData.RANK_N().toString(), RangeData.RANK_N().toString()),
                () -> assertEquals(RangeData.RANK_F().toString(), RangeData.RANK_F().toString())
        );
    }

    @Test
    void compareTo() {
        assertAll(
                () -> assertEquals(0, RangeData.RANK_1().compareTo(RangeData.RANK_1())),
                () -> assertTrue(RangeData.RANK_1().compareTo(RangeData.RANK_2()) < 0),
                () -> assertTrue(RangeData.RANK_1().compareTo(RangeData.RANK_3()) < 0)
        );
    }

}