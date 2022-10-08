package sa.reforms.tasks.contracted.jobs.variableprice.pricetable;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.tasks.contracted.Quantity;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.function.Function;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UniqueRankPriceJob extends PriceTableJob {

    public UniqueRankPriceJob(@NonNull Insurer insurer, @NonNull Guild guild,
                              @NonNull String name, @NonNull Quantity quantity,
                              @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, guild, name, quantity, priceTable);
    }

    public UniqueRankPriceJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Quantity quantity,
                              @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, job, quantity, priceTable);
    }

    @Override
    public BigDecimal getPrice(@NonNull Quantity quantity) {
        return this.priceTable
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(quantity.getMeasure()))
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(function -> function.apply(quantity.getMeasure()))
                .orElseThrow(() -> new InvalidParamsException("No rank registered for that quantity"))
                .setScale(2, RoundingMode.CEILING);
    }

}
