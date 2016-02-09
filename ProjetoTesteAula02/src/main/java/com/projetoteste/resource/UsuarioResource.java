package com.projetoteste.resource;

import com.projetoteste.dao.UsuarioDao;
import com.projetoteste.modelo.Usuarios;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/usuarios")
public class UsuarioResource {

    UsuarioDao usuarioDao = new UsuarioDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuarios> findAll() {

        System.out.println("Método GET 'findAll'");
        return usuarioDao.buscaTodosUsuarios();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuarios find(@PathParam("id") int id) {

        System.out.println("Método GET 'find'");
        return usuarioDao.buscaUsuario(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuarios create(Usuarios usuario) {

        System.out.println("Método POST 'create'");
        return usuarioDao.criaUsuario(usuario);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuarios update(Usuarios usuario) {

        System.out.println("Método PUT 'update'");
        return usuarioDao.atualizaUsuario(usuario);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") long id) {

        System.out.println("Método DELETE 'remove'");
        usuarioDao.removeUsuario(id);
    }
}
