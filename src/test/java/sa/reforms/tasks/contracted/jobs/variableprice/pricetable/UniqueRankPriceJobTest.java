package sa.reforms.tasks.contracted.jobs.variableprice.pricetable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sa.reforms.tasks.contracted.Quantity.Unit.*;
import static sa.reforms.tasks.contracted.Quantity.Unit.M2;
import static sa.reforms.tasks.contracted.jobs.variableprice.pricetable.data.UniqueRankPriceJobData.*;

class UniqueRankPriceJobTest {

    @Test
    void test_constructor() {
        UniqueRankPriceJob job1 = GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M));
        UniqueRankPriceJob job2 = new UniqueRankPriceJob(
                GET_DEFAULT_INSURER_A(),
                GET_GUILD_A(),
                GET_JOB_A().getName(),
                CASE_A(M),
                GET_DEFAULT_PRICE_TABLE()
        );
        assertAll(
                () -> assertEquals(job1, job2),
                () -> assertEquals(job2, job1),
                () -> assertEquals(GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)), job1),
                () -> assertEquals(GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)), job2)
        );
    }

    @Test
    void test_getPrice() {
        assertAll(
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_A(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_B(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_B(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_C(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_C(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_A(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_A(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_B(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_B(M)).getPrice()),
                () -> assertEquals(GET_DEFAULT_PRICE(CASE_C(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_C(M)).getPrice())
        );
    }

    @Test
    void test_equals() {
        assertAll(
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M))
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_B(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_B(M))
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_C(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_C(M))
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_A(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_A(M))
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_B(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_B(M))
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_C(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_C(M))
                ),
                () -> assertNotEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_B(M))
                ),
                () -> assertNotEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_A(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_B(M))
                ),
                () -> assertNotEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_A(M))
                )
        );
    }

    @Test
    void test_hashCode() {
        assertAll(
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).hashCode()
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_B(M)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_B(M)).hashCode()
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_C(M)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_C(M)).hashCode()
                ),
                () -> assertNotEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M2)).hashCode()
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_A(M2)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_A(M2)).hashCode()
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_B(M2)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_B(M2)).hashCode()
                ),
                () -> assertEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_C(M2)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_C(M2)).hashCode()
                ),
                () -> assertNotEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_C(M)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_C(M2)).hashCode()
                ),
                () -> assertNotEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_B(M)).hashCode()
                ),
                () -> assertNotEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_A(M)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_B(M)).hashCode()
                ),
                () -> assertNotEquals(
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).hashCode(),
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_A(EU)).hashCode()
                )
        );
    }

    @Test
    void test_toString() {
        assertAll(
                () -> assertEquals(
                        "UniqueRankPriceJob(super=PriceTableJob(super=VariablePriceJob(super=ContractedJob(super=Job(guild=PAINTWORK, name=plastic, description=Optional.empty), insurer=Insurer(name=INSURER_A, phones=[], fax=Optional.empty, email=Optional.empty)), quantity=Quantity(measure=3.0, unit=M)), have priceTable=true)",
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(CASE_A(M)).toString()
                ),
                () -> assertEquals(
                        "UniqueRankPriceJob(super=PriceTableJob(super=VariablePriceJob(super=ContractedJob(super=Job(guild=BRICKWORK, name=plaster, description=Optional.empty), insurer=Insurer(name=INSURER_B, phones=[], fax=Optional.empty, email=Optional.empty)), quantity=Quantity(measure=12.0, unit=M2)), have priceTable=true)",
                        GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(CASE_B(M2)).toString()
                )
        );
    }

}