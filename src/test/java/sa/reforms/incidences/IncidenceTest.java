package sa.reforms.incidences;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sa.reforms.exceptions.ReformsException;
import sa.reforms.insurers.Proficient;
import sa.reforms.tasks.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static sa.reforms.incidences.data.IncidenceData.*;
import static sa.reforms.insurers.data.InsuranceData.*;
import static sa.reforms.insurers.data.ProficientData.*;

@ExtendWith(MockitoExtension.class)
class IncidenceTest {

    private Incidence incidence;

    @Mock
    private Task taskA;

    @Mock
    private Task taskB;

    @BeforeEach
    void setUp() {
        this.incidence = GET_INCIDENCE_A();
        this.incidence.getTasks().add(this.taskA);
        this.incidence.getTasks().add(this.taskB);
    }

    @Test
    void test_constructor() {
        assertAll(
                () -> assertDoesNotThrow(() -> new Incidence("CODE", GET_INSURANCE_A(), GET_PROFICIENT_A())),
                () -> assertThrows(
                        ReformsException.class,
                        () -> new Incidence("CODE", GET_INSURANCE_A(), GET_PROFICIENT_C())
                )
        );
    }

    @Test
    void test_getAssessment() {
        BigDecimal expected = new BigDecimal("150.00").setScale(2, RoundingMode.CEILING);

        when(this.taskA.getPrice()).thenReturn(new BigDecimal("50.00").setScale(2, RoundingMode.CEILING));
        when(this.taskB.getPrice()).thenReturn(new BigDecimal("100.00").setScale(2, RoundingMode.CEILING));

        BigDecimal actual = this.incidence.getAssessment();

        assertEquals(expected, actual);
    }

    @Test
    void test_getActualProficient_alone() {
        Proficient expected = GET_PROFICIENT_A();

        Proficient actual = this.incidence.getActualProficient();

        assertEquals(expected, actual);
    }

    @Test
    void test_getActualProficient_various() {
        this.incidence.setProficient(GET_PROFICIENT_B());
        Proficient expected = GET_PROFICIENT_B();

        Proficient actual = this.incidence.getActualProficient();

        assertEquals(expected, actual);
    }

    @Test
    void test_getProficientsHistorial() throws InterruptedException {
        sleep(500);

        this.incidence.setProficient(GET_PROFICIENT_B());

        assertAll(
                () -> assertEquals(this.incidence.getActualProficient(), GET_PROFICIENT_B()),
                () -> {
                    Proficient last = this.incidence
                            .getProficientsHistorial()
                            .stream()
                            .max(Comparator.comparing(Pair::getLeft))
                            .map(Pair::getRight)
                            .orElseThrow();
                    assertEquals(GET_PROFICIENT_B(), last);
                },
                () -> {
                    Proficient first = this.incidence
                            .getProficientsHistorial()
                            .stream()
                            .min(Comparator.comparing(Pair::getLeft))
                            .map(Pair::getRight)
                            .orElseThrow();
                    assertEquals(GET_PROFICIENT_A(), first);
                },
                () -> {
                    List<Pair<Date, Proficient>> proficientsHistorial = this.incidence.getProficientsHistorial();
                    Date dateA = proficientsHistorial.get(1).getLeft();
                    Date dateB = proficientsHistorial.get(0).getLeft();
                    assertTrue(dateB.getTime() - dateA.getTime() >= 500);
                }
        );
    }

    @Test
    void test_setProficient() throws InterruptedException {
        sleep(500);

        this.incidence.setProficient(GET_PROFICIENT_B());

        assertAll(
                () -> assertEquals(this.incidence.getActualProficient(), GET_PROFICIENT_B()),
                () -> {
                    Date dateB = this.incidence.getProficientsHistorial().get(0).getLeft();
                    Date maxDate = this.incidence
                            .getProficientsHistorial()
                            .stream()
                            .max(Comparator.comparing(Pair::getLeft))
                            .map(Pair::getLeft)
                            .orElseThrow();
                    assertEquals(dateB, maxDate);
                }
        );
    }

    @Test
    void test_equals() {
    }

    @Test
    void test_hashCode() {
    }

    @Test
    void test_toString() {
    }

    @Test
    void test_getCode() {
    }

    @Test
    void test_getInsurance() {
    }

    @Test
    void test_getTasks() {
    }

    @Test
    void test_getContacts() {
    }

}