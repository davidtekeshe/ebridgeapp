package com.ebridgevas.android.ebridgeapp.data;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Market {

    private String mId;
    private String mCategoryId;
    private String mNarrative;
    private Date mDate;
    private Time mTime;
    private Date mBetTillDate;
    private Time mBetTillTime;
    private Date mLastUpdateDate;
    private Time mLastUpdateTime;

    public Market() {
    }

    public Market(String id, String categoryId, String narrative, Date date, Time time,
            Date betTillDate, Time betTillTime, Date lastUpdateDate, Time lastUpdateTime) {
        mId = id;
        mCategoryId = categoryId;
        mNarrative = narrative;
        mDate = date;
        mTime = time;
        mBetTillDate = betTillDate;
        mBetTillTime = betTillTime;
        mLastUpdateDate = lastUpdateDate;
        mLastUpdateTime = lastUpdateTime;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getNarrative() {
        return mNarrative;
    }

    public void setNarrative(String narrative) {
        mNarrative = narrative;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time time) {
        mTime = time;
    }

    public Date getBetTillDate() {
        return mBetTillDate;
    }

    public void setBetTillDate(Date betTillDate) {
        mBetTillDate = betTillDate;
    }

    public Time getBetTillTime() {
        return mBetTillTime;
    }

    public void setBetTillTime(Time betTillTime) {
        mBetTillTime = betTillTime;
    }

    public Date getLastUpdateDate() {
        return mLastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        mLastUpdateDate = lastUpdateDate;
    }

    public Time getLastUpdateTime() {
        return mLastUpdateTime;
    }

    public void setLastUpdateTime(Time lastUpdateTime) {
        mLastUpdateTime = lastUpdateTime;
    }

    private List<Participant> mParticipants;

    public List<Participant> getParticipants() {
        if (mParticipants == null) {
            mParticipants = new ArrayList<>();
        }
        return mParticipants;
    }

    public void setParticipants(List<Participant> participants) {
        mParticipants = participants;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id:'" + mId + '\'' +
                ", categoryId:'" + mCategoryId + '\'' +
                ", narrative:'" + mNarrative + '\'' +
                ", date:" + mDate +
                ", time:" + mTime +
                ", betTillDate:" + mBetTillDate +
                ", betTillTime:" + mBetTillTime +
                ", lastUpdateDate:" + mLastUpdateDate +
                ", lastUpdateTime:" + mLastUpdateTime +
                ", participants:" + mParticipants +
                "}\n";
    }
}
