package com.eventhorizon.meshu.eventhorizon.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by meshu on 3/22/2017.
 */
@Table(name = "User")
public class User extends Model {
    @Column(name = "userId", index = true)
    public String userId;


    @Column(name = "name")
    public String name;

    @Column(name = "eventId")
    public String eventId;

    @Column(name = "phone")
    public String phone;

    @Column(name = "address")
    public String address;


    public User(String userId, String name, String eventId, String phone, String address) {
        this.userId = userId;
        this.name = name;
        this.eventId = eventId;
        this.phone = phone;
        this.address = address;
    }

    public User() {
        super();
    }
}