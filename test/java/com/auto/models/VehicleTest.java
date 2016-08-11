package com.auto.models;

import com.auto.util.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/11/16.
 */
public class VehicleTest {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy");
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        session.createNativeQuery("truncate table clients").executeUpdate();
        session.createNativeQuery("truncate table vehicles").executeUpdate();
        session.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
        Vehicle vehicle1 = new Vehicle("Chevy", "Malibu", "Black", format.parse("2015"), null);
        Vehicle vehicle2 = new Vehicle("Subaru", "Outback", "Green", format.parse("2015"), null);
        Client client = new Client("Magnus");
        client.getVehicles().add(vehicle1);
        client.getVehicles().add(vehicle2);
        session.save(client);
        session.getTransaction().commit();
        session.close();


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateNewVehicleAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Vehicle vehicle = new Vehicle("Toyota", "Tundra", "Black", format.parse("2015"), null);
        session.save(vehicle);
        session.getTransaction().commit();
        session.close();
        assertEquals(3, vehicle.getId());
    }



}