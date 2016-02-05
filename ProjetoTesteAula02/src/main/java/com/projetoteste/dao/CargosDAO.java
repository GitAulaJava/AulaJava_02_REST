/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetoteste.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.projetoteste.modelo.Cargos;

/**
 *
 * @author bruno.kurzawe
 */
public class CargosDAO {
    
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("myPUTeste");
    EntityManager manager = factory.createEntityManager();

    public List<Cargos> buscaTodos() {

        List<Cargos> cargos = new ArrayList<Cargos>();

        Query query = manager.createQuery("select t from Cargos as t ");
        cargos = query.getResultList();
        return cargos;
    }

    ;
    
    public Cargos buscaPorId(int id) {
        Cargos c = manager.find(Cargos.class, id);
        return c;
    }

    public Cargos criarCargo(Cargos cargo) {

        manager.getTransaction().begin();
        manager.persist(cargo);
        manager.getTransaction().commit();
        manager.close();

        return cargo;
    }

    public Cargos atualizaCargo(Cargos cargo) {
        manager.getTransaction().begin();
        manager.merge(cargo);
        manager.getTransaction().commit();
        manager.close();

        return cargo;
    }

    public void remove(int id) {
        Cargos byId = this.buscaPorId(id);
        manager.getTransaction().begin();
        manager.remove(byId);
        manager.getTransaction().commit();
        manager.close();
    }
    
}
