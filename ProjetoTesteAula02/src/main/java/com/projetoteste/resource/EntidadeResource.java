package com.projetoteste.resource;

import com.projetoteste.dao.EntidadeDao;
import com.projetoteste.modelo.Entidades;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/entidades") 
public class EntidadeResource { 

    EntidadeDao entidadeDao = new EntidadeDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entidades> findAll() {

        System.out.println("Método GET 'findAll'");
        return entidadeDao.buscaTodosEntidades();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entidades find(@PathParam("id") int id) {

        System.out.println("Método GET 'find'");
        return entidadeDao.buscaEntidade(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Entidades create(Entidades usuario) {

        System.out.println("Método POST 'create'");
        return entidadeDao.criaEntidade(usuario);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Entidades update(Entidades usuario) {

        System.out.println("Método PUT 'update'");
        return entidadeDao.atualizaEntidade(usuario);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") long id) {

        System.out.println("Método DELETE 'remove'");
        entidadeDao.removeEntidade(id);
    }
}
