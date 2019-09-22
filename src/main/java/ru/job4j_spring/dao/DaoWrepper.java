package ru.job4j_spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Repository
public class DaoWrepper {
    @Autowired
    private  SessionFactory sessionFactory;

    public <T> T tx(Function<Session, T> command) {
        final Session session = this.sessionFactory.openSession();
        System.out.println(session.toString());
        final Transaction transaction = session.beginTransaction();
        try {
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void txUpdate(Consumer<Session> command) {
        final Session session = this.sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            command.accept(session);
            transaction.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
