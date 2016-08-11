package com.auto.models;
import static org.junit.Assert.*;
import com.auto.util.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Optional;

public class ClientTest {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy");
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("set FOREIGN_KEY_CHECKS = 0").executeUpdate();
        session.createNativeQuery("truncate table clients").executeUpdate();
        session.createNativeQuery("truncate table vehicles").executeUpdate();
        session.createNativeQuery("set FOREIGN_KEY_CHECKS = 1").executeUpdate();
        Client stan = new Client("Stan");
        Vehicle v = new Vehicle("bmw", "x5", "Blue", format.parse("2000"), stan);
        stan.getVehicles().add(v);
        session.save(stan);
        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldCreateNewClientAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client client = new Client("jennifer");
        session.save(client);
        session.getTransaction().commit();
        session.close();
        assertEquals(2, client.getId());
    }

    @Test(expected = org.hibernate.exception.DataException.class)
    public void shouldNotSaveDueToNameTooLong() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client client = new Client("01234567890123456789012345678901234567890123456789");

        try {
            session.save(client);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    @Test
    public void shouldGetAnExistingClient() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        try {
            Client testClient = session.get(Client.class, 1);
            assertEquals(1, testClient.getId());
            assertEquals("Stan", testClient.getName());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Test
    public void shouldUpdateExistingClient() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client client = session.get(Client.class, 1);
        client.setName("Stan 2");
        session.getTransaction().commit();
        session.close();
        assertEquals(1, client.getId());
        assertEquals("Stan 2", client.getName());
    }


    @Test
    public void notATestProofOfConcept() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();


        Client client = session.get(Client.class, 1);
        client.setName("Stanley");
        Optional<Vehicle> bmw = client.getVehicles().stream().filter(a -> a.getMake().equals("bmw")).findFirst();
        bmw.ifPresent(f -> {
            f.setMake("BMW2");
        });
        System.out.println("HERE WE GO:"+ bmw.get().getMake());


        Vehicle v1 = new Vehicle("Honda", "Accord", "Blue", format.parse("2000"), client);
        client.getVehicles().add(v1);

        session.getTransaction().commit();
        session.close();
    }



}