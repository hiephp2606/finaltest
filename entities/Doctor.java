package entities;

public class Doctor extends Person {
    private String medicalSpecialist;
    private String dateIN;
    private String dateOUT;
    private String information;
    private String condition;
    private double salary;

    public Doctor(String name, String birth, int personalNumber, int phoneNumber, String email, String medicalSpecialist, String dateIN, String dateOUT, String information, String condition, double salary) {
        super(name, birth, personalNumber, phoneNumber, email);
        this.medicalSpecialist = medicalSpecialist;
        this.dateIN = dateIN;
        this.dateOUT = dateOUT;
        this.information = information;
        this.condition = condition;
        this.salary = salary;
    }

    public String getMedicalSpecialist() {
        return medicalSpecialist;
    }

    public void setMedicalSpecialist(String medicalSpecialist) {
        this.medicalSpecialist = medicalSpecialist;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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
        return "Doctor{" +
                "medicalSpecialist='" + medicalSpecialist + '\'' +
                ", dateIN='" + dateIN + '\'' +
                ", dateOUT='" + dateOUT + '\'' +
                ", information='" + information + '\'' +
                ", condition='" + condition + '\'' +
                ", salary=" + salary +
                '}';
    }
}
