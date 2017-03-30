package com.eventhorizon.meshu.eventhorizon.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by meshu on 3/18/2017.
 */

@Table(name = "Host")
public class Host extends Model {
    @Column(name = "hostId", index = true)
    public String hostId;

    @Column(name = "hostName")
    public String hostName;

    @Column(name = "organization")
    public String organization;


    public Host(String hostId, String hostName, String organization) {
        this.hostId = hostId;
        this.hostName = hostName;
        this.organization = organization;
    }

    public Host() {
        super();
    }
}
