package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class CrudRepository {
    private SessionFactory sf;

    public void run(Consumer<Session> command) {
        tx(session -> {
            command.accept(session);
            return null;
        });
    }

    public <T> List<T> query(String query, Class<T> cl) {
        return tx(session -> session.createQuery(query, cl).list());
    }

    public <T> List<T> query(String query, Class<T> cl, Map<String, Object> args) {
        return tx(session -> {
            Query qr = session.createQuery(query, cl);
            for (Map.Entry<String, Object> entry : args.entrySet()) {
                qr.setParameter(entry.getKey(), entry.getValue());
            }
            return qr.list();
        });
    }

    public <T> Optional<T> optional(String query, Class<T> cl, Map<String, Object> args) {
        return tx(session -> {
            Query qr = session.createQuery(query, cl);
            for (Map.Entry<String, Object> entry : args.entrySet()) {
                qr.setParameter(entry.getKey(), entry.getValue());
            }
            return qr.uniqueResultOptional();
        });
    }


    public <T> T tx(Function<Session, T> command) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
