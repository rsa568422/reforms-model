package sa.reforms.tasks.contracted;

import sa.reforms.exceptions.InvalidParamsException;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
public class Quantity {

    public enum Unit { M, M2, H, EU }

    @NonNull
    private final Double measure;

    @NonNull
    private final Unit unit;

    public Quantity(@NonNull Double measure, @NonNull Unit unit) {
        if (measure.compareTo(0D) <= 0) throw new InvalidParamsException("Measure can't be equals or less than zero");
        this.measure = measure;
        this.unit = unit;
    }

}
