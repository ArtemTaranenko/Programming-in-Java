package Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fishing
{
    private LocalDate date;
    private String dateFormat;
    private DayOfWeek day;
    private String species;
    private String fisherName;
    private int id;
    private double weight;
    private double length;


    public Fishing(int year, int month, int day, String species, String fisherName, int id, double weight, double length) {
        this.date = LocalDate.of(year, month, day);
        this.day = date.getDayOfWeek();
        this.species = species;
        this.fisherName = fisherName;
        this.id = id;
        this.weight = weight;
        this.length = length;
        formatDate();
    }

    private void formatDate()
    {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.dateFormat = date.format(formatters);
    }

    @Override
    public String toString() {
        return "Fishing result: " +
                " " + dateFormat + " (" + day.toString() + "), "
                + species +
                ", " + fisherName +
                " (" + id + "), "
                + weight +
                ", " + length;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getFisherName() {
        return fisherName;
    }

    public void setFisherName(String fisherName) {
        this.fisherName = fisherName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
