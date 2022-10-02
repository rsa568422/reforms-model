package sa.reforms.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import sa.reforms.enums.Guild;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Optional;

public class ProportionalJob extends ContractedJob {

    @NonNull
    @Getter
    @Setter
    private BigDecimal unitPrice;

    public ProportionalJob(@NonNull Insurer insurer, @NonNull Guild guild,
                           @NonNull String name, @NonNull BigDecimal unitPrice) {
        super(insurer, guild, name);
        this.unitPrice = unitPrice;
    }

    public ProportionalJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull BigDecimal unitPrice) {
        this(insurer, job.getGuild(), job.getName(), unitPrice);
    }

    @Override
    public BigDecimal getPrize(Optional<Double> quantity) {
        BigDecimal qty = BigDecimal.valueOf(quantity.orElseThrow(() -> new InvalidParamsException("")));
        return this.unitPrice.multiply(qty);
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "ProportionalJob");
    }

}
