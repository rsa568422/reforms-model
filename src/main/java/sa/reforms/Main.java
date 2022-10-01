package sa.reforms;

import sa.reforms.entities.Insurer;

import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String... args) {

        Set<Insurer> insurers = initInsurers();

        System.out.println("\nAseguradoras:");
        insurers.forEach(System.out::println);

        System.out.println("\nAgregar Pólizas a las aseguradoras");

    }

    private static Set<Insurer> initInsurers() {
        Insurer ocaso = new Insurer("Ocaso");
        ocaso.getPhones().add("901000001");
        ocaso.getPhones().add("901000002");
        ocaso.setFax("901000003");

        Insurer mapfre = new Insurer("Mapfre");
        mapfre.setEmail("email@mapfre.com");

        Set<Insurer> insurers = new LinkedHashSet<>();
        insurers.add(ocaso);
        insurers.add(mapfre);

        return insurers;
    }

}
