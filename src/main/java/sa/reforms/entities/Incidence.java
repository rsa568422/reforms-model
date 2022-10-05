package sa.reforms.entities;

import sa.reforms.exceptions.InvalidParamsException;
import sa.reforms.tasks.entities.Task;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringJoiner;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class Incidence {

    @NonNull
    @Setter
    private String code;

    @NonNull
    @Setter
    private Insurance insurance;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Incidence)) return false;

        Incidence incidence = (Incidence) o;

        if (!getCode().equals(incidence.getCode())) return false;
        return getInsurance().equals(incidence.getInsurance());
    }

    @Override
    public int hashCode() {
        int result = getCode().hashCode();
        result = 31 * result + getInsurance().hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Incidence{ ", " }");
        joiner.add(String.format("code:%s", this.code));
        joiner.add(String.format("insurance:%s", this.insurance));
        joiner.add(String.format("proficient:%s", this.proficient));
        if (!this.tasks.isEmpty()) joiner.add(String.format("tasks:%s", this.tasks));
        if (!this.contacts.isEmpty()) joiner.add(String.format("phones:%s", this.contacts));
        return joiner.toString();
    }

}
