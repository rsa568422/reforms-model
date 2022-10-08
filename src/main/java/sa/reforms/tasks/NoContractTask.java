package sa.reforms.tasks;

import sa.reforms.entities.Task;

import java.math.BigDecimal;

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
        this.price = price;
        this.description = description;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

}
