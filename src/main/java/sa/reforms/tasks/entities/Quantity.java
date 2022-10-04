package sa.reforms.tasks.entities;

import sa.reforms.exceptions.InvalidParamsException;

import lombok.Getter;
import lombok.NonNull;

@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;

        Quantity quantity = (Quantity) o;

        if (!getMeasure().equals(quantity.getMeasure())) return false;
        return getUnit() == quantity.getUnit();
    }

    @Override
    public int hashCode() {
        int result = getMeasure().hashCode();
        result = 31 * result + getUnit().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("Quantity{measure:%f, unit:%s}", this.measure, this.unit);
    }

}
