package sa.reforms.insurers.data;

import sa.reforms.insurers.Insurance;

import static sa.reforms.insurers.data.ClientData.*;
import static sa.reforms.insurers.data.InsurerData.*;
import static sa.reforms.insurers.data.PropertyData.*;

public class InsuranceData {

    public static Insurance GET_INSURANCE_A() {
        return new Insurance("CODE A", GET_INSURER_A(), GET_CLIENT_A(), GET_PROPERTY_A());
    }

    public static Insurance GET_INSURANCE_B() {
        return new Insurance("CODE B", GET_INSURER_B(), GET_CLIENT_B(), GET_PROPERTY_B());
    }

}
