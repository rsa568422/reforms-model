package sa.reforms.tasks.contradtedjobs;

import lombok.NonNull;
import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.tasks.quatities.Quantity;

import java.math.BigDecimal;
import java.util.Optional;

public class VariablePrizeJob extends ContractedJob {

    public VariablePrizeJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name) {
        super(insurer, guild, name);
    }

    public VariablePrizeJob(@NonNull Insurer insurer, @NonNull Job job) {
        super(insurer, job);
    }

    @Override
    public BigDecimal getPrize(Optional<Double> quantity) {
        return null;
    }

    @Override
    public boolean valid(Optional<Quantity> quantity) {
        return false;
    }

}
