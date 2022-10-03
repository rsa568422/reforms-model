package sa.reforms.tasks.entities.data;

import sa.reforms.tasks.entities.Quantity;

import java.util.*;

public class QuantityData {

    public static List<Optional<Quantity>> QUANTITIES(Quantity.Unit unit) {
        return Arrays.asList(
                Optional.of(new Quantity(-3.0, unit)),
                Optional.of(new Quantity(3.0, unit)),
                Optional.of(new Quantity(12.0, unit)),
                Optional.of(new Quantity(50.0, unit)),
                Optional.empty()
        );
    }

}
