package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.Range;

public class RangeData {

    /********************************************************************\
          -7  -6  -5  -4  -3  -2  -1   0   1   2   3   4   5   6   7
        ---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---
     N   (-inf                         0]
     P                                (0                          inf]
     1                   (-3           0]
     2                                (0           3]
     3                      (-2                2]
     A   (-inf                                                    inf]
    \********************************************************************/

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
