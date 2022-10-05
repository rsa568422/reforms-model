package sa.reforms.entities;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class Person {

    @NonNull
    @Setter
    private Long id;

    @NonNull
    @Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return getId().equals(person.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Person{ ", " }");
        joiner.add(String.format("id:%s", this.id));
        joiner.add(String.format("name:%s", this.name));
        this.surname1.ifPresent(surname -> joiner.add(String.format("surname1:%s", surname)));
        this.surname2.ifPresent(surname -> joiner.add(String.format("surname2:%s", surname)));
        if (!this.phones.isEmpty()) joiner.add(String.format("phones:%s", this.phones));
        return joiner.toString();
    }

}
