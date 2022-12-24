package sa.reforms.incidences;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import sa.reforms.exceptions.InvalidParamsException;
import sa.reforms.insurers.Insurance;
import sa.reforms.insurers.Person;
import sa.reforms.insurers.Proficient;
import sa.reforms.tasks.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.*;

@ToString
@EqualsAndHashCode
public class Incidence {

    @Getter
    @NonNull
    private final String code;

    @Getter
    @NonNull
    private final Insurance insurance;

    private final List<Pair<Date, Proficient>> proficients = new LinkedList<>();

    @Getter
    private final Set<Task> tasks = new LinkedHashSet<>();

    @Getter
    private final Set<Person> contacts = new LinkedHashSet<>();

    public Incidence(@NonNull String code, @NonNull Insurance insurance, @NonNull Proficient proficient) {
        if (!insurance.getInsurer().equals(proficient.getInsurer()))
            throw new InvalidParamsException("Different insurer for insurance and proficient");
        this.code = code;
        this.insurance = insurance;
        this.proficients.add(0, new ImmutablePair<>(Date.from(Instant.now()), proficient));
    }

    public BigDecimal getAssessment() {
        return this.tasks
                .stream()
                .map(Task::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.CEILING);
    }

    public Proficient getActualProficient() {
        return this.proficients.get(0).getRight();
    }

    public List<Pair<Date, Proficient>> getProficientsHistorial() {
        return this.proficients;
    }

    public void setProficient(Proficient proficient) {
        this.proficients.add(0, new ImmutablePair<>(Date.from(Instant.now()), proficient));
    }

}
