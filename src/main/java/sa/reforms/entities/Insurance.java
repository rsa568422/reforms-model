package sa.reforms.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@RequiredArgsConstructor
public class Insurance {

    @NonNull
    @Setter
    private String code;

    @NonNull
    @Setter
    private Insurer insurer;

    @NonNull
    @Setter
    private Client client;

    @NonNull
    @Setter
    private Property property;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Insurance)) return false;

        Insurance insurance = (Insurance) o;

        if (!getCode().equals(insurance.getCode())) return false;
        return getInsurer().equals(insurance.getInsurer());
    }

    @Override
    public int hashCode() {
        int result = getCode().hashCode();
        result = 31 * result + getInsurer().hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Insurance{ ", " }");
        joiner.add(String.format("code:%s", this.code));
        joiner.add(String.format("insurer:%s", this.insurer));
        joiner.add(String.format("client:%s", this.client));
        joiner.add(String.format("property:%s", this.property));
        return joiner.toString();
    }

}