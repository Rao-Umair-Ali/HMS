import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckupDAO {

    public void addCheckup(Checkup checkup) {
        String query = "INSERT INTO checkups (patient_id, doctor_id, checkup_date, diagnosis, treatment) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, checkup.getPatientId());
            stmt.setInt(2, checkup.getDoctorId());
            stmt.setDate(3, Date.valueOf(checkup.getCheckupDate()));
            stmt.setString(4, checkup.getDiagnosis());
            stmt.setString(5, checkup.getTreatment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Checkup> getAllCheckups() {
        List<Checkup> checkups = new ArrayList<>();
        String query = "SELECT * FROM checkups";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Checkup checkup = new Checkup();
                checkup.setId(rs.getInt("id"));
                checkup.setPatientId(rs.getInt("patient_id"));
                checkup.setDoctorId(rs.getInt("doctor_id"));
                checkup.setCheckupDate(rs.getDate("checkup_date").toLocalDate());
                checkup.setDiagnosis(rs.getString("diagnosis"));
                checkup.setTreatment(rs.getString("treatment"));
                checkups.add(checkup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkups;
    }

    // Add methods for update, delete, and search (if needed)
}
