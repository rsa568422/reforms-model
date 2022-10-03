package sa.reforms.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import sa.reforms.enums.Guild;

import sa.reforms.enums.Guild;

import java.util.Optional;
import java.util.StringJoiner;

@Getter
@RequiredArgsConstructor
public class Job {

    @NonNull
    @Setter
    private Guild guild;

    @NonNull
    @Setter
    private String name;

    private Optional<String> description = Optional.empty();

    public void setDescription(String description) {
        this.description = Optional.ofNullable(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        return getName().equals(job.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Job{ ", " }");
        joiner.add(String.format("guild:%s", this.guild));
        joiner.add(String.format("name:%s", this.name));
        this.description.ifPresent(description -> joiner.add(String.format("description:%s", this.description)));
        return joiner.toString();
    }

}
