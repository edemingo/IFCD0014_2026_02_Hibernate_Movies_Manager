package es.edemingo.moviesmanager.persistence;

import org.hibernate.Session;

public class SessionManager {
    protected static Session session=null;

    public static void startSession() {
        if (session==null || !session.isOpen()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
    }
    public static void finishSession(){
        if (session!=null) {
            session.close();
        }
    }
}
