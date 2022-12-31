package sa.reforms.insurers.data;

import sa.reforms.insurers.Client;

import static sa.reforms.insurers.data.PersonData.*;

public class ClientData {

    public static Client GET_CLIENT_A() {
        return new Client(GET_PERSON_A());
    }

    public static Client GET_CLIENT_B() {
        return new Client(GET_PERSON_B());
    }

    public static Client GET_CLIENT_C() {
        return new Client(GET_PERSON_C());
    }

    public static Client GET_CLIENT_D() {
        return new Client(GET_PERSON_D());
    }

}
