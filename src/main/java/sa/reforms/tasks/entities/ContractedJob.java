package sa.reforms.tasks.entities;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class ContractedJob extends Job {

    @NonNull
    private final Insurer insurer;

    public ContractedJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name) {
        super(guild, name);
        this.insurer = insurer;
    }

    public ContractedJob(@NonNull Insurer insurer, @NonNull Job job) {
        this(insurer, job.getGuild(), job.getName());
    }

    public abstract BigDecimal getPrize(Optional<Double> quantity);

    public abstract boolean valid(Optional<Quantity> quantity);

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{") + 1);
        String replacement = String.format("ContractedJob{ insurer: %s, ", this.insurer);
        return super.toString().replace(target, replacement);
    }

}
