package sa.reforms.tasks.entities;

import sa.reforms.exceptions.InvalidParamsException;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Range implements Comparable<Range> {

    @NonNull
    private Double min;

    @NonNull
    private Double max;

    public Range(@NonNull Double min, @NonNull Double max) {
        if (min.compareTo(max) >= 0) throw new InvalidParamsException("Min can´t be greater than max");
        this.min = min;
        this.max = max;
    }

    public boolean contains(Double value) {
        return value.compareTo(this.min) > 0 && value.compareTo(this.max) <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Range)) return false;

        Range range = (Range) o;

        if (!getMin().equals(range.getMin())) return false;
        return getMax().equals(range.getMax());
    }

    @Override
    public int hashCode() {
        int result = getMin().hashCode();
        result = 31 * result + getMax().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s]",
                this.min.equals(Double.MIN_VALUE) ? "-inf" : this.min,
                this.max.equals(Double.MAX_VALUE) ? "+inf" : this.max);
    }

    @Override
    public int compareTo(Range other) {
        if (this.max <= other.min) return -1;
        if (this.min >= other.max) return 1;
        return 0;
    }

}
