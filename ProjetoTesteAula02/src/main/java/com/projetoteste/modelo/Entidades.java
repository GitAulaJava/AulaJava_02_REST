package com.projetoteste.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name = "ENTIDADES")
public class Entidades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @ManyToMany
    @JoinTable(name = "ENTIDADES_USUARIOS",
            joinColumns = {
                @JoinColumn(name = "ENTIDADES_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "USUARIOS_ID")})
    private List<Usuarios> usuarios;

    public Entidades() {
    }

    public Entidades(long id, String nome, List<Usuarios> usuarios) {
        this.id = id;
        this.nome = nome;
        this.usuarios = usuarios;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
}
