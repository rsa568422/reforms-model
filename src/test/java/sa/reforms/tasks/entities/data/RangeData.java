package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.Range;

public class RangeData {

    public static Range RANK_N() {
        return new Range(-Double.MAX_VALUE, 0D);
    }

    public static Range RANK_P() {
        return new Range(0D, Double.MAX_VALUE);
    }

    public static Range RANK_1() {
        return new Range(-3D, 0D);
    }

    public static Range RANK_2() {
        return new Range(0D, 3D);
    }

    public static Range RANK_3() {
        return new Range(-2D, 2D);
    }

    public static Range RANK_A() {
        return new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
    }

}
