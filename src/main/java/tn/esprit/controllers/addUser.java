package tn.esprit.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import tn.esprit.entities.User;
import tn.esprit.services.ServiceUser;
import javafx.scene.control.CheckBox;


public class addUser {
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailId;

    @FXML
    private CheckBox isVerifiedId;

    @FXML
    private TextField passwordId;

    @FXML
    private ComboBox<String> roleId;

    @FXML
    private TextField usernameId;

    @FXML
    void addUser(ActionEvent event) {
        ServiceUser serviceUser = new ServiceUser();
        User user = new User();
        user.setUsername(usernameId.getText());
        user.setEmail(emailId.getText());
        user.setPassword(passwordId.getText());

        // Set isVerified based on CheckBox state
        boolean isVerified = isVerifiedId.isSelected(); // Changed from isverifiedId
        user.setIs_verified(isVerified);

        String selectedRole = roleId.getValue();
        if (selectedRole == null || selectedRole.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a role.");
            return; // Stop processing if role is not selected
        }

        // Correspondance des rôles sélectionnés avec les valeurs attendues
        String roleValue;
        switch (selectedRole) {
            case "Administrateur":
                roleValue = "[\"ROLE_ADMIN\"]";
                break;
            case "Recruteur":
                roleValue = "[\"ROLE_RECRUTEUR\"]";
                break;
            case "Freelancer":
                roleValue = "[\"ROLE_FREELANCER\"]" ;
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid role selected.");
                return; // Stop processing if an invalid role is selected
        }

        user.setRoles(roleValue);

        try {
            serviceUser.add(user);
            showAlert(Alert.AlertType.CONFIRMATION, "Success", "User added");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    /*
    @FXML
    public void initialize() {
        initializeRoleComboBox();
    }

    private void initializeRoleComboBox() {
        List<String> rolesList = Arrays.asList("Administrateur", "Recruteur", "Freelancer");
        roleId.setItems(FXCollections.observableArrayList(rolesList));
    }
     */
}
