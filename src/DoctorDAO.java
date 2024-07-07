// DoctorDAO.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (name, specialty, experience) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialty());
            stmt.setInt(3, doctor.getExperience());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setExperience(rs.getInt("experience"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    // Add methods for update, delete, and search (if needed)
}
