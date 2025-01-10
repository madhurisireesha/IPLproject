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




















    package com.IPLproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    public class IPLCSVReader {
        String matchesCSV = "/home/sireesha/Documents/Java_Projects/IPL _Project/matches.csv";
        String deliversCSV ="/home/sireesha/Documents/Java_Projects/IPL _Project/deliveries.csv";
        //      String matchesCSV="/home/sireesha/IdeaProjects/IPL_DataProject/src/deliveries.csv";
//      String deliversCSV="/home/sireesha/IdeaProjects/IPL_DataProject/src/matches.csv";
        List<String[]> matchesList = new ArrayList<>();
        List<String[]> deliversList = new ArrayList<>();

        public List<String[]> getMatchesList() throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(matchesCSV));
            String rowData;
            while ((rowData = bufferedReader.readLine()) != null) {
                String[] match = rowData.split(",");
                matchesList.add(match);
            }
            bufferedReader.close();
            return matchesList;
        }
        public List<String[]> getDeliversList() throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(deliversCSV));
            String rowData;
            while ((rowData = bufferedReader.readLine()) != null) {
                String[] delivery = rowData.split(",");
                deliversList.add(delivery);
            }
            bufferedReader.close();
            return deliversList;
        }
    }






    _____________________________________________________________________________________________________________________________________________________


import com.IPLproject.IPLCSVReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.List;
import java.util.*;

    public class Scenarios {
        //Number of matches played per year of all the years in IPL.
        void numberOfMatches(List<String[]> matches) {

            Map<Integer, Integer> season = new TreeMap<Integer, Integer>();
            for (int i = 1; i < matches.size(); i++) {
                int year = Integer.parseInt(matches.get(i)[1]);
                season.put(year, season.getOrDefault(year, 0) + 1);
            }
            System.out.println("Number of matches played per year of all the years in IPL.");
            for (Map.Entry<Integer, Integer> entry : season.entrySet()) {
                System.out.println("Year: " + entry.getKey() + ", Matches: " + entry.getValue());
            }
            System.out.println("____________________________________________________________");
        }

        //Number of matches won of all teams over all the years of IPL.
        void numberOfMatchesWon(List<String[]> matches) {
            Map<String, Integer> matchesWon = new TreeMap<String, Integer>();
            for (int i = 1; i < matches.size(); i++) {
                String winner = matches.get(i)[10];
                if (!winner.isEmpty()) {
                    matchesWon.put(winner, matchesWon.getOrDefault(winner, 0) + 1);
                }
            }
            System.out.println("Number of matches won of all teams over all the years of IPL.");
            for (Map.Entry<String, Integer> entry : matchesWon.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }

        //For the year 2016 get the extra runs conceded per team.
        void extraRunsConceded(List<String[]> matches, List<String[]> deliveries) {
            Map<String,Integer> extraRuns = new HashMap<>();
            TreeSet<Integer> idList = new TreeSet<>();
            System.out.println("___________________________________");
            System.out.println("For the year 2016 get the extra runs conceded per team.");
            for(int i = 1; i < matches.size(); i++) {
                int currentid = Integer.parseInt(matches.get(i)[0]);
                int year = Integer.parseInt(matches.get(i)[1]);
                if(year == 2016) {
                    idList.add(currentid);
                }
            }

            for(int i = 1; i < deliveries.size(); i++) {
                int matchId = Integer.parseInt(deliveries.get(i)[0]);
                if(idList.contains(matchId)) {
                    String team = deliveries.get(i)[3];
                    int runs = extraRuns.getOrDefault(team, 0);
                    int currentExtraRuns = Integer.parseInt(deliveries.get(i)[16]);
                    extraRuns.put(team, runs + currentExtraRuns);
                }
            }
            for (Map.Entry<String, Integer> entry : extraRuns.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());

            }
        }

        //For the year 2015 get the top economical bowlers.

        void topEconomicalBowler(List<String[]> matches, List<String[]> deliveries){
            System.out.println("________________________________________");
            System.out.println("For the year 2015 get the top economical bowlers.");
            TreeSet<Integer> idList=new TreeSet<>();
            Map<String,Integer> runsByBowler=new HashMap<>();
            Map<String,Integer> ballsByBowler=new HashMap<>();
            for(int i=1;i<matches.size();i++){
                int year=Integer.parseInt(matches.get(i)[1]);
                int currentId=Integer.parseInt(matches.get(i)[0]);
                if(year==2015){
                    idList.add(currentId);
                }
            }

            for(int i=1;i<deliveries.size();i++){
                int matchId=Integer.parseInt(deliveries.get(i)[0]);
                if(idList.contains(matchId)){
                    String bowler=deliveries.get(i)[8];
                    //int totalRunsResult= RunsByBowler.getOrDefault(bowler,0);
                    int totalRuns=Integer.parseInt(deliveries.get(i)[17]);
                    int byeRuns=Integer.parseInt(deliveries.get(i)[11]);
                    int legByeRuns=Integer.parseInt(deliveries.get(i)[12]);
                    int result=totalRuns-(byeRuns+legByeRuns);
                    int currentRuns=runsByBowler.getOrDefault(bowler,0);
                    runsByBowler.put(bowler,currentRuns+result);
                    int isValid = (Integer.parseInt(deliveries.get(i)[10])==0)&&(Integer.parseInt(deliveries.get(i)[13]) == 0) ? 1 : 0;
                    int currentBalls=ballsByBowler.getOrDefault(bowler,0);
                    ballsByBowler.put(bowler,currentBalls+isValid);
                }
            }

            Map<String,Double> economyRateByBowler=new HashMap<>();
            for(String bowler:runsByBowler.keySet()){
                int totalRuns=runsByBowler.get(bowler);
                int totalBalls=ballsByBowler.get(bowler);
                double overs=totalBalls/6.0;
                double economyRate=totalRuns/overs;
                economyRateByBowler.put(bowler,economyRate);
            }

            List<Map.Entry<String, Double>> listOfMaps=new ArrayList<>(economyRateByBowler.entrySet());
            Collections.sort(listOfMaps,(argument1,argument2)->
                    argument1.getValue().compareTo(argument2.getValue()));
            System.out.println("\nAfter Sorting:");
            for (Map.Entry<String, Double> entry:listOfMaps) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }

        //Find the player with highest strike rate against MI in 2015 by teams .
        void highestStrikeRate(List<String[]> matches, List<String[]> deliveries){
            Map<String,Double> highestStrikeRate=new HashMap<>();
            Map<String,Integer> totalRunsOfBatter=new HashMap<>();
            Map<String,Integer> totalBallsOfBatter=new HashMap<>();
            List<Integer> idList=new ArrayList<>();
            for(int i=1;i<matches.size();i++){
                int year=Integer.parseInt(matches.get(i)[1]);
                if(year==2015){
                    idList.add(Integer.parseInt(matches.get(i)[0]));
                }
            }
            // System.out.print(idList);
            System.out.println("___________________________________________");
            System.out.println("Finding the strike rates");
            for(int i=1;i<deliveries.size();i++){
                int currentMatchId=Integer.parseInt(deliveries.get(i)[0]);
                String bowlingTeam=deliveries.get(i)[3];
                if(idList.contains(currentMatchId)&&(bowlingTeam.equals("Mumbai Indians"))){
                    String batter=deliveries.get(i)[6];
                    int runs=totalRunsOfBatter.getOrDefault(batter,0);
                    totalRunsOfBatter.put(batter,runs+(Integer.parseInt(deliveries.get(i)[17])));
                    int balls=totalBallsOfBatter.getOrDefault(batter,0);
                    totalBallsOfBatter.put(batter,balls+1);
                }
            }
            // Finding strike rate.
            for(String batter:totalRunsOfBatter.keySet()){
                int totalRuns=totalRunsOfBatter.get(batter);
                int totalBalls=totalBallsOfBatter.getOrDefault(batter,0);
                double strikeRate=(totalRuns/(double)totalBalls)*100;
                highestStrikeRate.put(batter,strikeRate);
            }
            //sorting
            List<Map.Entry<String,Double>> topList=new ArrayList<>(highestStrikeRate.entrySet());
            Collections.sort(topList,(value1,value2)->
                    value2.getValue().compareTo(value1.getValue()));
            //Displaying the list
            for(Map.Entry<String,Double> data:topList){
                System.out.println("Batter"+data.getKey()+ "Strike rate:"+data.getValue());
            }
            Map.Entry<String,Double> top=topList.get(0);
            System.out.println("Top strike rate"+top.getKey()+" "+top.getValue());
        }
        // Find the highest number of times one player has been dismissed by another player
        public void dismissedPlayer(List<String[]> deliveries) {
            Map<String, Map<String, Integer>> highestDismissalList = new HashMap<>();
            int maxDismissalCount = 0;
            String topBowler = "";
            String topBatter = "";


            for (int i = 1; i < deliveries.size(); i++) {
                String bowler = deliveries.get(i)[8];
                if (deliveries.get(i).length > 18) {
                    String batter = deliveries.get(i)[18];
                    if (batter == null || batter.isEmpty()) {
                        continue;
                    }
                    Map<String, Integer> dismissalPlayer = highestDismissalList.getOrDefault(bowler, new HashMap<>());
                    int count = dismissalPlayer.getOrDefault(batter, 0);
                    count++;
                    dismissalPlayer.put(batter, count);
                    highestDismissalList.put(bowler, dismissalPlayer);
                    if (count > maxDismissalCount) {
                        maxDismissalCount = count;
                        topBowler = bowler;
                        topBatter = batter;
                    }
                }
//            for(Map.Entry<String,Map<String,Integer>> entry:highestDismissalList.entrySet()){
//                String bowler=entry.getKey();
//                Map<String,Integer> dismissCount=entry.getValue();
//                //Printing the dismissal players list.
//                for(Map.Entry<String,Integer> e:dismissCount.entrySet()){
//                    String batter=e.getKey();
//                    int num=e.getValue();
//                    System.out.println("Bowler:"+bowler+"  Batter:"+batter+" Count:"+num);
//                }
//            }
                // Printing the top dismissal list
            }
            System.out.println("Top Dismissal pair : Bowler:" + topBowler + " Batter:" + topBatter + " Dismissal Count " + maxDismissalCount);
        }

    }
    class main{
        public static void main(String args[]) throws IOException {
            IPLCSVReader csvReader = new IPLCSVReader();
            List<String[]> matchesList=csvReader.getMatchesList();
            List<String[]> deliversList=csvReader.getDeliversList();
            Scenarios scenarios=new Scenarios();
//        scenarios.numberOfMatches(matchesList);
//        scenarios.numberOfMatchesWon(matchesList);
//        scenarios.extraRunsConceded(matchesList,deliversList);
//        scenarios.topEconomicalBowler(matchesList,deliversList);
//        scenarios.highestStrikeRate(matchesList,deliversList);
//        scenarios.dismissedPlayer(deliversList);
            SampleScenarios ss=new SampleScenarios();
            //ss.highestPlayerOfMatch(matchesList);
            //ss.dismissedPlayer(deliversList);
            //ss.tossAndMatchWon(matchesList);
            ss.superOverEconomyRate(deliversList);
        }
    }
\package com.IPLproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    public class IPLCSVReader {
        String matchesCSV = "/home/sireesha/Documents/Java_Projects/IPL _Project/matches.csv";
        String deliversCSV ="/home/sireesha/Documents/Java_Projects/IPL _Project/deliveries.csv";
        //      String matchesCSV="/home/sireesha/IdeaProjects/IPL_DataProject/src/deliveries.csv";
//      String deliversCSV="/home/sireesha/IdeaProjects/IPL_DataProject/src/matches.csv";
        List<String[]> matchesList = new ArrayList<>();
        List<String[]> deliversList = new ArrayList<>();

        public List<String[]> getMatchesList() throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(matchesCSV));
            String rowData;
            while ((rowData = bufferedReader.readLine()) != null) {
                String[] match = rowData.split(",");
                matchesList.add(match);
            }
            bufferedReader.close();
            return matchesList;
        }
        public List<String[]> getDeliversList() throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(deliversCSV));
            String rowData;
            while ((rowData = bufferedReader.readLine()) != null) {
                String[] delivery = rowData.split(",");
                deliversList.add(delivery);
            }
            bufferedReader.close();
            return deliversList;
        }
    }






    _____________________________________________________________________________________________________________________________________________________


import com.IPLproject.IPLCSVReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.List;
import java.util.*;

    public class Scenarios {
        //Number of matches played per year of all the years in IPL.
        void numberOfMatches(List<String[]> matches) {

            Map<Integer, Integer> season = new TreeMap<Integer, Integer>();
            for (int i = 1; i < matches.size(); i++) {
                int year = Integer.parseInt(matches.get(i)[1]);
                season.put(year, season.getOrDefault(year, 0) + 1);
            }
            System.out.println("Number of matches played per year of all the years in IPL.");
            for (Map.Entry<Integer, Integer> entry : season.entrySet()) {
                System.out.println("Year: " + entry.getKey() + ", Matches: " + entry.getValue());
            }
            System.out.println("____________________________________________________________");
        }

        //Number of matches won of all teams over all the years of IPL.
        void numberOfMatchesWon(List<String[]> matches) {
            Map<String, Integer> matchesWon = new TreeMap<String, Integer>();
            for (int i = 1; i < matches.size(); i++) {
                String winner = matches.get(i)[10];
                if (!winner.isEmpty()) {
                    matchesWon.put(winner, matchesWon.getOrDefault(winner, 0) + 1);
                }
            }
            System.out.println("Number of matches won of all teams over all the years of IPL.");
            for (Map.Entry<String, Integer> entry : matchesWon.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }

        //For the year 2016 get the extra runs conceded per team.
        void extraRunsConceded(List<String[]> matches, List<String[]> deliveries) {
            Map<String,Integer> extraRuns = new HashMap<>();
            TreeSet<Integer> idList = new TreeSet<>();
            System.out.println("___________________________________");
            System.out.println("For the year 2016 get the extra runs conceded per team.");
            for(int i = 1; i < matches.size(); i++) {
                int currentid = Integer.parseInt(matches.get(i)[0]);
                int year = Integer.parseInt(matches.get(i)[1]);
                if(year == 2016) {
                    idList.add(currentid);
                }
            }

            for(int i = 1; i < deliveries.size(); i++) {
                int matchId = Integer.parseInt(deliveries.get(i)[0]);
                if(idList.contains(matchId)) {
                    String team = deliveries.get(i)[3];
                    int runs = extraRuns.getOrDefault(team, 0);
                    int currentExtraRuns = Integer.parseInt(deliveries.get(i)[16]);
                    extraRuns.put(team, runs + currentExtraRuns);
                }
            }
            for (Map.Entry<String, Integer> entry : extraRuns.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());

            }
        }

        //For the year 2015 get the top economical bowlers.

        void topEconomicalBowler(List<String[]> matches, List<String[]> deliveries){
            System.out.println("________________________________________");
            System.out.println("For the year 2015 get the top economical bowlers.");
            TreeSet<Integer> idList=new TreeSet<>();
            Map<String,Integer> runsByBowler=new HashMap<>();
            Map<String,Integer> ballsByBowler=new HashMap<>();
            for(int i=1;i<matches.size();i++){
                int year=Integer.parseInt(matches.get(i)[1]);
                int currentId=Integer.parseInt(matches.get(i)[0]);
                if(year==2015){
                    idList.add(currentId);
                }
            }

            for(int i=1;i<deliveries.size();i++){
                int matchId=Integer.parseInt(deliveries.get(i)[0]);
                if(idList.contains(matchId)){
                    String bowler=deliveries.get(i)[8];
                    //int totalRunsResult= RunsByBowler.getOrDefault(bowler,0);
                    int totalRuns=Integer.parseInt(deliveries.get(i)[17]);
                    int byeRuns=Integer.parseInt(deliveries.get(i)[11]);
                    int legByeRuns=Integer.parseInt(deliveries.get(i)[12]);
                    int result=totalRuns-(byeRuns+legByeRuns);
                    int currentRuns=runsByBowler.getOrDefault(bowler,0);
                    runsByBowler.put(bowler,currentRuns+result);
                    int isValid = (Integer.parseInt(deliveries.get(i)[10])==0)&&(Integer.parseInt(deliveries.get(i)[13]) == 0) ? 1 : 0;
                    int currentBalls=ballsByBowler.getOrDefault(bowler,0);
                    ballsByBowler.put(bowler,currentBalls+isValid);
                }
            }

            Map<String,Double> economyRateByBowler=new HashMap<>();
            for(String bowler:runsByBowler.keySet()){
                int totalRuns=runsByBowler.get(bowler);
                int totalBalls=ballsByBowler.get(bowler);
                double overs=totalBalls/6.0;
                double economyRate=totalRuns/overs;
                economyRateByBowler.put(bowler,economyRate);
            }

            List<Map.Entry<String, Double>> listOfMaps=new ArrayList<>(economyRateByBowler.entrySet());
            Collections.sort(listOfMaps,(argument1,argument2)->
                    argument1.getValue().compareTo(argument2.getValue()));
            System.out.println("\nAfter Sorting:");
            for (Map.Entry<String, Double> entry:listOfMaps) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }

        //Find the player with highest strike rate against MI in 2015 by teams .
        void highestStrikeRate(List<String[]> matches, List<String[]> deliveries){
            Map<String,Double> highestStrikeRate=new HashMap<>();
            Map<String,Integer> totalRunsOfBatter=new HashMap<>();
            Map<String,Integer> totalBallsOfBatter=new HashMap<>();
            List<Integer> idList=new ArrayList<>();
            for(int i=1;i<matches.size();i++){
                int year=Integer.parseInt(matches.get(i)[1]);
                if(year==2015){
                    idList.add(Integer.parseInt(matches.get(i)[0]));
                }
            }
            // System.out.print(idList);
            System.out.println("___________________________________________");
            System.out.println("Finding the strike rates");
            for(int i=1;i<deliveries.size();i++){
                int currentMatchId=Integer.parseInt(deliveries.get(i)[0]);
                String bowlingTeam=deliveries.get(i)[3];
                if(idList.contains(currentMatchId)&&(bowlingTeam.equals("Mumbai Indians"))){
                    String batter=deliveries.get(i)[6];
                    int runs=totalRunsOfBatter.getOrDefault(batter,0);
                    totalRunsOfBatter.put(batter,runs+(Integer.parseInt(deliveries.get(i)[17])));
                    int balls=totalBallsOfBatter.getOrDefault(batter,0);
                    totalBallsOfBatter.put(batter,balls+1);
                }
            }
            // Finding strike rate.
            for(String batter:totalRunsOfBatter.keySet()){
                int totalRuns=totalRunsOfBatter.get(batter);
                int totalBalls=totalBallsOfBatter.getOrDefault(batter,0);
                double strikeRate=(totalRuns/(double)totalBalls)*100;
                highestStrikeRate.put(batter,strikeRate);
            }
            //sorting
            List<Map.Entry<String,Double>> topList=new ArrayList<>(highestStrikeRate.entrySet());
            Collections.sort(topList,(value1,value2)->
                    value2.getValue().compareTo(value1.getValue()));
            //Displaying the list
            for(Map.Entry<String,Double> data:topList){
                System.out.println("Batter"+data.getKey()+ "Strike rate:"+data.getValue());
            }
            Map.Entry<String,Double> top=topList.get(0);
            System.out.println("Top strike rate"+top.getKey()+" "+top.getValue());
        }
        // Find the highest number of times one player has been dismissed by another player
        public void dismissedPlayer(List<String[]> deliveries) {
            Map<String, Map<String, Integer>> highestDismissalList = new HashMap<>();
            int maxDismissalCount = 0;
            String topBowler = "";
            String topBatter = "";


            for (int i = 1; i < deliveries.size(); i++) {
                String bowler = deliveries.get(i)[8];
                if (deliveries.get(i).length > 18) {
                    String batter = deliveries.get(i)[18];
                    if (batter == null || batter.isEmpty()) {
                        continue;
                    }
                    Map<String, Integer> dismissalPlayer = highestDismissalList.getOrDefault(bowler, new HashMap<>());
                    int count = dismissalPlayer.getOrDefault(batter, 0);
                    count++;
                    dismissalPlayer.put(batter, count);
                    highestDismissalList.put(bowler, dismissalPlayer);
                    if (count > maxDismissalCount) {
                        maxDismissalCount = count;
                        topBowler = bowler;
                        topBatter = batter;
                    }
                }
//            for(Map.Entry<String,Map<String,Integer>> entry:highestDismissalList.entrySet()){
//                String bowler=entry.getKey();
//                Map<String,Integer> dismissCount=entry.getValue();
//                //Printing the dismissal players list.
//                for(Map.Entry<String,Integer> e:dismissCount.entrySet()){
//                    String batter=e.getKey();
//                    int num=e.getValue();
//                    System.out.println("Bowler:"+bowler+"  Batter:"+batter+" Count:"+num);
//                }
//            }
                // Printing the top dismissal list
            }
            System.out.println("Top Dismissal pair : Bowler:" + topBowler + " Batter:" + topBatter + " Dismissal Count " + maxDismissalCount);
        }

    }
    class main{
        public static void main(String args[]) throws IOException {
            IPLCSVReader csvReader = new IPLCSVReader();
            List<String[]> matchesList=csvReader.getMatchesList();
            List<String[]> deliversList=csvReader.getDeliversList();
            Scenarios scenarios=new Scenarios();
//        scenarios.numberOfMatches(matchesList);
//        scenarios.numberOfMatchesWon(matchesList);
//        scenarios.extraRunsConceded(matchesList,deliversList);
//        scenarios.topEconomicalBowler(matchesList,deliversList);
//        scenarios.highestStrikeRate(matchesList,deliversList);
//        scenarios.dismissedPlayer(deliversList);
            SampleScenarios ss=new SampleScenarios();
            //ss.highestPlayerOfMatch(matchesList);
            //ss.dismissedPlayer(deliversList);
            //ss.tossAndMatchWon(matchesList);
            ss.superOverEconomyRate(deliversList);
        }
    }



}

