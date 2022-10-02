package sa.reforms.entities;

import lombok.NonNull;
import sa.reforms.enums.Guild;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class UniqueRankPriceTableJob extends PriceTableJob {

    public UniqueRankPriceTableJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name, @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, guild, name, priceTable);
    }

    public UniqueRankPriceTableJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        this(insurer, job.getGuild(), job.getName(), priceTable);
    }

    @Override
    public BigDecimal getPrize(Optional<Double> quantity) {
        return calculatePrice(quantity.orElseThrow(() -> new InvalidParamsException("Quantity can't be null")));
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "UniqueRankPriceTableJob");
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
