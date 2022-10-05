package sa.reforms;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sa.reforms.entities.*;
public class Main {

    public static void main(String... args) {

        Set<Insurer> insurers = initInsurers();
        System.out.println("\n#Aseguradoras");
        insurers.forEach(System.out::println);

        Set<Proficient> proficients = initProficients(insurers);
        System.out.println("\n#Peritos");
        proficients.forEach(System.out::println);

        Set<Insurance> insurances = initInsurances(insurers);
        System.out.println("\n#Pólizas");
        insurances.forEach(System.out::println);

        Set<Incidence> incidences = initIncidences(insurances, proficients);
        System.out.println("\n#Siniestros");
        incidences.forEach(System.out::println);

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

    private static Set<Insurance> initInsurances(Set<Insurer> insurers) {
        return insurers
                .stream()
                .map(Main::initInsurances)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    private static List<Insurance> initInsurances(Insurer insurer) {
        return Arrays.asList(initInsurance(insurer, "A"), initInsurance(insurer, "B"));
    }

    private static Insurance initInsurance(Insurer insurer, String suffix) {
        return new Insurance(String.format("%s_%s", insurer.getName(), suffix), insurer, initClient(suffix), initProperty(suffix));
    }

    private static Property initProperty(String suffix) {
        return new Property("Dirección_".concat(suffix));
    }

    private static Client initClient(String suffix) {
        return new Client((long) suffix.hashCode(), "Cliente_".concat(suffix));
    }

    private static Set<Proficient> initProficients(Set<Insurer> insurers) {
        return insurers
                .stream()
                .map(Main::initProficients)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    private static List<Proficient> initProficients(Insurer insurer) {
        return Arrays.asList(initProficient(insurer, "A"), initProficient(insurer, "B"));
    }

    private static Proficient initProficient(Insurer insurer, String suffix) {
        return new Proficient((long) suffix.hashCode(), "Perito_".concat(suffix), insurer);
    }

    private static Set<Incidence> initIncidences(Set<Insurance> insurances, Set<Proficient> proficients) {
        return insurances
                .stream()
                .map(insurance -> initIncidences(insurance, proficients))
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    private static List<Incidence> initIncidences(Insurance insurance, Set<Proficient> proficients) {
        return proficients
                .stream()
                .filter(proficient -> proficient.getInsurer().equals(insurance.getInsurer()))
                .map(proficient -> initIncidence(insurance, proficient))
                .collect(Collectors.toList());
    }

    private static Incidence initIncidence(Insurance insurance, Proficient proficient) {
        return new Incidence(String.format("%s-%s", insurance.getCode(), proficient.getId() * insurance.hashCode()), insurance, proficient);
    }

}
