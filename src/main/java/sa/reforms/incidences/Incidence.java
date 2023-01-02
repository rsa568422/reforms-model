package sa.reforms.incidences;

import sa.reforms.events.Appointment;
import sa.reforms.events.Call;
import sa.reforms.events.Event;

import sa.reforms.insurers.Insurance;
import sa.reforms.insurers.Person;
import sa.reforms.insurers.Proficient;

import sa.reforms.tasks.Task;

import sa.reforms.exceptions.InvalidParamsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
public class Incidence {

    @Getter
    @NonNull
    private final String code;

    @Getter
    @NonNull
    private final Insurance insurance;

    private final List<Pair<Date, Proficient>> proficients = new LinkedList<>();

    private final List<Event> events = new LinkedList<>();

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

    public void setProficient(@NonNull Proficient proficient) {
        if (!insurance.getInsurer().equals(proficient.getInsurer()))
            throw new InvalidParamsException("Different insurer for insurance and proficient");
        this.proficients.add(0, new ImmutablePair<>(Date.from(Instant.now()), proficient));
    }

    public List<Event> getEvents() {
        return this.events
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<Call> getCalls() {
        return this.events
                .stream()
                .map(Incidence::getCall)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointments() {
        return this.events
                .stream()
                .map(Incidence::getAppointment)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public void addEvent(@NonNull Event event) {
        this.events.add(0, event);
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

    private static Optional<Call> getCall(Event event) {
        Optional<Call> call;
        if (event instanceof Call) {
            call = Optional.of((Call) event);
        } else if (event instanceof Appointment) {
            Appointment appointment = (Appointment) event;
            call = appointment.getCall();
        } else {
            call = Optional.empty();
        }
        return call;
    }

    private static Optional<Appointment> getAppointment(Event event) {
        Optional<Appointment> appointment;
        if (event instanceof Appointment) {
            appointment = Optional.of((Appointment) event);
        } else {
            appointment = Optional.empty();
        }
        return appointment;
    }

}
