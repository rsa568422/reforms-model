package sa.reforms.tasks.contracted.jobs;

import java.util.Optional;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Job {

    public enum Guild { PAINTWORK, BRICKWORK, PLUMBING, CARPENTRY, CLEANNESS }

    @NonNull
    private Guild guild;

    @NonNull
    private String name;

    private Optional<String> description = Optional.empty();

    public void setDescription(String description) {
        this.description = Optional.ofNullable(description);
    }

}
