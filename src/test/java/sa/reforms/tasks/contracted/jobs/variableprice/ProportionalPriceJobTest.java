package sa.reforms.tasks.contracted.jobs.variableprice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contracted.Quantity.Unit.M;
import static sa.reforms.tasks.contracted.jobs.variableprice.data.ProportionalPriceJobData.*;

class ProportionalPriceJobTest {

    @Test
    void test_constructor() {
        ProportionalPriceJob job1 = GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A();
        ProportionalPriceJob job2 = new ProportionalPriceJob(
                GET_DEFAULT_INSURER_A(),
                GET_GUILD_A(),
                GET_JOB_A().getName(),
                CASE_A(M),
                GET_DEFAULT_UNIT_PRICE_A()
        );
        assertAll(
                () -> assertEquals(job1, job2),
                () -> assertEquals(job2, job1),
                () -> assertEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A(), job1),
                () -> assertEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A(), job2)
        );
    }

    @Test
    void test_getPrice() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_PRICE_A_x(GET_DEFAULT_UNIT_PRICE_A()),
                        GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A().getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE_B_x(GET_DEFAULT_UNIT_PRICE_B()),
                        GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B().getPrice())
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A(), GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A()),
                () -> assertEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B(), GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B()),
                () -> assertNotEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A(), GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B()),
                () -> assertNotEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B(), GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A()),
                () -> {
                    ProportionalPriceJob job = GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A();
                    job.setDescription(GET_DESCRIPTION_B());
                    assertNotEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A(), job);
                },
                () -> {
                    ProportionalPriceJob job = GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B();
                    job.setDescription(GET_DESCRIPTION_A());
                    assertNotEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B(), job);
                }
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A().hashCode(),
                        GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A().hashCode()),
                () -> assertEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B().hashCode(),
                        GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B().hashCode()),
                () -> assertNotEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A().hashCode(),
                        GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B().hashCode()),
                () -> assertNotEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B().hashCode(),
                        GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A().hashCode()),
                () -> {
                    ProportionalPriceJob job = GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A();
                    job.setDescription(GET_DESCRIPTION_B());
                    assertNotEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A().hashCode(), job.hashCode());
                },
                () -> {
                    ProportionalPriceJob job = GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B();
                    job.setDescription(GET_DESCRIPTION_A());
                    assertNotEquals(GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B().hashCode(), job.hashCode());
                }
        );
    }

    @Test
    void test_toString() {
        assertAll(
                () -> assertEquals("ProportionalPriceJob(super=VariablePriceJob(super=ContractedJob(super=Job(guild=PAINTWORK, name=plastic, description=Optional.empty), insurer=Insurer(name=INSURER_A, phones=[], fax=Optional.empty, email=Optional.empty)), quantity=Quantity(measure=3.0, unit=M)), unitPrice=1.50)",
                        GET_DEFAULT_PROPORTIONAL_PRICE_JOB_A().toString()),
                () -> assertEquals("ProportionalPriceJob(super=VariablePriceJob(super=ContractedJob(super=Job(guild=BRICKWORK, name=plaster, description=Optional.empty), insurer=Insurer(name=INSURER_B, phones=[], fax=Optional.empty, email=Optional.empty)), quantity=Quantity(measure=12.0, unit=M2)), unitPrice=2.50)",
                        GET_DEFAULT_PROPORTIONAL_PRICE_JOB_B().toString())
        );
    }

}