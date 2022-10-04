package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.Quantity;

import java.util.*;

public class QuantityData {

    public static Quantity CASE_A(Quantity.Unit unit) {
        return new Quantity(3.0, unit);
    }

    public static Quantity CASE_B(Quantity.Unit unit) {
        return new Quantity(12.0, unit);
    }

    public static Quantity CASE_C(Quantity.Unit unit) {
        return new Quantity(50.0, unit);
    }

    public static Quantity NEGATIVE(Quantity.Unit unit) {
        return new Quantity(-3.0, unit);
    }

    public static Optional<Quantity> EMPTY() {
        return Optional.empty();
    }

}
