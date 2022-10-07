package sa.reforms.tasks.quantified.jobs;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.tasks.quantified.quatities.Quantity;

import java.math.BigDecimal;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DirectPriceJob extends VariablePrizeJob {


    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name, @NonNull Quantity quantity) {
        super(insurer, guild, name, quantity);
    }

    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Quantity quantity) {
        super(insurer, job, quantity);
    }

    @Override
    public BigDecimal getPrize(@NonNull Quantity quantity) {
        return null;
    }

}
