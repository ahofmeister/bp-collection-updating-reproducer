package org.acme;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.UpdatableEntityView;

import java.util.Set;

@EntityView(Food.class)
@UpdatableEntityView
public interface FoodUpdateView {

    @IdMapping
    String getId();

    void setId(String id);

    Set<FoodUnitUpdateView> getUnitMappings();

    void setUnitMappings(Set<FoodUnitUpdateView> unitMappings);

    String getName();

    void setName(String name);


}
