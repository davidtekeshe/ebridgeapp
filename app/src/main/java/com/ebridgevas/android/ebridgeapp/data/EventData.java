package com.ebridgevas.android.ebridgeapp.data;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class EventData {


    private long eventId;
    private String narrative;
    private Date eventDate;
    private Time eventTime;

    public EventData() {
    }

    public EventData(long eventId, String narrative, Date eventDate, Time eventTime) {
        this.eventId = eventId;
        this.narrative = narrative;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }
}
