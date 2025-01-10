package com.IPLproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IPLDataProject {
    private static final int ID = 0;
    private static final int SEASON = 1;
    private static final int CITY = 2;
    private static final int DATE = 3;
    private static final int TEAM1 = 4;
    private static final int TEAM2 = 5;
    private static final int TOSS_WINNER = 6;
    private static final int TOSS_DECISION = 7;
    private static final int RESULT = 8;
    private static final int DL_APPLIED = 9;
    private static final int WINNER = 10;
    private static final int WIN_BY_RUNS = 11;
    private static final int WIN_BY_WICKETS = 12;
    private static final int PLAYER_OF_MATCH = 13;
    private static final int VENUE = 14;
    private static final int UMPIRE1 = 15;
    private static final int UMPIRE2 = 16;
    private static final int UMPIRE3 = 17;

    private static final int MATCH_ID = 0;
    private static final int INNING = 1;
    private static final int BATTING_TEAM = 2;
    private static final int BOWLING_TEAM = 3;
    private static final int OVER = 4;
    private static final int BALL = 5;
    private static final int BATSMAN = 6;
    private static final int NON_STRIKER = 7;
    private static final int BOWLER = 8;
    private static final int IS_SUPER_OVER = 9;
    private static final int WIDE_RUNS = 10;
    private static final int BYE_RUNS = 11;
    private static final int LEG_BYE_RUNS = 12;
    private static final int NO_BALL_RUNS = 13;
    private static final int PENALITY_RUNS = 14;
    private static final int BATS_MAN_RUNS = 15;
    private static final int EXTRA_RUNS = 16;
    private static final int TOTAL_RUNS = 17;
    private static final int PLAYER_DISMISSED = 18;
    private static final int DISMISSAL_KIND = 19;
    private static final int FIELDER = 20;

    public static void main(String args[]) throws IOException {
        List<Match> matches = fetchMatchesData();
        List<Delivery> deliveries = fetchDeliveriesData();

        findNumberOfMatchesPlayedPerYear(matches);
        findNumberOfMatchesWonPerTeamInALlYears(matches);
        findExtraRunsConcededPerTeamIn2016(matches, deliveries);
        findTopEconomicalBowlersiIn2015(matches, deliveries);
        findNumberOfTimesTheTeamWonTossAndMatch(matches);

        findAverageScore(matches,deliveries);
    }


    public static List<Match> fetchMatchesData() throws IOException {
        String matchesCSV = "data/matches.csv";

        List<Match> matches = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(matchesCSV));
        bufferedReader.readLine();
        String rowData;
        while ((rowData = bufferedReader.readLine()) != null) {
            String[] matchRecordData = rowData.split(",");
            Match match = new Match();
            match.setId(Integer.parseInt(matchRecordData[ID]));
            match.setSeason(Integer.parseInt(matchRecordData[SEASON]));
            match.setCity(matchRecordData[CITY]);
            match.setDate(matchRecordData[DATE]);
            match.setTeam1(matchRecordData[TEAM1]);
            match.setTeam2(matchRecordData[TEAM2]);
            match.setToss_winner(matchRecordData[TOSS_WINNER]);
            match.setToss_decision(matchRecordData[TOSS_DECISION]);
            match.setResult(matchRecordData[RESULT]);
            match.setDl_applied(Integer.parseInt(matchRecordData[DL_APPLIED]));
            match.setWinner(matchRecordData[WINNER]);
            match.setWin_by_runs(Integer.parseInt(matchRecordData[WIN_BY_RUNS]));
            match.setWin_by_wickets(Integer.parseInt(matchRecordData[WIN_BY_WICKETS]));
            match.setPlayer_of_match(matchRecordData[PLAYER_OF_MATCH]);
            match.setVenue(matchRecordData[VENUE]);
            match.setUmpire1(matchRecordData.length < 18 ? "null" : matchRecordData[UMPIRE1]);
            match.setUmpire2(matchRecordData.length < 18 ? "null" : matchRecordData[UMPIRE2]);
            match.setUmpire3(matchRecordData.length < 18 ? "null" : matchRecordData[UMPIRE3]);

            matches.add(match);
        }
        return matches;
    }

    private static List<Delivery> fetchDeliveriesData() throws IOException {
        String deliveriesCSV = "data/deliveries.csv";

        List<Delivery> deliveries = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(deliveriesCSV));
        bufferedReader.readLine();
        String rowData;
        while ((rowData = bufferedReader.readLine()) != null) {
            String[] matchRecordData = rowData.split(",");
            Delivery delivery = new Delivery();
            delivery.setMatchId(Integer.parseInt(matchRecordData[MATCH_ID]));
            delivery.setInning(Integer.parseInt(matchRecordData[INNING]));
            delivery.setBattingTeam(matchRecordData[BATTING_TEAM]);
            delivery.setBowlingTeam(matchRecordData[BOWLING_TEAM]);
            delivery.setOver(Integer.parseInt(matchRecordData[OVER]));
            delivery.setBall(Integer.parseInt(matchRecordData[BALL]));
            delivery.setBatsman(matchRecordData[BATSMAN]);
            delivery.setNonStriker(matchRecordData[NON_STRIKER]);
            delivery.setBowler(matchRecordData[BOWLER]);
            delivery.setIsSuperOver(Integer.parseInt(matchRecordData[IS_SUPER_OVER]));
            delivery.setWideRuns(Integer.parseInt(matchRecordData[WIDE_RUNS]));
            delivery.setByeRuns(Integer.parseInt(matchRecordData[BYE_RUNS]));
            delivery.setLegByeRuns(Integer.parseInt(matchRecordData[LEG_BYE_RUNS]));
            delivery.setNoBallRuns(Integer.parseInt(matchRecordData[NO_BALL_RUNS]));
            delivery.setPenalityRuns(Integer.parseInt(matchRecordData[PENALITY_RUNS]));
            delivery.setBatsmanRuns(Integer.parseInt(matchRecordData[BATS_MAN_RUNS]));
            delivery.setExtraRuns(Integer.parseInt(matchRecordData[EXTRA_RUNS]));
            delivery.setTotalRuns(Integer.parseInt(matchRecordData[TOTAL_RUNS]));
            delivery.setPlayerDismissed(matchRecordData.length > 20 ? matchRecordData[PLAYER_DISMISSED] : "null");
            delivery.setDismissalKind(matchRecordData.length > 20 ? matchRecordData[DISMISSAL_KIND] : "null");
            delivery.setFielder(matchRecordData.length > 20 ? matchRecordData[FIELDER] : "null");

            deliveries.add(delivery);
        }
        return deliveries;
    }

    private static void findNumberOfMatchesPlayedPerYear(List<Match> matches) {
        Map<Integer, Integer> matchesPerYear = new HashMap<>();

        for (Match match : matches) {
            int matchYear = match.getSeason();
            matchesPerYear.put(matchYear, matchesPerYear.getOrDefault(matchYear, 0) + 1);
        }
        //System.out.println(matchesPerYear);

    }

    private static void findNumberOfMatchesWonPerTeamInALlYears(List<Match> matches) {
        Map<String, Integer> matchesWonPerTeam = new HashMap<>();
        for (Match match : matches) {
            String team = match.getWinner();
            if (!team.isEmpty()) {
                matchesWonPerTeam.put(team, matchesWonPerTeam.getOrDefault(team, 0) + 1);
            }
        }
       //System.out.println(matchesWonPerTeam);
    }

    private static void findExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        List<Integer> matchIDList = new ArrayList<>();
        Map<String, Integer> teamToExtraRunsConceded = new HashMap<>();
        for (Match match : matches) {
            if (match.getSeason() == 2016) {
                matchIDList.add(match.getId());
            }
        }

        for (Delivery delivery : deliveries) {
            if (matchIDList.contains(delivery.getMatchId())) {
                String team = delivery.getBowlingTeam();
                int currentRuns = teamToExtraRunsConceded.getOrDefault(team, 0);
                int extraRuns = delivery.getExtraRuns();
                teamToExtraRunsConceded.put(team, currentRuns + extraRuns);

            }
        }
        //System.out.println(teamToExtraRunsConceded);
    }

    private static void findTopEconomicalBowlersiIn2015(List<Match> matches, List<Delivery> deliveries) {
        Map<String, Integer> runsByBowler = new HashMap<>();
        Map<String, Integer> ballsByBowler = new HashMap<>();
        Map<String, Double> topEconomicalBowlers = new HashMap<>();
        List<Integer> idList = new ArrayList<>();
        for (Match match : matches) {
            if (match.getSeason() == 2015) {
                idList.add(match.getId());
            }

        }
        for (Delivery delivery : deliveries) {
            if (idList.contains(delivery.getMatchId())) {
                String bowler = delivery.getBowler();
                int totalRuns = delivery.getTotalRuns();
                runsByBowler.put(bowler, totalRuns);
                if (delivery.getWideRuns() == 0 && delivery.getNoBallRuns() == 0) {
                    ballsByBowler.put(bowler, ballsByBowler.getOrDefault(bowler, 0) + 1);
                }
            }
        }

        for (String bowler : runsByBowler.keySet()) {
            int totalRunsOfBowler = runsByBowler.get(bowler);
            int totalBallsOfBowler = ballsByBowler.get(bowler);
            double overs = totalBallsOfBowler / 6.0;
            double economyRate = totalRunsOfBowler / overs;
            topEconomicalBowlers.put(bowler, economyRate);
        }

        List<Map.Entry<String, Double>> economicalBowlerList = new ArrayList<>(topEconomicalBowlers.entrySet());
        Collections.sort(economicalBowlerList, (value1, value2) ->
                value1.getValue().compareTo(value2.getValue()));
        //System.out.print(economicalBowlerList);

    }

    private static void findNumberOfTimesTheTeamWonTossAndMatch(List<Match> matches) {

        Map<String, Integer> wonTossAndMatch = new HashMap<>();
        for (Match match : matches) {
            String tossWinner = match.getToss_winner();
            String matchWinner = match.getWinner();
            if (tossWinner.equals(matchWinner)) {
                int count = wonTossAndMatch.getOrDefault(tossWinner, 0);
                wonTossAndMatch.put(tossWinner, count + 1);
            }
        }
        //System.out.print(wonTossAndMatch);
    }


    //average score per team per year calculate the average score of each team for every year

    private static void findAverageScore(List<Match> matches, List<Delivery> deliveries) {
        Map<Integer,Map<String ,Integer>> avgScore=new HashMap<>();
        Set<String> teams=new HashSet<>();

        for(Match match:matches){
            String team1=match.getTeam1();
            String team2= match.getTeam2();
            teams.add(team1);
            teams.add(team2);
            Map<String,Integer> innerMap=avgScore.getOrDefault(match.getSeason(),new HashMap<>());

            avgScore.put(match.getSeason(),new HashMap<>());
        }
        for(Delivery delivery:deliveries){

        }
    }


}

