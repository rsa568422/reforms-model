package sa.reforms.insurers;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Insurer {

    @NonNull
    private final String name;

    private final Set<String> phones = new LinkedHashSet<>();

    private Optional<String> fax = Optional.empty();

    private Optional<String> email = Optional.empty();

    public void setFax(String fax) {
        this.fax = Optional.ofNullable(fax);
    }

    public void setEmail(String email) {
        this.email = Optional.ofNullable(email);
    }

}
