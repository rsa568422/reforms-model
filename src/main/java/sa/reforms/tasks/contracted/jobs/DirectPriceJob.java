package sa.reforms.tasks.contracted.jobs;

import java.math.BigDecimal;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.tasks.contracted.quatities.Quantity;

import lombok.NonNull;

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

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "DirectPriceJob");
    }

}
