package es.edemingo.moviesmanager.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="t_genre")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenero;
    private String name;

    public Genero() {
    }

    public Genero(String name) {
        this.name = name;
    }

    public Genero(int idGenero, String name) {
        this.idGenero = idGenero;
        this.name = name;
    }


    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int id) {
        this.idGenero = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "idGenero=" + idGenero +
                ", name='" + name + '\'' +
                '}';
    }
}
