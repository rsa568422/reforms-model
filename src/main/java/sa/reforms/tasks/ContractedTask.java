package sa.reforms.tasks;

import sa.reforms.entities.Task;
import sa.reforms.tasks.quatities.Quantity;
import sa.reforms.tasks.contradtedjobs.ContractedJob;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.StringJoiner;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class ContractedTask extends Task {

    @NonNull
    private ContractedJob job;

    private Optional<Quantity> quantity = Optional.empty();

    public ContractedTask(@NonNull ContractedJob job) {
        this.job = job;
    }

    public void setJob(@NonNull ContractedJob job) {
        if (this.quantity.isPresent() && !job.valid(quantity))
            throw new InvalidParamsException("Invalid quantity for the new Job");
        this.job = job;
    }

    public void setQuantity(Quantity quantity) {
        if (!this.job.valid(Optional.ofNullable(quantity)))
            throw new InvalidParamsException("Invalid quantity");
        this.quantity = Optional.ofNullable(quantity);
    }

    @Override
    public BigDecimal getPrice() {
        return this.job.getPrize(this.quantity.map(Quantity::getMeasure));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContractedTask)) return false;

        ContractedTask task = (ContractedTask) o;

        if (!getJob().equals(task.getJob())) return false;
        if (getStatus() != task.getStatus()) return false;
        return getQuantity().equals(task.getQuantity());
    }

    @Override
    public int hashCode() {
        int result = getJob().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getQuantity().hashCode();
        return result;
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{") + 1);
        StringJoiner joiner = new StringJoiner(", ", "ContractedTask{ ", ", ");
        joiner.add(String.format("job:%s", this.job));
        this.quantity.ifPresent(quantity -> joiner.add(String.format("quantity:%s", quantity)));
        return super.toString().replace(target, joiner.toString());
    }

}
