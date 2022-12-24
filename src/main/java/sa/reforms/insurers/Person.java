package sa.reforms.insurers;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Person {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    private Optional<String> surname1 = Optional.empty();

    private Optional<String> surname2 = Optional.empty();

    private final Set<String> phones = new LinkedHashSet<>();

    public void setSurname1(String surname1) {
        this.surname1 = Optional.ofNullable(surname1);
    }

    public void setSurname2(String surname2) {
        this.surname2 = Optional.ofNullable(surname2);
    }

}
