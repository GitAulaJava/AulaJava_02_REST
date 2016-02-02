package com.projetoteste.dao;

import com.projetoteste.modelo.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UsuarioDao {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPUTeste");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List<Usuarios> buscaTodosUsuarios() {

        List<Usuarios> usuarios = new ArrayList<Usuarios>();

        Query query = entityManager.createQuery("select t from USUARIOS as t ");
        usuarios = query.getResultList();
        return usuarios;
    }
    
    public Usuarios buscaUsuario(long id) {
        
        Usuarios usuario = entityManager.find(Usuarios.class, id);
        return usuario;
    }

    public Usuarios criarUsuario(Usuarios usuario) {

        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
        entityManager.close();

        return usuario;
    }

    public Usuarios atualizaUsuario(Usuarios usuario) {

        entityManager.getTransaction().begin();
        entityManager.merge(usuario);
        entityManager.getTransaction().commit();
        entityManager.close();

        return usuario;
    }

    public void removeUsuario(long id) {

        entityManager.getTransaction().begin();
        entityManager.remove(id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}