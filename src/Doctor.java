public class Doctor {
    private int id;
    private String name;
    private String specialty;
    private int experience;

    // Constructor
    public Doctor() {}

    public Doctor(int id, String name, String specialty, int experience) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.experience = experience;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
