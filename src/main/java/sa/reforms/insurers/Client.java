package sa.reforms.insurers;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

    public Client(@NonNull Long id, @NonNull String name) {
        super(id, name);
    }

}
