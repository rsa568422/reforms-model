package sa.reforms.entities.data;

import sa.reforms.entities.PriceTableJob;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PriceTableJobData extends JobData {

    public static PriceTableJob PT_JOB_PAINTWORK_PLASTIC() {
        Map<Double, Function<Double, BigDecimal>> map = new HashMap<>();
        map.put(5.0, quantity -> new BigDecimal("20.00"));
        map.put(15.0, quantity -> BigDecimal.valueOf(quantity).multiply(new BigDecimal("4.50")));
        map.put(Double.MAX_VALUE, quantity -> BigDecimal.valueOf(quantity).multiply(new BigDecimal("4.00")));
        return new PriceTableJob(InsurerData.INSURER_A, JOB_PAINTWORK_PLASTIC, map);
    }

}
