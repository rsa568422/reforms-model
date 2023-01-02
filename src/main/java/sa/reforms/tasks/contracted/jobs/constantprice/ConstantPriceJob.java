package sa.reforms.tasks.contracted.jobs.constantprice;

import sa.reforms.tasks.contracted.jobs.Job;
import sa.reforms.tasks.contracted.ContractedJob;

import sa.reforms.insurers.Insurer;

import java.math.BigDecimal;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ConstantPriceJob extends ContractedJob {

    private final BigDecimal price;

    public ConstantPriceJob(@NonNull Insurer insurer, @NonNull Guild guild,
                            @NonNull String name, @NonNull BigDecimal price) {
        super(insurer, guild, name);
        this.price = price;
    }

    public ConstantPriceJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull BigDecimal price) {
        super(insurer, job);
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

}
