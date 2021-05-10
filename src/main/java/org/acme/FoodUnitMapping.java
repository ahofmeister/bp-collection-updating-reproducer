package org.acme;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Embeddable
public class FoodUnitMapping {

    @Positive
    public double gramsPerUnit;

    @Enumerated(EnumType.STRING)
    @NotNull
    public FoodUnit unit;

    public FoodUnitMapping() {
    }

    public FoodUnitMapping(double gramsPerUnit, FoodUnit unit) {
        this.gramsPerUnit = gramsPerUnit;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodUnitMapping that = (FoodUnitMapping) o;
        return Double.compare(that.gramsPerUnit, gramsPerUnit) == 0 && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gramsPerUnit, unit);
    }
}