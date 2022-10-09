package sa.reforms.tasks.contracted.jobs.variableprice.pricetable.data;

import sa.reforms.tasks.contracted.jobs.variableprice.pricetable.Range;

public interface RangeData {

    static Range RANK_N() {
        return new Range(-Double.MAX_VALUE, 0D);
    }

    static Range RANK_P() {
        return new Range(0D, Double.MAX_VALUE);
    }

    static Range RANK_1() {
        return new Range(-3D, 0D);
    }

    static Range RANK_2() {
        return new Range(0D, 3D);
    }

    static Range RANK_3() {
        return new Range(-2D, 2D);
    }

    static Range RANK_A() {
        return new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
    }

}
