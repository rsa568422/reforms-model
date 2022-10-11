package sa.reforms.tasks.contracted;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;

import java.math.BigDecimal;

import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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

    public abstract BigDecimal getPrice();

}
