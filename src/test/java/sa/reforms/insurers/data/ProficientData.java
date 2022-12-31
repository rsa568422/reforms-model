package sa.reforms.insurers.data;

import sa.reforms.insurers.Proficient;

import static sa.reforms.insurers.data.InsurerData.*;
import static sa.reforms.insurers.data.PersonData.*;

public class ProficientData {

    public static Proficient GET_PROFICIENT_E() {
        return new Proficient(GET_PERSON_E(), GET_INSURER_A());
    }

    public static Proficient GET_PROFICIENT_F() {
        return new Proficient(GET_PERSON_F(), GET_INSURER_A());
    }

    public static Proficient GET_PROFICIENT_G() {
        return new Proficient(GET_PERSON_G(), GET_INSURER_B());
    }

    public static Proficient GET_PROFICIENT_H() {
        return new Proficient(GET_PERSON_H(), GET_INSURER_B());
    }

}
