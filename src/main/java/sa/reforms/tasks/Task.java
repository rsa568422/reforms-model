package sa.reforms.tasks;

import sa.reforms.tasks.quatities.Quantity;
import sa.reforms.tasks.contradtedjobs.ContractedJob;
import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.StringJoiner;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class Task {

    public enum TaskStatus { PENDING, IN_PROGRESS, DONE, CANCELED }

    @NonNull
    private ContractedJob job;

    @NonNull
    @Setter
    private TaskStatus status = TaskStatus.PENDING;

    private Optional<Quantity> quantity = Optional.empty();

    public Task(@NonNull ContractedJob job) {
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

}
