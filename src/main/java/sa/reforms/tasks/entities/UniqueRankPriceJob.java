package sa.reforms.tasks.entities;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.exceptions.InvalidParamsException;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class UniqueRankPriceJob extends PriceTableJob {

    public UniqueRankPriceJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name, @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, guild, name, priceTable);
    }

    public UniqueRankPriceJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        this(insurer, job.getGuild(), job.getName(), priceTable);
    }

    @Override
    public BigDecimal getPrize(Optional<Double> quantity) {
        return calculatePrice(quantity.orElseThrow(() -> new InvalidParamsException("Quantity can't be null")));
    }

    @Override
    public boolean valid(Optional<Quantity> quantity) {
        return quantity.map(qty -> qty.getMeasure().compareTo(0D) >= 0).orElse(false);
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "UniqueRankPriceJob");
    }

    private BigDecimal calculatePrice(Double quantity) {
        return this.priceTable
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(quantity))
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(function -> function.apply(quantity))
                .orElseThrow(() -> new InvalidParamsException("No rank registered for that quantity"))
                .setScale(2, RoundingMode.CEILING);
    }

}
