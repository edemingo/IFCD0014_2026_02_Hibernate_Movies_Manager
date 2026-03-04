package es.edemingo.moviesmanager.persistence;
import es.edemingo.moviesmanager.model.Genero;

import java.util.List;

public class GeneroDAO extends SessionManager{

    public static void create(Genero genero) {
        startSession();
        session.beginTransaction();
        session.persist(genero);
        session.getTransaction().commit();
        System.out.println("¡Película creado/a con éxito!");
    }

    public static Genero findById(Long id) {
        startSession();
        session.beginTransaction();
        Genero genero = session.find(Genero.class, id);
        session.getTransaction().commit();
        return genero;
    }

    public static List<Genero> findAll() {
        startSession();
        session.beginTransaction();

        List<Genero> generos = session
                .createQuery("FROM Genero", Genero.class)
                .getResultList();

        session.getTransaction().commit();

        return generos;
    }




}


