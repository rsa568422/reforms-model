package sa.reforms.tasks.contracted.jobs.variableprice;

import sa.reforms.tasks.contracted.jobs.Job;
import sa.reforms.tasks.contracted.Quantity;

import sa.reforms.insurers.Insurer;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProportionalPriceJob extends VariablePriceJob {

    @NonNull
    private final BigDecimal unitPrice;

    public ProportionalPriceJob(@NonNull Insurer insurer, @NonNull Guild guild,
                                @NonNull String name, @NonNull Quantity quantity,
                                @NonNull BigDecimal unitPrice) {
        super(insurer, guild, name, quantity);
        this.unitPrice = unitPrice;
    }

    public ProportionalPriceJob(@NonNull Insurer insurer, @NonNull Job job,
                                @NonNull Quantity quantity, @NonNull BigDecimal unitPrice) {
        super(insurer, job, quantity);
        this.unitPrice = unitPrice;
    }

    @Override
    public BigDecimal getPrice(@NonNull Quantity quantity) {
        return this.unitPrice
                .multiply(BigDecimal.valueOf(quantity.getMeasure()))
                .setScale(2, RoundingMode.CEILING);
    }

}
