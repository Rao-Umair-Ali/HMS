import java.time.LocalDate;

public class Checkup {
    private int id;
    private int patientId;
    private int doctorId;
    private LocalDate checkupDate;
    private String diagnosis;
    private String treatment;

    // Constructor
    public Checkup() {}

    public Checkup(int id, int patientId, int doctorId, LocalDate checkupDate, String diagnosis, String treatment) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.checkupDate = checkupDate;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getCheckupDate() {
        return checkupDate;
    }

    public void setCheckupDate(LocalDate checkupDate) {
        this.checkupDate = checkupDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
