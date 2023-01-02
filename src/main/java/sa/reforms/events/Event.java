package sa.reforms.events;

import java.util.Date;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
public class Event implements Comparable<Event> {

    @NonNull
    private final Date date;

    public Event(@NonNull Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Event event) {
        return this.date.compareTo(event.getDate());
    }

}
