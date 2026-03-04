package es.edemingo.moviesmanager.model;

import es.edemingo.moviesmanager.persistence.PeliculaDAO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="t_movie")
@NamedQuery(
        name="Pelicula.findByDirectorName",
        query = "FROM Pelicula p WHERE UPPER(p.director) LIKE:cadena"
)
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String titulo;
    private String director;
    @Column(name="year")
    private int anyo;
    @ManyToOne
    @JoinColumn(name = "idGenero")
    private Genero genero;

    public Pelicula() {
    }

    public Pelicula(String titulo, String director, int anyo, Genero genero) {
        this.titulo = titulo;
        this.director = director;
        this.anyo = anyo;
        this.genero = genero;
    }

    public Pelicula(int id, String titulo, String director, int anyo, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anyo = anyo;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }


}
