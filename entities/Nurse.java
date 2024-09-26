package entities;

public class Nurse extends Person {
    private String dateIN;
    private String dateOUT;
    private String schedule;
    private String condition;
    private double salary;

    public Nurse(String name, String birth, int personalNumber, int phoneNumber, String email, String dateIN, String dateOUT, String schedule, String condition, double salary) {
        super(name, birth, personalNumber, phoneNumber, email);
        this.dateIN = dateIN;
        this.dateOUT = dateOUT;
        this.schedule = schedule;
        this.condition = condition;
        this.salary = salary;
    }

    public String getDateIN() {
        return dateIN;
    }

    public void setDateIN(String dateIN) {
        this.dateIN = dateIN;
    }

    public String getDateOUT() {
        return dateOUT;
    }

    public void setDateOUT(String dateOUT) {
        this.dateOUT = dateOUT;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "dateIN='" + dateIN + '\'' +
                ", dateOUT='" + dateOUT + '\'' +
                ", schedule='" + schedule + '\'' +
                ", condition='" + condition + '\'' +
                ", salary=" + salary +
                '}';
    }
}
