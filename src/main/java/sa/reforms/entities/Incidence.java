package sa.reforms.entities;

import sa.reforms.exceptions.InvalidParamsException;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
public class Incidence {

    @NonNull
    private final String code;

    @NonNull
    private final Insurance insurance;

    @NonNull
    @Setter
    private Proficient proficient;

    private final Set<Task> tasks = new LinkedHashSet<>();

    private final Set<Person> contacts = new LinkedHashSet<>();

    public Incidence(@NonNull String code, @NonNull Insurance insurance, @NonNull Proficient proficient) {
        if (!insurance.getInsurer().equals(proficient.getInsurer()))
            throw new InvalidParamsException("Different insurer for insurance and proficient");
        this.code = code;
        this.insurance = insurance;
        this.proficient = proficient;
    }

}
