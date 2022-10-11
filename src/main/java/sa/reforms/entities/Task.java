package sa.reforms.entities;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Task {

    public enum Status { PENDING, IN_PROGRESS, DONE, CANCELED }

    @NonNull
    private Status status = Status.PENDING;

    public abstract BigDecimal getPrice();

}
