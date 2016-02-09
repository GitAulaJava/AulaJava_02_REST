package com.projetoteste.dao;

import com.projetoteste.modelo.Entidades;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EntidadeDao {
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPUTeste");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List<Entidades> buscaTodosEntidades() {

        List<Entidades> entidades = new ArrayList<Entidades>();
        Query query = entityManager.createQuery("select t from ENTIDADES as t ");
        entidades = query.getResultList();
        return entidades;
    };
    
    public Entidades buscaEntidade(long id) {
        
        Entidades entidade = entityManager.find(Entidades.class, id);
        return entidade;
    };

    public Entidades criaEntidade(Entidades entidade) {

        entityManager.getTransaction().begin();
        entityManager.persist(entidade);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entidade;
    };

    public Entidades atualizaEntidade(Entidades entidade) {
        
        entityManager.getTransaction().begin();
        entityManager.merge(entidade);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entidade;
    };

    public void removeEntidade(long id) {
        
        Entidades entidade = this.buscaEntidade(id);
        entityManager.getTransaction().begin();
        entityManager.remove(entidade);
        entityManager.getTransaction().commit();
        entityManager.close();
    };
}
