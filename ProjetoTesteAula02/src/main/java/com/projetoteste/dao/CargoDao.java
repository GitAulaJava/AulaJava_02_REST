package com.projetoteste.dao;

import com.projetoteste.modelo.Cargos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CargoDao {
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPUTeste");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List<Cargos> buscaTodosCargos() {

        List<Cargos> cargos = new ArrayList<Cargos>();
        Query query = entityManager.createQuery("select t from CARGOS as t ");
        cargos = query.getResultList();
        return cargos;
    };
    
    public Cargos buscaCargo(long id) {
        
        Cargos cargo = entityManager.find(Cargos.class, id);
        return cargo;
    };

    public Cargos criaCargo(Cargos cargo) {

        entityManager.getTransaction().begin();
        entityManager.persist(cargo);
        entityManager.getTransaction().commit();
        entityManager.close();
        return cargo;
    };

    public Cargos atualizaCargo(Cargos cargo) {
        
        entityManager.getTransaction().begin();
        entityManager.merge(cargo);
        entityManager.getTransaction().commit();
        entityManager.close();
        return cargo;
    };

    public void removeCargo(long id) {
        
        Cargos cargo = this.buscaCargo(id);
        entityManager.getTransaction().begin();
        entityManager.remove(cargo);
        entityManager.getTransaction().commit();
        entityManager.close();
    };
}
