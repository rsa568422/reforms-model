package sa.reforms.tasks.contracted.jobs;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.tasks.contracted.quatities.Quantity;

import java.math.BigDecimal;

import lombok.NonNull;

public abstract class VariablePrizeJob extends ContractedJob {

    private final Quantity quantity;

    public VariablePrizeJob(@NonNull Insurer insurer, @NonNull Guild guild,
                            @NonNull String name, @NonNull Quantity quantity) {
        super(insurer, guild, name);
        this.quantity = quantity;
    }

    public VariablePrizeJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Quantity quantity) {
        super(insurer, job);
        this.quantity = quantity;
    }

    @Override
    public BigDecimal getPrize() {
        return this.getPrize(this.quantity);
    }

    public abstract BigDecimal getPrize(@NonNull Quantity quantity);

}
