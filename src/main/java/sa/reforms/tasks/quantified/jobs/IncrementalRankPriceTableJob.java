package sa.reforms.tasks.quantified.jobs;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.tasks.quantified.quatities.Quantity;
import sa.reforms.tasks.quantified.quatities.Range;

import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.function.Function;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IncrementalRankPriceTableJob extends PriceTableJob {

    public IncrementalRankPriceTableJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name, @NonNull Quantity quantity, @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, guild, name, quantity, priceTable);
    }

    public IncrementalRankPriceTableJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Quantity quantity, @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, job, quantity, priceTable);
    }

    @Override
    public BigDecimal getPrize(@NonNull Quantity quantity) {
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
