package sa.reforms.entities;

import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class Proficient extends Person {

    @NonNull
    @Setter
    private Insurer insurer;

    private Optional<String> email = Optional.empty();

    public Proficient(@NonNull Long id, @NonNull String name, @NonNull Insurer insurer) {
        super(id, name);
        this.insurer = insurer;
    }

    public void setEmail(String email) {
        this.email = Optional.ofNullable(email);
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{") + 1);
        String replacement = String.format("Proficient{ insurer:%s, ", this.insurer);
        return super.toString().replace(target, replacement);
    }

}
