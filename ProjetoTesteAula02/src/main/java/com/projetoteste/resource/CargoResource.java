/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetoteste.resource;

import com.projetoteste.dao.CargosDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.projetoteste.modelo.Cargos;

/**
 *
 * @author bruno.kurzawe
 */
@Path("/cargos") 
public class CargoResource {  
    List<Cargos> cargos = new ArrayList();
    CargosDAO db = new CargosDAO();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cargos> getAllUsersInJSON() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException  {
        System.out.println("GET TODOS");
        cargos = db.buscaTodos();
        return cargos;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cargos getCargorById(@PathParam("id") int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        System.out.println("GET:"+id);
        return db.buscaPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cargos create(Cargos cargo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
         Cargos c = db.criarCargo(cargo);
        
        return c;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cargos update(Cargos cargo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
       Cargos c = db.atualizaCargo(cargo);
       return c;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        db.remove(id);
    }
}