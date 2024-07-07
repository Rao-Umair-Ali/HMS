// HospitalManagementApp.java
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class HospitalManagementApp extends Application {

    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private CheckupDAO checkupDAO = new CheckupDAO();
    private ListView<String> patientListView = new ListView<>();
    private ListView<String> doctorListView = new ListView<>();
    private ListView<String> checkupListView = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        Tab patientTab = new Tab("Patients", createPatientTabContent());
        Tab doctorTab = new Tab("Doctors", createDoctorTabContent());
        Tab checkupTab = new Tab("Checkups", createCheckupTabContent());

        tabPane.getTabs().addAll(patientTab, doctorTab, checkupTab);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hospital Management System");
        primaryStage.show();
    }

    private VBox createPatientTabContent() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));

        HBox hbox = new HBox(10);
        Button addPatientButton = new Button("Add Patient");
        Button loadPatientsButton = new Button("Load Patients");

        loadPatientsButton.setOnAction(e -> loadPatients());

        hbox.getChildren().addAll(addPatientButton, loadPatientsButton);
        vbox.getChildren().addAll(hbox, patientListView);

        addPatientButton.setOnAction(e -> showAddPatientDialog());

        return vbox;
    }

    private VBox createDoctorTabContent() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));

        HBox hbox = new HBox(10);
        Button addDoctorButton = new Button("Add Doctor");
        Button loadDoctorsButton = new Button("Load Doctors");

        loadDoctorsButton.setOnAction(e -> loadDoctors());

        hbox.getChildren().addAll(addDoctorButton, loadDoctorsButton);
        vbox.getChildren().addAll(hbox, doctorListView);

        addDoctorButton.setOnAction(e -> showAddDoctorDialog());

        return vbox;
    }

    private VBox createCheckupTabContent() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));

        HBox hbox = new HBox(10);
        Button addCheckupButton = new Button("Add Checkup");
        Button loadCheckupsButton = new Button("Load Checkups");

        loadCheckupsButton.setOnAction(e -> loadCheckups());

        hbox.getChildren().addAll(addCheckupButton, loadCheckupsButton);
        vbox.getChildren().addAll(hbox, checkupListView);

        addCheckupButton.setOnAction(e -> showAddCheckupDialog());

        return vbox;
    }

    private void loadPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        patientListView.getItems().clear();
        for (Patient patient : patients) {
            patientListView.getItems().add(patient.getName() + " - " + patient.getAge() + " years old");
        }
    }

    private void loadDoctors() {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        doctorListView.getItems().clear();
        for (Doctor doctor : doctors) {
            doctorListView.getItems().add(doctor.getName() + " - " + doctor.getSpecialty());
        }
    }

    private void loadCheckups() {
        List<Checkup> checkups = checkupDAO.getAllCheckups();
        checkupListView.getItems().clear();
        for (Checkup checkup : checkups) {
            checkupListView.getItems().add("Patient ID: " + checkup.getPatientId() + " - Doctor ID: " + checkup.getDoctorId() + " - Date: " + checkup.getCheckupDate());
        }
    }

    private void showAddPatientDialog() {
        Dialog<Patient> dialog = new Dialog<>();
        dialog.setTitle("Add New Patient");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        TextField genderField = new TextField();
        genderField.setPromptText("Gender");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Age:"), 0, 1);
        grid.add(ageField, 1, 1);
        grid.add(new Label("Gender:"), 0, 2);
        grid.add(genderField, 1, 2);
        grid.add(new Label("Address:"), 0, 3);
        grid.add(addressField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                Patient patient = new Patient();
                patient.setName(nameField.getText());
                patient.setAge(Integer.parseInt(ageField.getText()));
                patient.setGender(genderField.getText());
                patient.setAddress(addressField.getText());
                return patient;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(patient -> {
            patientDAO.addPatient(patient);
            loadPatients();
        });
    }

    private void showAddDoctorDialog() {
        Dialog<Doctor> dialog = new Dialog<>();
        dialog.setTitle("Add New Doctor");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField specialtyField = new TextField();
        specialtyField.setPromptText("Specialty");
        TextField experienceField = new TextField();
        experienceField.setPromptText("Experience");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Specialty:"), 0, 1);
        grid.add(specialtyField, 1, 1);
        grid.add(new Label("Experience:"), 0, 2);
        grid.add(experienceField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                Doctor doctor = new Doctor();
                doctor.setName(nameField.getText());
                doctor.setSpecialty(specialtyField.getText());
                doctor.setExperience(Integer.parseInt(experienceField.getText()));
                return doctor;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(doctor -> {
            doctorDAO.addDoctor(doctor);
            loadDoctors();
        });
    }

    private void showAddCheckupDialog() {
        Dialog<Checkup> dialog = new Dialog<>();
        dialog.setTitle("Add New Checkup");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField patientIdField = new TextField();
        patientIdField.setPromptText("Patient ID");
        TextField doctorIdField = new TextField();
        doctorIdField.setPromptText("Doctor ID");
        TextField checkupDateField = new TextField();
        checkupDateField.setPromptText("Checkup Date (YYYY-MM-DD)");
        TextField diagnosisField = new TextField();
        diagnosisField.setPromptText("Diagnosis");
        TextField treatmentField = new TextField();
        treatmentField.setPromptText("Treatment");

        grid.add(new Label("Patient ID:"), 0, 0);
        grid.add(patientIdField, 1, 0);
        grid.add(new Label("Doctor ID:"), 0, 1);
        grid.add(doctorIdField, 1, 1);
        grid.add(new Label("Checkup Date:"), 0, 2);
        grid.add(checkupDateField, 1, 2);
        grid.add(new Label("Diagnosis:"), 0, 3);
        grid.add(diagnosisField, 1, 3);
        grid.add(new Label("Treatment:"), 0, 4);
        grid.add(treatmentField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                Checkup checkup = new Checkup();
                checkup.setPatientId(Integer.parseInt(patientIdField.getText()));
                checkup.setDoctorId(Integer.parseInt(doctorIdField.getText()));
                checkup.setCheckupDate(LocalDate.parse(checkupDateField.getText()));
                checkup.setDiagnosis(diagnosisField.getText());
                checkup.setTreatment(treatmentField.getText());
                return checkup;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(checkup -> {
            checkupDAO.addCheckup(checkup);
            loadCheckups();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
