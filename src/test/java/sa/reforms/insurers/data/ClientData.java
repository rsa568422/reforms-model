package sa.reforms.insurers.data;

import sa.reforms.insurers.Client;

public class ClientData {

    public static Client GET_CLIENT_A() {
        return new Client(1L, "Client A");
    }

    public static Client GET_CLIENT_B() {
        return new Client(2L, "Client B");
    }

}
