package sa.reforms.tasks.contracted.jobs.variableprice.pricetable;

import sa.reforms.insurers.Insurer;
import sa.reforms.tasks.contracted.jobs.Job;
import sa.reforms.tasks.contracted.Quantity;

import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.function.Function;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IncrementalRankPriceTableJob extends PriceTableJob {

    public IncrementalRankPriceTableJob(@NonNull Insurer insurer, @NonNull Guild guild,
                                        @NonNull String name, @NonNull Quantity quantity,
                                        @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, guild, name, quantity, priceTable);
    }

    public IncrementalRankPriceTableJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Quantity quantity,
                                        @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, job, quantity, priceTable);
    }

    @Override
    public BigDecimal getPrice(@NonNull Quantity quantity) {
        Range rank = this.priceTable
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(quantity.getMeasure()))
                .findAny()
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new InvalidParamsException("No rank registered for that quantity"));
        return this.priceTable
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().compareTo(rank) <= 0)
                .map(Map.Entry::getValue)
                .map(function -> function.apply(quantity.getMeasure()))
                .reduce(new BigDecimal("0.00"), BigDecimal::add)
                .setScale(2, RoundingMode.CEILING);
    }

}
