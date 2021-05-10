package org.acme;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Food {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    public String id;

    @NotNull
    @Column(nullable = false)
    public String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Valid
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn
    public Set<FoodUnitMapping> unitMappings = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn
    private Set<String> eans;

    @Enumerated(value = EnumType.STRING)
    private FoodUnit defaultUnit;

    @Column(length = 500)
    private String tags;

    private String imageUrl;

    @Column(length = 2000)
    private String ingredients;

    private String legacyId;

    private boolean hidden;

    public String getName() {
        return this.name;
    }


    public FoodUnitMapping getFoodUnitMapping(FoodUnit unit) {
        return this.unitMappings.stream().filter(mapping -> mapping.unit == unit).findFirst()
                .orElse(null);
    }


    public Set<FoodUnitMapping> getUnitMappings() {
        return unitMappings;
    }

    public void setUnitMappings(Set<FoodUnitMapping> unitMappings) {
        this.unitMappings = unitMappings;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }

    public Set<String> getEans() {
        return eans;
    }

    public void setEans(Set<String> eans) {
        this.eans = eans;
    }

    public FoodUnit getDefaultUnit() {
        return defaultUnit;
    }

    public void setDefaultUnit(FoodUnit defaultUnit) {
        this.defaultUnit = defaultUnit;
    }


}
