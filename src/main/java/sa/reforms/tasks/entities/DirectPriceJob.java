package sa.reforms.tasks.entities;

import lombok.NonNull;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;

import sa.reforms.enums.Guild;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class DirectPriceJob extends ContractedJob {

    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name) {
        super(insurer, guild, name);
    }

    public DirectPriceJob(@NonNull Insurer insurer, @NonNull Job job) {
        this(insurer, job.getGuild(), job.getName());
    }

    @Override
    public BigDecimal getPrize(Optional<Double> quantity) {
        BigDecimal qty = BigDecimal.valueOf(quantity.orElseThrow(() -> new InvalidParamsException("Quantity can't be null")));
        if (qty.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidParamsException("Quantity can't be negative");
        return qty.setScale(2, RoundingMode.CEILING);
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "DirectPriceJob");
    }

}
