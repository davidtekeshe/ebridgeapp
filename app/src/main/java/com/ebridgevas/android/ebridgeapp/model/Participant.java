package com.ebridgevas.android.ebridgeapp.model;

import java.math.BigDecimal;

/**
 * @author david@tekeshe.com
 */
public class Participant {

    private String id;
    private String marketId;
    private String name;
    private String odds;
    private BigDecimal oddsDecimal;

    public Participant() {
    }

    public Participant(String id, String marketId, String name, String odds, BigDecimal oddsDecimal) {
        this.id = id;
        this.marketId = marketId;
        this.name = name;
        this.odds = odds;
        this.oddsDecimal = oddsDecimal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
