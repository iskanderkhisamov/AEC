package ru.kstu.aec.dao;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kstu.aec.models.User;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserDAO implements StandartDAO<User>{

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> findByUsername(String username) {
        Transaction tx = null;
        User a = null;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.byNaturalId(User.class)
                    .using("username", username)
                    .load();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
        return Optional.ofNullable(a);
    }

    @Override
    public void save(User a) {
        Session session = open();
        Transaction tx = null;
        try {
            tx = getTx(session);
            session.persist(a);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
    }

    @Override
    public List<User> list() {
        Session session = open();
        Transaction tx = null;
        List<User> list = null;
        try {
            tx = getTx(session);
            TypedQuery<User> query = session.createQuery("SELECT e FROM User e", User.class);
            list = query.getResultList();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
        return list;
    }

    @Override
    public Optional<User> show(long id) {
        Transaction tx = null;
        User a = null;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(User.class, id);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
        return Optional.ofNullable(a);
    }

    @Override
    public void update(User a) {
        Transaction tx = null;
        Session session = open();
        try {
            tx = getTx(session);
            session.update(a);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
    }

    @Override
    public void delete(long id) {
        Transaction tx = null;
        User a;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(User.class, id);
            if(null != a){
                session.delete(a);
            }
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
    }

    private Session open() {
        return sessionFactory.openSession();
    }
    private Transaction getTx(Session session) {
        return session.beginTransaction();
    }
    private void close(Session session) {
        session.close();
    }
}
