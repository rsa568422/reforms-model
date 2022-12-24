package sa.reforms.tasks.contracted.jobs.constantprice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contracted.jobs.constantprice.data.ConstantPriceJobData.*;

class ConstantPriceJobTest {

    @Test
    void test_constructor() {
        assertAll(
                () -> {
                    ConstantPriceJob job1 = new ConstantPriceJob(
                            GET_DEFAULT_INSURER_A(),
                            GET_GUILD_A(),
                            GET_JOB_A().getName(),
                            GET_DEFAULT_PRICE_A()
                    );
                    ConstantPriceJob job2 = new ConstantPriceJob(
                            GET_DEFAULT_INSURER_A(),
                            GET_JOB_A(),
                            GET_DEFAULT_PRICE_A()
                    );
                    assertEquals(job1, job2);
                    assertEquals(job2, job1);
                    assertEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_A(), job1);
                    assertEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_A(), job2);
                }
        );
    }

    @Test
    void test_getPrice() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_PRICE_A(), GET_DEFAULT_CONSTANT_PRICE_JOB_A().getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE_B(), GET_DEFAULT_CONSTANT_PRICE_JOB_B().getPrice()),
                () -> assertNotEquals(GET_DEFAULT_PRICE_A(), GET_DEFAULT_CONSTANT_PRICE_JOB_B().getPrice()),
                () -> assertNotEquals(GET_DEFAULT_PRICE_B(), GET_DEFAULT_CONSTANT_PRICE_JOB_A().getPrice())
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_A(), GET_DEFAULT_CONSTANT_PRICE_JOB_A()),
                () -> assertEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_B(), GET_DEFAULT_CONSTANT_PRICE_JOB_B()),
                () -> assertNotEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_A(), GET_DEFAULT_CONSTANT_PRICE_JOB_B()),
                () -> assertNotEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_B(), GET_DEFAULT_CONSTANT_PRICE_JOB_A()),
                () -> {
                    ConstantPriceJob job = GET_DEFAULT_CONSTANT_PRICE_JOB_A();
                    job.setDescription(GET_DESCRIPTION_B());
                    assertNotEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_A(), job);
                },
                () -> {
                    ConstantPriceJob job = GET_DEFAULT_CONSTANT_PRICE_JOB_B();
                    job.setDescription(GET_DESCRIPTION_A());
                    assertNotEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_B(), job);
                }
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_A().hashCode(),
                        GET_DEFAULT_CONSTANT_PRICE_JOB_A().hashCode()),
                () -> assertEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_B().hashCode(),
                        GET_DEFAULT_CONSTANT_PRICE_JOB_B().hashCode()),
                () -> assertNotEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_A().hashCode(),
                        GET_DEFAULT_CONSTANT_PRICE_JOB_B().hashCode()),
                () -> assertNotEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_B().hashCode(),
                        GET_DEFAULT_CONSTANT_PRICE_JOB_A().hashCode()),
                () -> {
                    ConstantPriceJob job = GET_DEFAULT_CONSTANT_PRICE_JOB_A();
                    job.setDescription(GET_DESCRIPTION_B());
                    assertNotEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_A().hashCode(), job.hashCode());
                },
                () -> {
                    ConstantPriceJob job = GET_DEFAULT_CONSTANT_PRICE_JOB_B();
                    job.setDescription(GET_DESCRIPTION_A());
                    assertNotEquals(GET_DEFAULT_CONSTANT_PRICE_JOB_B().hashCode(), job.hashCode());
                }
        );
    }

    @Test
    void test_toString() {
        assertAll(
                () -> assertEquals("ConstantPriceJob(super=ContractedJob(super=Job(guild=PAINTWORK, name=plastic, description=Optional.empty), insurer=Insurer(name=INSURER_A, phones=[], fax=Optional.empty, email=Optional.empty)), price=20.00)",
                        GET_DEFAULT_CONSTANT_PRICE_JOB_A().toString()),
                () -> assertEquals("ConstantPriceJob(super=ContractedJob(super=Job(guild=BRICKWORK, name=plaster, description=Optional.empty), insurer=Insurer(name=INSURER_B, phones=[], fax=Optional.empty, email=Optional.empty)), price=50.50)",
                        GET_DEFAULT_CONSTANT_PRICE_JOB_B().toString())
        );
    }

}