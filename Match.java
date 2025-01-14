package com.IPLproject;

public class Match {
    private int id;
    private int season;
    private String city;
    private String date;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String result;
    private String winner;
    private int dlApplied;
    private int winByRuns;
    private int winByWickets;
    private String playerOfMatch;
    private String venue;
    private String umpire1;
    private String umpire2;
    private String umpire3;

    public String getUmpire3() {
        return umpire3;
    }

    public void setUmpire3(String umpire3) {
        this.umpire3 = umpire3;
    }

    public String getUmpire2() {
        return umpire2;
    }

    public void setUmpire2(String umpire2) {
        this.umpire2 = umpire2;
    }

    public String getUmpire1() {
        return umpire1;
    }

    public void setUmpire1(String umpire1) {
        this.umpire1 = umpire1;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPlayer_of_match() {
        return playerOfMatch;
    }

    public void setPlayerOfMatch(String player_of_match) {
        this.playerOfMatch = player_of_match;
    }

    public int getWinByWickets() {
        return winByWickets;

    }

    public void setWinByWickets(int win_by_wickets) {
        this.winByWickets = win_by_wickets;
    }

    public int getWin_by_runs() {
        return winByRuns;
    }

    public void setWin_by_runs(int win_by_runs) {
        this.winByRuns = win_by_runs;
    }

    public int getDlApplied() {
        return dlApplied;
    }

    public void setDl_applied(int dl_applied) {
        this.dlApplied = dl_applied;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToss_decision() {
        return tossDecision;
    }

    public void setToss_decision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String toss_winner) {
        this.tossWinner = tossWinner;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
