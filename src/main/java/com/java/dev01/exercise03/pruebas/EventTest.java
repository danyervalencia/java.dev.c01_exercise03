package com.java.dev01.exercise03.pruebas;

import java.util.*;
import com.java.dev01.exercise03.daos.EventDaoSimple;
import com.java.dev01.exercise03.pojos.Event;
import com.java.dev01.exercise03.util.HibernateUtil;

public class EventTest {
    public static void main(String[] args) {
        HibernateUtil.tableDrop("drop table events");
        HibernateUtil.sqlExecute("create table events (event_id int, event_name VARCHAR(50), event_datestart date, event_duration int)");

        // retorna variable SessionFactory
        EventDaoSimple eventDao = new EventDaoSimple();

        // eventDao.create establece Session
        Event event1 = new Event();
        event1.setName("Java Full Day");
        event1.setDuration(12);
        eventDao.create(event1);

        Event event2 = new Event();
        event2.setName("Javathon");
        event2.setDuration(24);
        eventDao.create(event2);

        Event event3 = new Event();
        event3.setName("Microservicios");
        event3.setDuration(6);
        eventDao.create(event3);

        Event event4 = new Event();
        event4.setName("Microservicios Docker y Kubernets");
        event4.setDuration(4);
        eventDao.create(event4);

        // Perform find Dao operation
        Event eventDaoFind = eventDao.find(event4.getId());
        System.out.println("Name of the event found = " + eventDaoFind.getName());

        // Perform update Dao operation
        event2.setName("Edit Conference");
        eventDao.update(event2);

        // Perform findAll Dao operation
        List eventDaoList = eventDao.findAll();
        System.out.println("Number of records in the table events => " + eventDaoList.size());
        System.out.println(eventDaoList);

        HibernateUtil.dataSelect("select * from events");
    }
}