package sa.reforms.tasks.entities;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.exceptions.InvalidParamsException;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class DirectPriceJob extends ContractedJob {

    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name) {
        super(insurer, guild, name);
    }

    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Job job) {
        super(insurer, job);
    }

    @Override
    public BigDecimal getPrize(Optional<Double> quantity) {
        BigDecimal qty = BigDecimal.valueOf(quantity.orElseThrow(() -> new InvalidParamsException("Quantity can't be null")));
        return qty.setScale(2, RoundingMode.CEILING);
    }

    @Override
    public boolean valid(Optional<Quantity> quantity) {
        if (quantity.isPresent()) {
            Quantity qty = quantity.get();
            return qty.getUnit().equals(Quantity.Unit.EU) && qty.getMeasure().compareTo(0D) >= 0;
        }
        return false;
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "DirectPriceJob");
    }

}
