package sa.reforms.tasks.contracted.jobs.variableprice;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.tasks.contracted.Quantity;

import java.math.BigDecimal;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DirectPriceJob extends VariablePriceJob {

    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name, @NonNull Quantity quantity) {
        super(insurer, guild, name, quantity);
    }

    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Quantity quantity) {
        super(insurer, job, quantity);
    }

    @Override
    public BigDecimal getPrice(@NonNull Quantity quantity) {
        return null;
    }

}
