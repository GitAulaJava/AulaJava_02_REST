package com.projetoteste.resource;

import com.projetoteste.dao.CargoDao;
import com.projetoteste.modelo.Cargos;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cargos") 
public class CargoResource { 

    CargoDao cargoDao = new CargoDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cargos> findAll() {

        System.out.println("Método GET 'findAll'");
        return cargoDao.buscaTodosCargos();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cargos find(@PathParam("id") int id) {

        System.out.println("Método GET 'find'");
        return cargoDao.buscaCargo(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cargos create(Cargos usuario) {

        System.out.println("Método POST 'create'");
        return cargoDao.criaCargo(usuario);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cargos update(Cargos usuario) {

        System.out.println("Método PUT 'update'");
        return cargoDao.atualizaCargo(usuario);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") long id) {

        System.out.println("Método DELETE 'remove'");
        cargoDao.removeCargo(id);
    }
}
