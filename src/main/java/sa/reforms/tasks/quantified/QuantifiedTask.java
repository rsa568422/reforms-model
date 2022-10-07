package sa.reforms.tasks.quantified;

import sa.reforms.entities.Task;
import sa.reforms.tasks.quantified.jobs.ContractedJob;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class QuantifiedTask extends Task {

    @NonNull
    private final ContractedJob job;

    public QuantifiedTask(@NonNull ContractedJob job) {
        this.job = job;
    }

    @Override
    public BigDecimal getPrice() {
        return this.job.getPrize().setScale(2, RoundingMode.CEILING);
    }

}
