package com.ebridgevas.android.ebridgeapp.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author david@tekeshe.com
 */
public class Market {

    private String classId;
    private String typeId;
    private String id;
    private Date date;
    private String name;
    private ArrayList<Participant> participants;

    public Market() {
    }

    public Market(String classId, String typeId, String id, Date date, String name) {
        this.classId = classId;
        this.typeId = typeId;
        this.id = id;
        this.date = date;
        this.name = name;
        this.participants = new ArrayList<>();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }
}
