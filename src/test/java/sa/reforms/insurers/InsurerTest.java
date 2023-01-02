package sa.reforms.insurers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.insurers.data.InsurerData.*;

class InsurerTest {

    private Insurer insurer;

    @BeforeEach
    void setUp() {
        this.insurer = GET_INSURER_A();
    }

    @Test
    void test_setFax() {
        String expected = "900000001";

        insurer.setFax(expected);

        assertAll(
                () -> assertTrue(this.insurer.getFax().isPresent()),
                () -> assertEquals(expected, this.insurer.getFax().get())
        );
    }

    @Test
    void test_setEmail() {
        String expected = "insurer@email.com";

        this.insurer.setEmail(expected);

        assertAll(
                () -> assertTrue(this.insurer.getEmail().isPresent()),
                () -> assertEquals(expected, this.insurer.getEmail().get())
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(GET_INSURER_A(), this.insurer),
                () -> assertNotEquals(GET_INSURER_B(), this.insurer)
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(GET_INSURER_A().hashCode(), this.insurer.hashCode()),
                () -> assertNotEquals(GET_INSURER_B().hashCode(), this.insurer.hashCode())
        );
    }

    @Test
    void test_getName() {
        String expected = GET_NAME_A();

        String actual = this.insurer.getName();

        assertAll(
                () -> assertNotNull(expected),
                () -> assertNotNull(actual),
                () -> assertEquals(expected, this.insurer.getName())
        );
    }

    @Test
    void test_getPhones() {
        assertAll(
                () -> assertFalse(this.insurer.getPhones().contains(GET_PHONE_A())),
                () -> assertFalse(this.insurer.getPhones().contains(GET_PHONE_B())),
                () -> {
                    Insurer testInsurer = GET_INSURER_A_WITH_PHONE();
                    assertTrue(testInsurer.getPhones().contains(GET_PHONE_A()));
                },
                () -> {
                    Insurer testInsurer = GET_INSURER_A_WITH_PHONE();
                    testInsurer.getPhones().add(GET_PHONE_B());
                    assertEquals(2, testInsurer.getPhones().size());
                    assertTrue(testInsurer.getPhones().contains(GET_PHONE_B()));
                }
        );
    }

    @Test
    void test_getFax() {
        assertTrue(this.insurer.getFax().isEmpty());
    }

    @Test
    void test_getEmail() {
        assertTrue(this.insurer.getEmail().isEmpty());
    }

    @Test
    void test_toString() {
        assertAll(
                () -> assertEquals(GET_INSURER_A().toString(), this.insurer.toString()),
                () -> assertNotEquals(GET_INSURER_B().toString(), this.insurer.toString()),
                () -> assertNotEquals(GET_INSURER_A_WITH_PHONE().toString(), this.insurer.toString()),
                () -> assertTrue(this.insurer.toString().startsWith("Insurer(")),
                () -> assertTrue(this.insurer.toString().contains(GET_NAME_A())),
                () -> assertFalse(this.insurer.toString().contains(GET_NAME_B()))
        );
    }

}