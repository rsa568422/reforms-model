package sa.reforms.entities.data;

import sa.reforms.entities.PriceTableJob;
import sa.reforms.entities.Range;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PriceTableJobData extends JobData {

    public static PriceTableJob PT_JOB_PAINTWORK_PLASTIC() {
        Map<Range, Function<Double, BigDecimal>> map = new HashMap<>();
        map.put(new Range(0.0, 5.0), quantity -> new BigDecimal("20.00"));
        map.put(new Range(5.0, 15.0), quantity -> BigDecimal.valueOf(Math.min(quantity, 15) - 5.0).multiply(new BigDecimal("3.50")));
        map.put(new Range(15.0, Double.MAX_VALUE), quantity -> BigDecimal.valueOf(quantity - 15).multiply(new BigDecimal("3.00")));
        return new PriceTableJob(InsurerData.INSURER_A, JOB_PAINTWORK_PLASTIC, map);
    }

}
