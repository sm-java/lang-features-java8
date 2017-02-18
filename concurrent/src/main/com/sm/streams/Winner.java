/**
 * Created by smdeveloper on 2/17/17.
 * https://dzone.com/articles/a-java-8-streams-cookbook
 */

package sm.utils.streams;

import java.time.Duration;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.*;

/**
 * A cutom class to demonstrate usage of Java Stream Library.
 *
 *
 */
public class Winner {
    private int year;
    private String nationality;
    private String name;
    private String team;
    private int lengthKm;
    private Duration winningTime;
    private int daysInYellow;
    private int stageWins;

    public Winner(int year, String nationality, String name, String team, int lengthKm, Duration winningTime, int daysInYellow) {
        this.year = year;
        this.nationality = nationality;
        this.name = name;
        this.team = team;
        this.lengthKm = lengthKm;
        this.winningTime = winningTime;
        this.daysInYellow = daysInYellow;
    }

    public static final List<Winner> tdfWinners = Arrays.asList(
            new Winner(2006, "Spain", "Óscar Pereiro", "Caisse d'Epargne–Illes Balears", 3657, Duration.parse("PT89H40M27S"), 8),
            new Winner(2007, "Spain", "Alberto Contador", "Discovery Channel", 3570, Duration.parse("PT91H00M26S"), 4),
            new Winner(2008, "Spain", "Carlos Sastre", "Team CSC", 3559, Duration.parse("PT87H52M52S"), 5),
            new Winner(2009, "Spain", "Alberto Contador", "Astana", 3459, Duration.parse("PT85H48M35S"), 7),
            new Winner(2010, "Luxembourg", "Andy Schleck", "Team Saxo Bank", 3642, Duration.parse("PT91H59M27S"), 12),
            new Winner(2011, "Australia", "Cadel Evans", "BMC Racing Team", 3430, Duration.parse("PT86H12M22S"), 2),
            new Winner(2012, "Great Britain", "Bradley Wiggins", "Team Sky", 3496, Duration.parse("PT87H34M47S"), 14),
            new Winner(2013, "Great Britain", "Chris Froome", "Team Sky", 3404, Duration.parse("PT83H56M20S"), 14),
            new Winner(2014, "Italy", "Vincenzo Nibali", "Astana", 3661, Duration.parse("PT89H59M06S"), 19),
            new Winner(2015, "Great Britain", "Chris Froome", "Team Sky", 3360, Duration.parse("PT84H46M14S"), 16),
            new Winner(2016, "Great Britain", "Chris Froome", "Team Sky", 3529, Duration.parse("PT89H04M48S"), 14 ));


    public static void main(String[] args) {

        //FilterandMap
        List<String> winnersOfToursLessThan3500km  = tdfWinners
                                                        .stream()
                                                        .filter(d -> d.getLengthKm() < 3500)
                                                        .map(Winner::getName)
                                                        .collect(toList());
        System.out.println("Winners of Tours Less than 3500km - " + winnersOfToursLessThan3500km);

        //Winner of tour less than 3500km limit 2
        List<String> sameAsAboveLimit2 =  tdfWinners
                                            .stream()
                                            .filter(d -> d.getLengthKm() < 3500)
                                            .map(Winner::getName)
                                            .limit(2)
                                            .collect(toList());

        System.out.println("Winners of Tours Less than 3500km - pick 2" + sameAsAboveLimit2);

        //get the first 2 winners of 3500km
        List<String> distinctWinners3500 = tdfWinners
                                             .stream()
                                             .filter(d -> d.getLengthKm() < 3500 )
                                             .map(Winner::getName)
                                             .distinct()
                                             .collect(toList());

        System.out.println("Distinct Winners of Tours Less than 3500km - " + distinctWinners3500);

        //Number of distinct winners
        long distinctWinnersNum =  tdfWinners.stream().map(Winner::getName).distinct().count();
        System.out.println("Number of distinct winners - " + distinctWinnersNum);

    }

    public double getAveSpeed() {
        return (getLengthKm() / (getWinningTime().getSeconds() / 3600) );
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public int getLengthKm() {
        return lengthKm;
    }
    public void setLengthKm(int lengthKm) {
        this.lengthKm = lengthKm;
    }
    public Duration getWinningTime() {
        return winningTime;
    }
    public void setWinningTime(Duration winningTime) {
        this.winningTime = winningTime;
    }
    public int getStageWins() {
        return stageWins;
    }
    public void setStageWins(int stageWins) {
        this.stageWins = stageWins;
    }
    public int getDaysInYellow() {
        return daysInYellow;
    }
    public void setDaysInYellow(int daysInYellow) {
        this.daysInYellow = daysInYellow;
    }
    @Override
    public String toString() {
        return name;
    }
}
