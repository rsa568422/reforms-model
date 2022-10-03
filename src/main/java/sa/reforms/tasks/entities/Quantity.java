package sa.reforms.tasks.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Quantity {

    public enum Unit { EURO, M, M2, H }

    @NonNull
    private Double measure;

    @NonNull
    private Unit unit;

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
