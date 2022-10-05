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
public class Insurer {

    @NonNull
    @Setter
    private String name;

    private final Set<String> phones = new LinkedHashSet<>();

    private Optional<String> fax = Optional.empty();

    private Optional<String> email = Optional.empty();

    public void setFax(String fax) {
        this.fax = Optional.ofNullable(fax);
    }

    public void setEmail(String email) {
        this.email = Optional.ofNullable(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Insurer)) return false;

        Insurer insurer = (Insurer) o;

        return getName().equals(insurer.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Insurer{ ", " }");
        joiner.add(String.format("name:%s", this.name));
        if (!this.phones.isEmpty()) joiner.add(String.format("phones:%s", this.phones));
        this.fax.ifPresent(fax -> joiner.add(String.format("fax:%s", fax)));
        this.email.ifPresent(email -> joiner.add(String.format("fax:%s", email)));
        return joiner.toString();
    }

}
