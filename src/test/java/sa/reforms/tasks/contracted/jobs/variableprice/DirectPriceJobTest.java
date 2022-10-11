package sa.reforms.tasks.contracted.jobs.variableprice;

import sa.reforms.exceptions.InvalidParamsException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contracted.Quantity.Unit.*;
import static sa.reforms.tasks.contracted.jobs.variableprice.data.DirectPriceJobData.*;

class DirectPriceJobTest {

    @Test
    void test_constructor() {
        assertAll(
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new DirectPriceJob(GET_DEFAULT_INSURER_A(), GET_DEFAULT_JOB_A(), CASE_A(M)));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                },
                () -> {
                    Exception exception = assertThrows(InvalidParamsException.class,
                            () -> new DirectPriceJob(GET_DEFAULT_INSURER_B(), GET_DEFAULT_JOB_B(), CASE_B(M2)));
                    assertEquals(InvalidParamsException.class, exception.getClass());
                },
                () -> assertDoesNotThrow(() -> new DirectPriceJob(GET_DEFAULT_INSURER_A(),
                        GET_DEFAULT_JOB_A(), CASE_A(EU))),
                () -> {
                    DirectPriceJob job1 = GET_DEFAULT_DIRECT_PRICE_JOB_A();
                    DirectPriceJob job2 = new DirectPriceJob(
                            GET_DEFAULT_INSURER_A(),
                            GET_DEFAULT_JOB_A().getGuild(),
                            GET_DEFAULT_JOB_A().getName(),
                            CASE_EU_A()
                    );
                    assertEquals(job1, job2);
                    assertEquals(job2, job1);
                    assertEquals(GET_DEFAULT_DIRECT_PRICE_JOB_A(), job1);
                    assertEquals(GET_DEFAULT_DIRECT_PRICE_JOB_A(), job2);
                }
        );
    }

    @Test
    void test_getPrice() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_PRICE_A(), GET_DEFAULT_DIRECT_PRICE_JOB_A().getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE_B(), GET_DEFAULT_DIRECT_PRICE_JOB_B().getPrice())
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_DIRECT_PRICE_JOB_A(), GET_DEFAULT_DIRECT_PRICE_JOB_A()),
                () -> assertEquals(GET_DEFAULT_DIRECT_PRICE_JOB_B(), GET_DEFAULT_DIRECT_PRICE_JOB_B()),
                () -> assertNotEquals(GET_DEFAULT_DIRECT_PRICE_JOB_A(), GET_DEFAULT_DIRECT_PRICE_JOB_B()),
                () -> assertNotEquals(GET_DEFAULT_DIRECT_PRICE_JOB_B(), GET_DEFAULT_DIRECT_PRICE_JOB_A()),
                () -> {
                    DirectPriceJob job = GET_DEFAULT_DIRECT_PRICE_JOB_A();
                    job.setDescription(GET_DEFAULT_DESCRIPTION_B());
                    assertNotEquals(GET_DEFAULT_DIRECT_PRICE_JOB_A(), job);
                },
                () -> {
                    DirectPriceJob job = GET_DEFAULT_DIRECT_PRICE_JOB_B();
                    job.setDescription(GET_DEFAULT_DESCRIPTION_A());
                    assertNotEquals(GET_DEFAULT_DIRECT_PRICE_JOB_B(), job);
                }
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_DIRECT_PRICE_JOB_A().hashCode(),
                        GET_DEFAULT_DIRECT_PRICE_JOB_A().hashCode()),
                () -> assertEquals(GET_DEFAULT_DIRECT_PRICE_JOB_B().hashCode(),
                        GET_DEFAULT_DIRECT_PRICE_JOB_B().hashCode()),
                () -> assertNotEquals(GET_DEFAULT_DIRECT_PRICE_JOB_A().hashCode(),
                        GET_DEFAULT_DIRECT_PRICE_JOB_B().hashCode()),
                () -> assertNotEquals(GET_DEFAULT_DIRECT_PRICE_JOB_B().hashCode(),
                        GET_DEFAULT_DIRECT_PRICE_JOB_A().hashCode()),
                () -> {
                    DirectPriceJob job = GET_DEFAULT_DIRECT_PRICE_JOB_A();
                    job.setDescription(GET_DEFAULT_DESCRIPTION_B());
                    assertNotEquals(GET_DEFAULT_DIRECT_PRICE_JOB_A().hashCode(), job.hashCode());
                },
                () -> {
                    DirectPriceJob job = GET_DEFAULT_DIRECT_PRICE_JOB_B();
                    job.setDescription(GET_DEFAULT_DESCRIPTION_A());
                    assertNotEquals(GET_DEFAULT_DIRECT_PRICE_JOB_B().hashCode(), job.hashCode());
                }
        );
    }

    @Test
    void test_toString() {
        assertAll(
                () -> assertEquals("DirectPriceJob(super=VariablePriceJob(super=ContractedJob(super=Job(guild=PAINTWORK, name=plastic, description=Optional.empty), insurer=Insurer(name=INSURER_A, phones=[], fax=Optional.empty, email=Optional.empty)), quantity=Quantity(measure=20.0, unit=EU)))",
                        GET_DEFAULT_DIRECT_PRICE_JOB_A().toString()),
                () -> assertEquals("DirectPriceJob(super=VariablePriceJob(super=ContractedJob(super=Job(guild=BRICKWORK, name=plaster, description=Optional.empty), insurer=Insurer(name=INSURER_B, phones=[], fax=Optional.empty, email=Optional.empty)), quantity=Quantity(measure=50.5, unit=EU)))",
                        GET_DEFAULT_DIRECT_PRICE_JOB_B().toString())
        );
    }

}