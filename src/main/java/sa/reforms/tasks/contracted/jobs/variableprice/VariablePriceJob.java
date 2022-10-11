package sa.reforms.tasks.contracted.jobs.variableprice;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.tasks.contracted.ContractedJob;
import sa.reforms.tasks.contracted.Quantity;

import java.math.BigDecimal;

import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class VariablePriceJob extends ContractedJob {

    @NonNull
    private final Quantity quantity;

    public VariablePriceJob(@NonNull Insurer insurer, @NonNull Guild guild,
                            @NonNull String name, @NonNull Quantity quantity) {
        super(insurer, guild, name);
        this.quantity = quantity;
    }

    public VariablePriceJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull Quantity quantity) {
        super(insurer, job);
        this.quantity = quantity;
    }

    @Override
    public BigDecimal getPrice() {
        return this.getPrice(this.quantity);
    }

    public abstract BigDecimal getPrice(@NonNull Quantity quantity);

}
