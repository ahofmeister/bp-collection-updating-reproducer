package org.acme;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import java.util.Set;

@EntityView(Food.class)
@CreatableEntityView
public interface FoodCreateView {

    String getName();

    void setName(String name);

    @IdMapping
    String getId();

    void setId(String id);

    Set<FoodUnitMapping> getUnitMappings();

    void setUnitMappings(Set<FoodUnitMapping> unitMappings);

}
