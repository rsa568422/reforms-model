package sa.reforms.tasks.contracted.jobs;

import sa.reforms.tasks.contracted.quatities.Quantity;
import sa.reforms.tasks.contracted.quatities.Range;
import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;

import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

import lombok.NonNull;

public abstract class PriceTableJob extends VariablePrizeJob {

    protected final Map<Range, Function<Double, BigDecimal>> priceTable;

    public PriceTableJob(@NonNull Insurer insurer, @NonNull Guild guild,
                         @NonNull String name, @NonNull Quantity quantity,
                         @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, guild, name, quantity);
        if (priceTable.isEmpty()) throw new InvalidParamsException("Price table can't be empty");
        this.priceTable = priceTable;
    }

    public PriceTableJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Quantity quantity,
                         @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, job, quantity);
        this.priceTable = priceTable;
    }

}
