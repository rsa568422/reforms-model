package sa.reforms.tasks.contradtedjobs;

import sa.reforms.tasks.quatities.Quantity;
import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class ProportionalPriceJob extends ContractedJob {

    @NonNull
    @Getter
    @Setter
    private BigDecimal unitPrice;

    public ProportionalPriceJob(@NonNull Insurer insurer, @NonNull Guild guild,
                                @NonNull String name, @NonNull BigDecimal unitPrice) {
        super(insurer, guild, name);
        this.unitPrice = unitPrice;
    }

    public ProportionalPriceJob(@NonNull Insurer insurer, @NonNull Job job, @NonNull BigDecimal unitPrice) {
        this(insurer, job.getGuild(), job.getName(), unitPrice);
    }

    @Override
    public BigDecimal getPrize(Optional<Double> quantity) {
        BigDecimal qty = BigDecimal.valueOf(quantity.orElseThrow(() -> new InvalidParamsException("Quantity can't be null")));
        return this.unitPrice.multiply(qty).setScale(2, RoundingMode.CEILING);
    }

    @Override
    public boolean valid(Optional<Quantity> quantity) {
        return quantity.map(qty -> qty.getMeasure().compareTo(0D) >= 0).orElse(false);
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "ProportionalPriceJob");
    }

}
