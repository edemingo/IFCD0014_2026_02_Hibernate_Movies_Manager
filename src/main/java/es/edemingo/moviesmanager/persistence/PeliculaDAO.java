package es.edemingo.moviesmanager.persistence;

import es.edemingo.moviesmanager.model.Genero;
import es.edemingo.moviesmanager.model.Pelicula;

import javax.management.Query;
import java.util.List;

public class PeliculaDAO extends SessionManager{
    public static void create(Pelicula pelicula) {
        startSession();
        session.beginTransaction();
        session.persist(pelicula);
        session.getTransaction().commit();
        System.out.println("¡Película creado/a con éxito!");
    }


    public static List<Pelicula> findPeliculadByAnyo(int anyo) {
        startSession();
        session.beginTransaction();

        List<Pelicula> peliculas = session
                .createQuery("FROM Pelicula p WHERE p.anyo=:anyo", Pelicula.class)
                .setParameter("anyo", anyo)
                .getResultList();

        session.getTransaction().commit();

        return peliculas;
    }

    public static List<Pelicula> findPeliculadByAnyoPos(int anyo) {
        startSession();
        session.beginTransaction();

        List<Pelicula> peliculas = session
                .createQuery("FROM Pelicula p WHERE p.anyo>=:anyomaximo", Pelicula.class)
                .setParameter("anyomaximo", anyo)
                .getResultList();

        session.getTransaction().commit();
        return peliculas;
    }

    public static List<Pelicula> findPeliculadByAnyoAnt(int anyo) {
        startSession();
        session.beginTransaction();

        List<Pelicula> peliculas = session
                .createQuery("FROM Pelicula p WHERE p.anyo<=:anyo", Pelicula.class)
                .setParameter("anyo", anyo)
                .getResultList();

        session.getTransaction().commit();

        return peliculas;
    }


    public static List<Pelicula> findPeliculadByGenre(Genero genero) {
        startSession();
        session.beginTransaction();

        List<Pelicula> peliculas = session
                .createQuery("FROM Pelicula p WHERE p.genero=:generoBuscado", Pelicula.class)
                .setParameter("generoBuscado", genero)
                .getResultList();

        session.getTransaction().commit();

        return peliculas;
    }


    public static List<Pelicula> findPeliculadByDirector(String directorName) {
        startSession();
        session.beginTransaction();

        List<Pelicula> peliculas = session.createNamedQuery("Pelicula.findByDirectorName", Pelicula.class).
        setParameter("cadena","%" + directorName.toUpperCase() + "%").
        getResultList();

        session.getTransaction().commit();

        return peliculas;
    }

}
