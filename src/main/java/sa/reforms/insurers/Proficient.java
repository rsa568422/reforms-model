package sa.reforms.insurers;

import java.util.Optional;

import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Proficient extends Person {

    @NonNull
    private final Insurer insurer;

    private Optional<String> email = Optional.empty();

    public Proficient(@NonNull Long id, @NonNull String name, @NonNull Insurer insurer) {
        super(id, name);
        this.insurer = insurer;
    }

    public Proficient(@NonNull Person person, @NonNull Insurer insurer) {
        this(person.getId(), person.getName(), insurer);
    }

    public void setEmail(String email) {
        this.email = Optional.ofNullable(email);
    }

}
