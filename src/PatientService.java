import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PatientService {

    public List<Patient> searchPatientByName(List<Patient> patients, String name) {
        return patients.stream()
                .filter(patient -> patient.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void sortPatientsByName(List<Patient> patients) {
        Collections.sort(patients, Comparator.comparing(Patient::getName));
    }

    // Add other searching and sorting methods as needed
}
