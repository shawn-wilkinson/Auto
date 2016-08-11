package com.auto.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by localadmin on 8/11/16.
 */


@Entity
@Table(name = "vehicles")
@Access(AccessType.PROPERTY)
public class Vehicle {
    private int id;
    private String make;
    private String model;
    private String color;
    private Date year;
    private Client client;

    public Vehicle() {
    }

    public Vehicle(String make, String model, String color, Date year, Client client) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.client = client;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {return id;}
    public void setId(int id) {this.id = id; }
    @Basic
    @Column(name = "make", nullable = true, length = 45)
    public String getMake() {return make;}
    public void setMake(String make) {this.make = make;}
    @Basic
    @Column(name = "model", nullable = true, length = 45)
    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}
    @Basic
    @Column(name = "color", nullable = true, length = 45)
    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "year")
    public Date getYear() {return year;}
    public void setYear(Date year) {this.year = year;}
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}
}