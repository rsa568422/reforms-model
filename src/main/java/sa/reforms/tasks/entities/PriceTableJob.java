package sa.reforms.tasks.entities;

import sa.reforms.entities.Insurer;
import sa.reforms.entities.Job;
import sa.reforms.exceptions.InvalidParamsException;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

public abstract class PriceTableJob extends ContractedJob {

    protected final Map<Range, Function<Double, BigDecimal>> priceTable;

    public PriceTableJob(@NonNull Insurer insurer, @NonNull Guild guild, @NonNull String name,
                         @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        super(insurer, guild, name);
        if (priceTable.isEmpty()) throw new InvalidParamsException("Price table can't be empty");
        this.priceTable = priceTable;
    }

    public PriceTableJob(@NonNull Insurer insurer, @NonNull Job job,
                         @NonNull Map<Range, Function<Double, BigDecimal>> priceTable) {
        this(insurer, job.getGuild(), job.getName(), priceTable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceTableJob)) return false;
        if (!super.equals(o)) return false;

        PriceTableJob that = (PriceTableJob) o;

        return priceTable.equals(that.priceTable);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + priceTable.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String target = super.toString().substring(0, super.toString().indexOf("{"));
        return super.toString().replace(target, "PriceTableJob");
    }

}
