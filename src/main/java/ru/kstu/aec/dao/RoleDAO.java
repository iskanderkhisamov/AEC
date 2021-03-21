package ru.kstu.aec.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kstu.aec.models.Role;
import ru.kstu.aec.models.User;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class RoleDAO implements StandartDAO<Role>{

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Role> show(long id) {
        Transaction tx = null;
        Role a = null;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(Role.class, id);
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
    public List<Role> list() {
        Session session = open();
        Transaction tx = null;
        List<Role> list = null;
        try {
            tx = getTx(session);
            TypedQuery<Role> query = session.createQuery("SELECT e FROM Role e", Role.class);
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
    public void save(Role role) {
        Session session = open();
        Transaction tx = null;
        try {
            tx = getTx(session);
            session.persist(role);
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
    public void update(Role role) {
        Transaction tx = null;
        Session session = open();
        try {
            tx = getTx(session);
            session.update(role);
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
        Role a;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(Role.class, id);
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
