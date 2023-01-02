package sa.reforms.events;

import sa.reforms.workers.Group;

import java.util.Date;
import java.util.Optional;

import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Appointment extends Event {

    public enum Status { CONFIRMED, UNCONFIRMED }

    private final Optional<Call> call;

    @Setter
    private Group group;

    public Appointment(@NonNull Date date) {
        super(date);
        this.call = Optional.empty();
    }

    public Appointment(@NonNull Date date, @NonNull Call call) {
        super(date);
        this.call = Optional.of(call);
    }

}
