package sa.reforms.entities;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Insurance {

    @NonNull
    private final String code;

    @NonNull
    private final Insurer insurer;

    @NonNull
    private final Client client;

    @NonNull
    private final Property property;

}