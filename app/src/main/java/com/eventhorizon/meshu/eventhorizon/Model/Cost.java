package com.eventhorizon.meshu.eventhorizon.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by meshu on 3/18/2017.
 */
@Table(name = "Cost")
public class Cost extends Model{
    @Column(name = "costId", index = true)
    public String costId;

    @Column(name = "titles")
    public String titles;

    @Column(name = "eventId")
    public String eventId;

    @Column(name = "cost")
    public float cost;

    @Column(name = "details")
    public String details;


    public Cost(String costId, String titles, String eventId, float cost, String details) {
        this.costId = costId;
        this.titles = titles;
        this.eventId = eventId;
        this.cost = cost;
        this.details = details;
    }

    public Cost() {
      super();
    }
}
