package sa.reforms.tasks.quantified.jobs;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;

import java.math.BigDecimal;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ConstantPrizeJob extends ContractedJob {

    private final BigDecimal prize;

    public ConstantPrizeJob(@NonNull Insurer insurer, @NonNull Guild guild,
                            @NonNull String name, @NonNull BigDecimal prize) {
        super(insurer, guild, name);
        this.prize = prize;
    }

    public ConstantPrizeJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull BigDecimal prize) {
        super(insurer, job);
        this.prize = prize;
    }

    @Override
    public BigDecimal getPrize() {
        return this.prize;
    }

}
