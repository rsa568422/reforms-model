package sa.reforms.incidences;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sa.reforms.exceptions.InvalidParamsException;
import sa.reforms.exceptions.ReformsException;
import sa.reforms.insurers.Person;
import sa.reforms.insurers.Proficient;
import sa.reforms.tasks.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static sa.reforms.incidences.data.IncidenceData.*;
import static sa.reforms.insurers.data.ClientData.*;
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
                () -> assertDoesNotThrow(() -> new Incidence("CODE", GET_INSURANCE_A(), GET_PROFICIENT_E())),
                () -> assertThrows(
                        ReformsException.class,
                        () -> new Incidence("CODE", GET_INSURANCE_A(), GET_PROFICIENT_G())
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
        Proficient expected = GET_PROFICIENT_E();

        Proficient actual = this.incidence.getActualProficient();

        assertEquals(expected, actual);
    }

    @Test
    void test_getActualProficient_various() {
        this.incidence.setProficient(GET_PROFICIENT_F());
        Proficient expected = GET_PROFICIENT_F();

        Proficient actual = this.incidence.getActualProficient();

        assertEquals(expected, actual);
    }

    @Test
    void test_getProficientsHistorial() throws InterruptedException {
        sleep(50);

        this.incidence.setProficient(GET_PROFICIENT_F());

        assertAll(
                () -> assertEquals(this.incidence.getActualProficient(), GET_PROFICIENT_F()),
                () -> {
                    Proficient last = this.incidence
                            .getProficientsHistorial()
                            .stream()
                            .max(Comparator.comparing(Pair::getLeft))
                            .map(Pair::getRight)
                            .orElseThrow();
                    assertEquals(GET_PROFICIENT_F(), last);
                },
                () -> {
                    Proficient first = this.incidence
                            .getProficientsHistorial()
                            .stream()
                            .min(Comparator.comparing(Pair::getLeft))
                            .map(Pair::getRight)
                            .orElseThrow();
                    assertEquals(GET_PROFICIENT_E(), first);
                },
                () -> {
                    List<Pair<Date, Proficient>> proficientsHistorial = this.incidence.getProficientsHistorial();
                    Date dateA = proficientsHistorial.get(1).getLeft();
                    Date dateB = proficientsHistorial.get(0).getLeft();
                    assertTrue(dateB.getTime() - dateA.getTime() >= 50);
                }
        );
    }

    @Test
    void test_setProficient() throws InterruptedException {
        sleep(50);

        this.incidence.setProficient(GET_PROFICIENT_F());

        assertAll(
                () -> assertEquals(this.incidence.getActualProficient(), GET_PROFICIENT_F()),
                () -> {
                    Date dateB = this.incidence.getProficientsHistorial().get(0).getLeft();
                    Date maxDate = this.incidence
                            .getProficientsHistorial()
                            .stream()
                            .max(Comparator.comparing(Pair::getLeft))
                            .map(Pair::getLeft)
                            .orElseThrow();
                    assertEquals(dateB, maxDate);
                },
                () -> assertThrows(
                        InvalidParamsException.class,
                        () -> this.incidence.setProficient(GET_PROFICIENT_G())
                )
        );
    }

    @Test
    void test_equals() {
        Incidence expected = GET_INCIDENCE_A();
        expected.getTasks().add(this.taskA);
        expected.getTasks().add(this.taskB);
        assertAll(
                () -> assertEquals(expected, this.incidence),
                () -> assertEquals(GET_INCIDENCE_A(), this.incidence),
                () -> assertNotEquals(GET_INCIDENCE_B(), this.incidence)
        );
    }

    @Test
    void test_hashCode() {
        Incidence expected = GET_INCIDENCE_A();
        expected.getTasks().add(this.taskA);
        expected.getTasks().add(this.taskB);
        assertAll(
                () -> assertEquals(expected.hashCode(), this.incidence.hashCode()),
                () -> assertEquals(GET_INCIDENCE_A().hashCode(), this.incidence.hashCode()),
                () -> assertNotEquals(GET_INCIDENCE_B().hashCode(), this.incidence.hashCode())
        );
    }

    @Test
    void test_toString() {
        Incidence expected = GET_INCIDENCE_A();
        expected.getTasks().add(this.taskA);
        expected.getTasks().add(this.taskB);
        assertAll(
                () -> assertEquals(expected.toString(), this.incidence.toString()),
                () -> assertNotEquals(GET_INCIDENCE_A().toString(), this.incidence.toString()),
                () -> assertNotEquals(GET_INCIDENCE_B().toString(), this.incidence.toString())
        );
    }

    @Test
    void test_getCode() {
        assertAll(
                () -> assertEquals(GET_INCIDENCE_A().getCode(), this.incidence.getCode()),
                () -> assertNotEquals(GET_INCIDENCE_B().getCode(), this.incidence.getCode())
        );
    }

    @Test
    void test_getInsurance() {
        assertAll(
                () -> assertEquals(GET_INCIDENCE_A().getInsurance(), this.incidence.getInsurance()),
                () -> assertNotEquals(GET_INCIDENCE_B().getInsurance(), this.incidence.getInsurance())
        );
    }

    @Test
    void test_getTasks() {
        BigDecimal priceA = new BigDecimal("50.00").setScale(2, RoundingMode.CEILING);
        BigDecimal priceB = new BigDecimal("100.00").setScale(2, RoundingMode.CEILING);

        when(this.taskA.getPrice()).thenReturn(new BigDecimal("50.00").setScale(2, RoundingMode.CEILING));
        when(this.taskB.getPrice()).thenReturn(new BigDecimal("100.00").setScale(2, RoundingMode.CEILING));

        Set<Task> tasks = this.incidence.getTasks();

        assertAll(
                () -> assertEquals(2, tasks.size()),
                () -> assertTrue(tasks.stream().anyMatch(task -> task.getPrice().equals(priceA))),
                () -> assertTrue(tasks.stream().anyMatch(task -> task.getPrice().equals(priceB)))
        );
    }

    @Test
    void test_getContacts() {
        this.incidence.getContacts().add(GET_CLIENT_A());

        Set<Person> expected = Collections.singleton(GET_CLIENT_A());

        Set<Person> contacts = this.incidence.getContacts();

        assertAll(
                () -> assertEquals(1, contacts.size()),
                () -> assertTrue(contacts.containsAll(expected)),
                () -> assertTrue(expected.containsAll(contacts))
        );
    }

}