package sa.reforms.events;

import java.util.Date;
import java.util.Optional;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
public class Event implements Comparable<Event> {

    @NonNull
    private final Date date;

    private Optional<String> comments;

    public Event(@NonNull Date date) {
        this.date = date;
        this.comments = Optional.empty();
    }

    public Event(@NonNull Date date, @NonNull String comments) {
        this.date = date;
        this.comments = Optional.of(comments);
    }

    @Override
    public int compareTo(Event event) {
        return this.date.compareTo(event.getDate());
    }

    public void setComments(@NonNull String comments) {
        this.comments = Optional.of(comments);
    }

}
