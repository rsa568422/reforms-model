package sa.reforms.tasks.contracted.data;

import sa.reforms.tasks.contracted.Quantity;


public class QuantityData {

    public static Quantity CASE_A(Quantity.Unit unit) {
        return new Quantity(3D, unit);
    }

    public static Quantity CASE_B(Quantity.Unit unit) {
        return new Quantity(12D, unit);
    }

    public static Quantity CASE_C(Quantity.Unit unit) {
        return new Quantity(50D, unit);
    }

}
