package sa.reforms.tasks.entities;

import lombok.NonNull;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;

import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class IncrementalRankPriceTableJob extends PriceTableJob {

    public IncrementalRankPriceTableJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name, @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, guild, name, priceTable);
    }

    public IncrementalRankPriceTableJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        this(insurer, job.getGuild(), job.getName(), priceTable);
    }

    @Override
    public BigDecimal getPrize(Optional<Double> quantity) {
        return calculatePrice(quantity.orElseThrow(() -> new InvalidParamsException("Quantity can't be null")));
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "IncrementalRankPriceTableJob");
    }

    private BigDecimal calculatePrice(Double quantity) {
        if (quantity < 0) throw new InvalidParamsException("Quantity can't be negative");
        Range rank = this.priceTable
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(quantity))
                .findAny()
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new InvalidParamsException("No rank registered for that quantity"));
        return this.priceTable
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().compareTo(rank) <= 0)
                .map(Map.Entry::getValue)
                .map(function -> function.apply(quantity))
                .reduce(new BigDecimal("0.00"), BigDecimal::add)
                .setScale(2, RoundingMode.CEILING);
    }

}
