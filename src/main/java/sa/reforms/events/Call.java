package sa.reforms.events;

import sa.reforms.insurers.Person;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Call extends Event {

    public enum Status { CONTACTED, REFUSED }

    public enum Direction { INCOMING, OUTGOING }

    private Status status = Status.REFUSED;

    private final Direction direction;

    private final Person contact;

    public Call(@NonNull Date date, @NonNull Direction direction, @NonNull Person contact) {
        super(date);
        this.direction = direction;
        this.contact = contact;
    }

    public void setContacted() {
        this.status = Status.CONTACTED;
    }

}
