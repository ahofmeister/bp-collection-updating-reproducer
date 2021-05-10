package org.acme;

import com.blazebit.persistence.integration.jaxrs.EntityViewId;
import com.blazebit.persistence.view.EntityViewManager;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("foods")
public class FoodResource {

    @Inject
    EntityViewManager entityViewManager;

    @Inject
    EntityManager entityManager;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public FoodCreateView add(FoodCreateView view) {
        entityViewManager.save(entityManager, view);
        return view;
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") String id,
                           @EntityViewId("id") FoodUpdateView view) {
        entityViewManager.save(entityManager, view);
        return Response.ok(view).build();
    }

    @GET
    @Transactional
    public Response get() {
        Food food = entityManager.find(Food.class, "12");
        return Response.ok(food).build();
    }

}
