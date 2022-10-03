package sa.reforms.tasks.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import sa.reforms.tasks.enums.TaskStatus;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.StringJoiner;

@Getter
public class Task {

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
        this.quantity = Optional.ofNullable(quantity);
    }

    public void setQuantity(Quantity quantity) {
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
        return getQuantity().isPresent() ? getQuantity().equals(task.getQuantity()) : task.getQuantity().isEmpty();
    }

    @Override
    public int hashCode() {
        int result = getJob().hashCode();
        result = 31 * result + (getQuantity().isPresent() ? getQuantity().hashCode() : 0);
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
