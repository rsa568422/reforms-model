package sa.reforms.tasks.quatities;

import sa.reforms.exceptions.InvalidParamsException;
import sa.reforms.tasks.exceptions.ComparisonException;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Range implements Comparable<Range> {

    @NonNull
    private final Double min;

    @NonNull
    private final Double max;

    public Range(@NonNull Double min, @NonNull Double max) {
        if (min.equals(max))
            throw new InvalidParamsException("Min and max can´t be equals");
        else if (min > max)
            throw new InvalidParamsException("Min can´t be greater than max");
        this.min = min;
        this.max = max;
    }

    public boolean contains(Double value) {
        return value > this.min && value <= this.max;
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
                this.min.equals(-Double.MAX_VALUE) ? "-inf" : this.min,
                this.max.equals(Double.MAX_VALUE) ? "+inf" : this.max);
    }

    @Override
    public int compareTo(Range other) {
        if (this.min < other.min) {
            if (this.max <= other.min) return -1;
            else if(this.max >= other.max)
                throw new ComparisonException("other is completely contained");
            else
                throw new ComparisonException("both of them are partial contained");
        } else if (this.min.equals(other.min)) {
            if (this.max.equals(other.max)) return 0;
            else if (this.max < other.max)
                throw new ComparisonException("this is completely contained");
            else
                throw new ComparisonException("other is completely contained");
        } else {
            if (this.min >= other.max) return 1;
            else if (this.max <= other.max)
                throw new ComparisonException("this is completely contained");
            else
                throw new ComparisonException("both of them are partial contained");
        }
    }

}