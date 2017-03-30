package com.eventhorizon.meshu.eventhorizon.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by meshu on 3/18/2017.
 */
@Table(name = "Event")
public class Event extends Model {
    @Column(name = "eventId", index = true)
    public String eventId;

    @Column(name = "eventName")
    public String eventName;

    @Column(name = "hostId")
    public String hostId;

    @Column(name = "hostName")
    public String hostName;

    @Column(name = "date")
    public String date;

    @Column(name = "seat")
    public String seat;

    public Event(String eventId, String eventName, String hostId, String hostName, String date,String seat) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.hostId = hostId;
        this.hostName = hostName;
        this.date = date;
        this.seat = seat;
    }

    public Event() {
        super();
    }
}
