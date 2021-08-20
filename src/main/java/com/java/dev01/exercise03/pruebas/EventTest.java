package com.java.dev01.exercise03.pruebas;

import java.util.*;

import com.java.dev01.exercise03.daos.EventDaoSimple;
import com.java.dev01.exercise03.pojos.Event;
import com.java.dev01.exercise03.util.HibernateUtil;

public class EventTest {
    public static void main(String[] args) {
        HibernateUtil.tableDrop("drop table events");
        HibernateUtil.sqlExecute("create table events (event_id int, event_name VARCHAR(20), event_datestart date, event_duration int)");

        EventDaoSimple eventDao = new EventDaoSimple();

        System.out.println("Performing create Dao operation....");
        Event event1 = new Event();
        event1.setName("Java Full Day");
        eventDao.create(event1);

        Event event2 = new Event();
        event2.setName("Javathon");
        eventDao.create(event2);

        Event event3 = new Event();
        event3.setName("Java Microservicios");
        eventDao.create(event3);

        // Perform find Dao operation
        System.out.println("Perrforming find Dao operation...");
        Event foundEvent = eventDao.find(event1.getId());
        System.out.println("Name of the event found = " + foundEvent.getName());

        // Perform update Dao operation
        System.out.println("Perrforming update Dao operation...");
        event1.setName("New Conference");
        eventDao.update(event1);

        // Perform findAll Dao operation
        System.out.println("Performing findAll Dao operation...");
        List eventList = eventDao.findAll();
        System.out.println("Number of entries in the database table = " + eventList.size());

        HibernateUtil.dataSelect("select * from events");
    }
}