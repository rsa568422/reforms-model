package sa.reforms.entities;

import lombok.NonNull;

public class Client extends Person {

    public Client(@NonNull Long id, @NonNull String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "Client");
    }

}
