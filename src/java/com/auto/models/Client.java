package com.auto.models;

import com.auto.enums.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
@Access(AccessType.PROPERTY)
public class Client {
    private int id;
    private String name;
    private String address;
    private Date birthday;
    private Gender gender;
    private List<Vehicle> vehicles;

    public Client() {

    }

    public Client(String name) {
        this.name = name;
        this.vehicles = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Basic
    @Column(name = "address", nullable = true, length = 45)
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    public Date getBirthday() {return birthday;}
    public void setBirthday(Date birthday) {this.birthday = birthday;}

    @Basic
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    public Gender getGender() {return gender;}
    public void setGender(Gender gender) {this.gender = gender;}

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    public List<Vehicle> getVehicles() {return vehicles;}
    public void setVehicles(List<Vehicle> vehicles) {this.vehicles = vehicles;}

}




