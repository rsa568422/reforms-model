package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.Range;

public class RangeData {

    public static Range RANK_1() {
        return new Range(0D, 5D);
    }

    public static Range RANK_2() {
        return new Range(5D, 10D);
    }

    public static Range RANK_3() {
        return new Range(10D, 15D);
    }

    public static Range RANK_F() {
        return new Range(3D, 7D);
    }

    public static Range RANK_N() {
        return new Range(-3D, 5D);
    }

}
