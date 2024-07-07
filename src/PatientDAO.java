// PatientDAO.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void addPatient(Patient patient) {
        String query = "INSERT INTO patients (name, age, gender, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(rs.getString("gender"));
                patient.setAddress(rs.getString("address"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    // Add methods for update, delete, and search (if needed)
}
