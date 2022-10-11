package sa.reforms.tasks.contracted;

import sa.reforms.entities.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ContractedTask extends Task {

    @NonNull
    private final ContractedJob job;

    public ContractedTask(@NonNull ContractedJob job) {
        this.job = job;
    }

    @Override
    public BigDecimal getPrice() {
        return this.job.getPrice().setScale(2, RoundingMode.CEILING);
    }

}
