package sa.reforms.tasks.entities;

import sa.reforms.exceptions.InvalidParamsException;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.StringJoiner;

@Getter
public class Task {

    public enum TaskStatus { PENDING, DONE, CANCELED, REOPENED }

    @NonNull
    @Setter
    private ContractedJob job;

    @NonNull
    @Setter
    private TaskStatus status = TaskStatus.PENDING;

    private Optional<Quantity> quantity = Optional.empty();

    public Task(@NonNull ContractedJob job) {
        this.job = job;
    }

    public Task(@NonNull ContractedJob job, Quantity quantity) {
        this(job);
        if (job instanceof DirectPriceJob && isNotEu(quantity))
            throw new InvalidParamsException("Quantity only can be EU for DirectPriceJob");
        this.quantity = Optional.ofNullable(quantity);
    }

    public void setQuantity(Quantity quantity) {
        if (job instanceof DirectPriceJob && isNotEu(quantity))
            throw new InvalidParamsException("Quantity only can be EU for DirectPriceJob");
        this.quantity = Optional.ofNullable(quantity);
    }

    public BigDecimal getPrice() {
        return this.job.getPrize(this.quantity.map(Quantity::getMeasure));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

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
        StringJoiner joiner = new StringJoiner(", ", "Task{ ", " }");
        joiner.add(String.format("job:%s", this.job));
        joiner.add(String.format("status:%s", this.status));
        this.quantity.ifPresent(quantity -> joiner.add(String.format("quantity:%s", this.quantity)));
        return joiner.toString();
    }

    private static boolean isNotEu(Quantity quantity) {
        Optional<Quantity> optionalQuantity = Optional.ofNullable(quantity);
        return optionalQuantity.map(qty -> !qty.getUnit().equals(Quantity.Unit.EU)).orElse(false);
    }

}
