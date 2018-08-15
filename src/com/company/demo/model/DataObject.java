package com.company.demo.model;

public class DataObject {

    private final String matchId;

    private final Long marketId;

    private final String outcomeId;

    private final String specifiers;


    public DataObject(String matchId, Long marketId, String outcomeId, String specifiers) {
        this.matchId = matchId;
        this.marketId = marketId;
        this.outcomeId = outcomeId;
        this.specifiers = specifiers;
    }

    public DataObject(String matchId, Long marketId, String outcomeId) {
        this.matchId = matchId;
        this.marketId = marketId;
        this.outcomeId = outcomeId;
        this.specifiers = null;
    }

    public String getMatchId() {
        return matchId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public String getOutcomeId() {
        return outcomeId;
    }

    public String getSpecifiers() {
        return specifiers;
    }

    @Override
    public String toString() {
        return "matchId: " + this.matchId +
                "\nmarketId: " + this.marketId +
                "\noutcomeId: " + this.outcomeId +
                "\nspecifiers: " + this.specifiers;
    }
}
