package sa.reforms.insurers.data;

import sa.reforms.insurers.Proficient;

import static sa.reforms.insurers.data.InsurerData.*;

public class ProficientData {

    public static Proficient GET_PROFICIENT_A() {
        return new Proficient(1L, "PROFICIENT A", GET_INSURER_A());
    }

    public static Proficient GET_PROFICIENT_B() {
        return new Proficient(2L, "PROFICIENT B", GET_INSURER_A());
    }

    public static Proficient GET_PROFICIENT_C() {
        return new Proficient(3L, "PROFICIENT C", GET_INSURER_B());
    }

    public static Proficient GET_PROFICIENT_D() {
        return new Proficient(4L, "PROFICIENT D", GET_INSURER_B());
    }

}
