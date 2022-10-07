package sa.reforms.tasks.contracted.jobs;

import java.math.BigDecimal;
import java.math.RoundingMode;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.tasks.contracted.quatities.Quantity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class ProportionalPriceJob extends VariablePrizeJob {

    @NonNull
    @Getter
    @Setter
    private BigDecimal unitPrice;

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
    public BigDecimal getPrize(@NonNull Quantity quantity) {
        return this.unitPrice
                .multiply(BigDecimal.valueOf(quantity.getMeasure()))
                .setScale(2, RoundingMode.CEILING);
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "ProportionalPriceJob");
    }

}
