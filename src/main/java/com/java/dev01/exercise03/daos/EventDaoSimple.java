package com.java.dev01.exercise03.daos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.java.dev01.exercise03.pojos.Event;
import com.java.dev01.exercise03.util.HibernateFactory;
import com.java.dev01.exercise03.util.DataAccessLayerException;

import java.util.List;

//The Data Access Object for managing the persistent Events.
public class EventDaoSimple {
    Log log = LogFactory.getLog(EventDaoSimple.class);
    private Session session;
    private Transaction tx;

    public EventDaoSimple() {
        HibernateFactory.buildIfNeeded();
    }

    public void create(Event event) throws DataAccessLayerException {
        try {
            startOperation();
            session.save(event);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    public void update(Event event) throws DataAccessLayerException {
        try {
            startOperation();
            session.update(event);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    public void delete(Event event) throws DataAccessLayerException {
        try {
            startOperation();
            session.delete(event);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    public Event find(Long id) throws DataAccessLayerException {
        Event event = null;
        try {
            startOperation();
            event = (Event) session.load(Event.class, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            //HibernateFactory.close(session);
        }
        return event;
    }

    public List findAll() throws DataAccessLayerException {
        List eventList = null;
        try {
            startOperation();
            Query query = session.createQuery("from Event");
            eventList = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return eventList;
    }

    private void handleException(HibernateException e) throws DataAccessLayerException {
        HibernateFactory.rollback(tx);
        throw new DataAccessLayerException(e);
    }

    private void startOperation() throws HibernateException {
        session = HibernateFactory.openSession();
        tx = session.beginTransaction();
    }
}
