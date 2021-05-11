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


}
