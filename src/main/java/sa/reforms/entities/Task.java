package sa.reforms.entities;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public abstract class Task {

    public enum Status { PENDING, IN_PROGRESS, DONE, CANCELED }

    @NonNull
    @Getter
    @Setter
    private Status status = Status.PENDING;

    public abstract BigDecimal getPrice();

    @Override
    public String toString() {
        return String.format("Task{ status:%s }", this.status);
    }

}
