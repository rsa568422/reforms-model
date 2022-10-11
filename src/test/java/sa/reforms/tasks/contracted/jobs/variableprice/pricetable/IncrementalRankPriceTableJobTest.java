package sa.reforms.tasks.contracted.jobs.variableprice.pricetable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contracted.Quantity.Unit.*;
import static sa.reforms.tasks.contracted.jobs.variableprice.pricetable.data.IncrementalRankPriceTableJobData.*;

class IncrementalRankPriceTableJobTest {

    @Test
    void test_constructor() {
        IncrementalRankPriceTableJob job1 = GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_A(M));
        IncrementalRankPriceTableJob job2 = new IncrementalRankPriceTableJob(
                GET_DEFAULT_INSURER_A(),
                GET_DEFAULT_GUILD_A(),
                GET_DEFAULT_JOB_A().getName(),
                CASE_A(M),
                GET_DEFAULT_PRICE_TABLE()
        );
        assertAll(
                () -> assertEquals(job1, job2),
                () -> assertEquals(job2, job1),
                () -> assertEquals(GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_A(M)), job1),
                () -> assertEquals(GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_A(M)), job2)
        );
    }

    @Test
    void test_getPrice() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_A(M)),
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_B(M)),
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_B(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_C(M)),
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_C(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_A(M)),
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_B(CASE_A(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_B(M)),
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_B(CASE_B(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_C(M)),
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_B(CASE_C(M)).getPrice())
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_A(M)),
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_A(M))
                )
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).hashCode(),
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).hashCode()
                )
        );
    }

    @Test
    void test_toString() {
        assertAll(
                () -> assertEquals(
                        "IncrementalRankPriceTableJob(super=PriceTableJob(super=VariablePriceJob(super=ContractedJob(super=Job(guild=PAINTWORK, name=plastic, description=Optional.empty), insurer=Insurer(name=INSURER_A, phones=[], fax=Optional.empty, email=Optional.empty)), quantity=Quantity(measure=3.0, unit=M)), have priceTable=true)",
                        GET_DEFAULT_INCREMENTAL_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).toString()
                )
        );
    }

}