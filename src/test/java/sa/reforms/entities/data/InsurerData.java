package sa.reforms.entities.data;

import sa.reforms.entities.Insurer;

public class InsurerData {

    public static String GET_DEFAULT_NAME_A() {
        return "INSURER_A";
    }

    public static String GET_DEFAULT_NAME_B() {
        return "INSURER_B";
    }

    public static String GET_DEFAULT_PHONE_A() {
        return "900000001";
    }

    public static String GET_DEFAULT_PHONE_B() {
        return "900000002";
    }

    public static Insurer GET_DEFAULT_INSURER_A() {
        return new Insurer(GET_DEFAULT_NAME_A());
    }

    public static Insurer GET_DEFAULT_INSURER_B() {
        return new Insurer(GET_DEFAULT_NAME_B());
    }

    public static Insurer GET_DEFAULT_INSURER_A_WITH_PRONE() {
        Insurer insurer = GET_DEFAULT_INSURER_A();
        insurer.getPhones().add(GET_DEFAULT_PHONE_A());
        return insurer;
    }

    public static Insurer GET_DEFAULT_INSURER_B_WITH_PRONE() {
        Insurer insurer = GET_DEFAULT_INSURER_B();
        insurer.getPhones().add(GET_DEFAULT_PHONE_B());
        return insurer;
    }

}
