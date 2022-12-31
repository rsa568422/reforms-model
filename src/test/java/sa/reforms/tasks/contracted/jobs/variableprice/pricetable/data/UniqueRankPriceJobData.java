package sa.reforms.tasks.contracted.jobs.variableprice.pricetable.data;

import sa.reforms.tasks.contracted.Quantity;
import sa.reforms.tasks.contracted.jobs.variableprice.pricetable.Range;
import sa.reforms.tasks.contracted.jobs.variableprice.pricetable.UniqueRankPriceJob;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UniqueRankPriceJobData extends PriceTableJobData {

    public static UniqueRankPriceJob GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_A(Quantity quantity) {
        return new UniqueRankPriceJob(
                GET_DEFAULT_INSURER_A(),
                GET_JOB_A(),
                quantity,
                GET_DEFAULT_PRICE_TABLE()
        );
    }

    public static UniqueRankPriceJob GET_DEFAULT_UNIQUE_RANK_PRICE_TABLE_JOB_B(Quantity quantity) {
        return new UniqueRankPriceJob(
                GET_DEFAULT_INSURER_B(),
                GET_JOB_B(),
                quantity,
                GET_DEFAULT_PRICE_TABLE()
        );
    }

    public static BigDecimal GET_DEFAULT_PRICE(Quantity quantity) {
        if (quantity.getMeasure() == 3D) {
            return new BigDecimal("30.00").setScale(2, RoundingMode.CEILING);
        } else if (quantity.getMeasure() == 12D) {
            return new BigDecimal("60.00").setScale(2, RoundingMode.CEILING);
        }
        return new BigDecimal("200.00").setScale(2, RoundingMode.CEILING);
    }

    public static Map<Range, Function<Double, BigDecimal>> GET_DEFAULT_PRICE_TABLE() {
        Map<Range, Function<Double, BigDecimal>> table = new HashMap<>();
        table.put(RANK_1(), (quantity) -> new BigDecimal("30.00")
                .setScale(2, RoundingMode.CEILING));
        table.put(RANK_2(), (quantity) -> new BigDecimal("5.00")
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.CEILING));
        table.put(RANK_3(), (quantity) -> new BigDecimal("4.00")
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.CEILING));
        return table;
    }

}
