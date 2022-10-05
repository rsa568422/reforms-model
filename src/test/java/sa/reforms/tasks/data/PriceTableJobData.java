package sa.reforms.tasks.data;

import sa.reforms.tasks.quatities.Range;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PriceTableJobData extends ContractedJobData {

    public static Map<Range, Function<Double, BigDecimal>> RANKS_MAP() {
        Map<Range, Function<Double, BigDecimal>> map = new HashMap<>();
        map.put(new Range(0.0, 5.0), quantity -> new BigDecimal("20.00"));
        map.put(new Range(5.0, 15.0), quantity -> BigDecimal.valueOf(Math.min(quantity, 15) - 5.0).multiply(new BigDecimal("3.50")));
        map.put(new Range(15.0, Double.MAX_VALUE), quantity -> BigDecimal.valueOf(quantity - 15).multiply(new BigDecimal("3.00")));
        return map;
    }

}
