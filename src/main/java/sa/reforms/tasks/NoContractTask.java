package sa.reforms.tasks;

import sa.reforms.entities.Task;

import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.*;

@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NoContractTask extends Task {

    @NonNull
    private BigDecimal price;

    @NonNull
    @Getter
    private String description;

    public NoContractTask(@NonNull BigDecimal price, @NonNull String description) {
        if (price.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidParamsException("Price can't be equal or less than zero");
        if (description.trim().isBlank())
            throw new InvalidParamsException("Description can't be empty");
        this.price = price;
        this.description = description;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price.setScale(2, RoundingMode.CEILING);
    }

}
