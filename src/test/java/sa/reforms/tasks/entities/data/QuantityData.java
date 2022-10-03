package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.Quantity;

public class QuantityData {

    public static Quantity QUATITY_M() {
        return new Quantity(3.0, Quantity.Unit.H);
    }

    public static Quantity QUATITY_M2() {
        return new Quantity(12.0, Quantity.Unit.M);
    }

    public static Quantity QUATITY_H() {
        return new Quantity(50.0, Quantity.Unit.M2);
    }

    public static Quantity QUATITY_E() {
        return new Quantity(38.75, Quantity.Unit.EU);
    }

}
