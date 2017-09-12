package com.ebridgevas.android.ebridgeapp.data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class Participant {

    private String id;
    private String narrative;
    private String odds;
    private BigDecimal oddsDecimal;
    private Date mLastUpdateDate;
    private Time mLastUpdateTime;

    public Participant() {
    }

    public Participant(
            String id, String narrative, String odds, BigDecimal oddsDecimal, Date lastUpdateDate,
            Time lastUpdateTime) {

        this.id = id;
        this.narrative = narrative;
        this.odds = odds;
        this.oddsDecimal = oddsDecimal;
        mLastUpdateDate = lastUpdateDate;
        mLastUpdateTime = lastUpdateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public BigDecimal getOddsDecimal() {
        return oddsDecimal;
    }

    public void setOddsDecimal(BigDecimal oddsDecimal) {
        this.oddsDecimal = oddsDecimal;
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
}
