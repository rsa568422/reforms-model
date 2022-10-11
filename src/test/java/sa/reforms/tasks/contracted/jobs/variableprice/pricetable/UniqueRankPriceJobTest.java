package sa.reforms.tasks.contracted.jobs.variableprice.pricetable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contracted.Quantity.Unit.M;
import static sa.reforms.tasks.contracted.jobs.variableprice.pricetable.data.UniqueRankPriceJobData.*;

class UniqueRankPriceJobTest {

    @Test
    void test_constructor() {
        UniqueRankPriceJob job1 = GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A();
        UniqueRankPriceJob job2 = new UniqueRankPriceJob(
                GET_DEFAULT_INSURER_A(),
                GET_DEFAULT_GUILD_A(),
                GET_DEFAULT_JOB_A().getName(),
                CASE_A(M),
                GET_DEFAULT_PRICE_TABLE()
        );
        assertAll(
                () -> assertEquals(job1, job2),
                () -> assertEquals(job2, job1),
                () -> assertEquals(GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(), job1),
                () -> assertEquals(GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(), job2)
        );
    }

    @Test
    void getPrice() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

}