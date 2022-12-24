package sa.reforms.incidences.data;

import sa.reforms.incidences.Incidence;

import static sa.reforms.insurers.data.InsuranceData.*;
import static sa.reforms.insurers.data.ProficientData.*;

public class IncidenceData {

    public static Incidence GET_INCIDENCE_A() {
        return new Incidence("CODE_A", GET_INSURANCE_A(), GET_PROFICIENT_A());
    }

    public static Incidence GET_INCIDENCE_B() {
        return new Incidence("CODE_B", GET_INSURANCE_B(), GET_PROFICIENT_B());
    }

}
