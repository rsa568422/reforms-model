package sa.reforms.tasks.contracted.jobs.variableprice;

import sa.reforms.insurers.Insurer;
import sa.reforms.tasks.contracted.jobs.Job;
import sa.reforms.tasks.contracted.Quantity;

import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DirectPriceJob extends VariablePriceJob {

    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name, @NonNull Quantity quantity) {
        super(insurer, guild, name, quantity);
        if (quantity.getUnit() != Quantity.Unit.EU) throw new InvalidParamsException("Unit must be EU");
    }

    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Quantity quantity) {
        super(insurer, job, quantity);
        if (quantity.getUnit() != Quantity.Unit.EU) throw new InvalidParamsException("Unit must be EU");
    }

    @Override
    public BigDecimal getPrice(@NonNull Quantity quantity) {
        return BigDecimal.valueOf(this.getQuantity().getMeasure()).setScale(2, RoundingMode.CEILING);
    }

}
