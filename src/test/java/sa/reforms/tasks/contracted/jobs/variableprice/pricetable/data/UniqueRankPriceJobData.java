package sa.reforms.tasks.contracted.jobs.variableprice.pricetable.data;

import sa.reforms.tasks.contracted.jobs.variableprice.pricetable.Range;
import sa.reforms.tasks.contracted.jobs.variableprice.pricetable.UniqueRankPriceJob;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static sa.reforms.tasks.contracted.Quantity.Unit.*;

public class UniqueRankPriceJobData extends PriceTableJobData {

    public static UniqueRankPriceJob GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A() {
        return new UniqueRankPriceJob(
                GET_DEFAULT_INSURER_A(),
                GET_DEFAULT_JOB_A(),
                CASE_A(M),
                GET_DEFAULT_PRICE_TABLE()
        );
    }

    public static UniqueRankPriceJob GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B() {
        return new UniqueRankPriceJob(
                GET_DEFAULT_INSURER_B(),
                GET_DEFAULT_JOB_B(),
                CASE_B(M2),
                GET_DEFAULT_PRICE_TABLE()
        );
    }

    public static Map<Range, Function<Double, BigDecimal>> GET_DEFAULT_PRICE_TABLE() {
        Map<Range, Function<Double, BigDecimal>> table = new HashMap<>();
        table.put(RANK_1(), (quantity) -> new BigDecimal("10.00")
                .setScale(2, RoundingMode.CEILING));
        table.put(RANK_2(), (quantity) -> new BigDecimal("5.00")
                .multiply(BigDecimal.valueOf(Math.min(quantity, RANK_2().getMax()) - RANK_2().getMin()))
                .setScale(2, RoundingMode.CEILING));
        table.put(RANK_3(), (quantity) -> new BigDecimal("3.00")
                .multiply(BigDecimal.valueOf(quantity - RANK_2().getMin()))
                .setScale(2, RoundingMode.CEILING));
        return table;
    }

}
