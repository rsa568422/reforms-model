package sa.reforms.entities;

import lombok.NonNull;
import sa.reforms.enums.Guild;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class PriceTableJob extends ContractedJob {

    private final Map<Double, Function<Double, BigDecimal>> priceTable;

    public PriceTableJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name,
                         @NonNull Map<Double, Function<Double, BigDecimal>> priceTable) {
        super(insurer, guild, name);
        if (priceTable.isEmpty()) throw new InvalidParamsException("Price table can't be empty");
        this.priceTable = priceTable;
    }

    public PriceTableJob(@NonNull Insurer insurer, @NonNull Job job,
                         @NonNull Map<Double, Function<Double, BigDecimal>> priceTable) {
        this(insurer, job.getGuild(), job.getName(), priceTable);
    }

    @Override
    public BigDecimal getPrize(Optional<Double> quantity) {
        return calculatePrice(quantity.orElseThrow(() -> new InvalidParamsException("Quantity can't be null")));
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "PriceTableJob");
    }

    private BigDecimal calculatePrice(Double quantity) {
        if (quantity < 0) throw new InvalidParamsException("Quantity can't be negative");
        return this.priceTable
                .entrySet()
                .stream()
                .filter(entry -> quantity.compareTo(entry.getKey()) < 0)
                .min(Map.Entry.comparingByKey())
                .orElseThrow(() -> new InvalidParamsException("Price not found on table"))
                .getValue()
                .apply(quantity)
                .setScale(2, RoundingMode.CEILING);
    }

}
