package entities;

public class Patient extends Person {
    private String disease;
    private String condition;
    private String dateIN;
    private String dateOUT;
    private String doctorInCharge;
    private String nurseInCharge;
    private double hospitalFees;
    private double paymentCondition;
    private String familyMember;
    private String visitDate;

    public Patient(String name, String birth, int personalNumber, int phoneNumber, String email, String disease, String condition, String dateIN, String dateOUT, String doctorInCharge, String nurseInCharge, double hospitalFees, double paymentCondition, String familyMember, String visitDate) {
        super(name, birth, personalNumber, phoneNumber, email);
        this.disease = disease;
        this.condition = condition;
        this.dateIN = dateIN;
        this.dateOUT = dateOUT;
        this.doctorInCharge = doctorInCharge;
        this.nurseInCharge = nurseInCharge;
        this.hospitalFees = hospitalFees;
        this.paymentCondition = paymentCondition;
        this.familyMember = familyMember;
        this.visitDate = visitDate;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public String getDoctorInCharge() {
        return doctorInCharge;
    }

    public void setDoctorInCharge(String doctorInCharge) {
        this.doctorInCharge = doctorInCharge;
    }

    public String getNurseInCharge() {
        return nurseInCharge;
    }

    public void setNurseInCharge(String nurseInCharge) {
        this.nurseInCharge = nurseInCharge;
    }

    public double getHospitalFees() {
        return hospitalFees;
    }

    public void setHospitalFees(double hospitalFees) {
        this.hospitalFees = hospitalFees;
    }

    public double getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(double paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public String getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "disease='" + disease + '\'' +
                ", condition='" + condition + '\'' +
                ", dateIN='" + dateIN + '\'' +
                ", dateOUT='" + dateOUT + '\'' +
                ", doctorInCharge='" + doctorInCharge + '\'' +
                ", nurseInCharge='" + nurseInCharge + '\'' +
                ", hospitalFees=" + hospitalFees +
                ", paymentCondition=" + paymentCondition +
                ", familyMember='" + familyMember + '\'' +
                ", visitDate='" + visitDate + '\'' +
                '}';
    }
}
