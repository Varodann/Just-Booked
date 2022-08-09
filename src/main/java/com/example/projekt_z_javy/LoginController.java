package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;

    /* DO ZROBIENIA */

    //Usunąc obramowania z przyiskami funkcyjnymi (zminimalizuj, powieksz zamknij)
    //Zablokowac możliwośc zmiany rozmiaru



    /**
     * Funkcja odpowiedzjaca z aobsulge przycisku logowania - poprawic przejscia miedzy scneanmi (odkomentowac)
     */
    public void handleButtonPressedLogin(ActionEvent actionEvent) throws SQLException, IOException {
        Main m = new Main();
        try {

            if (name.getText().isEmpty() || password.getText().isEmpty()) {
                m.changeScene("login-error.fxml");
                name.setPromptText("Brak nazwy ");
                password.setPromptText("Brak hasła");
            } else {
                if (JavaPostgreSQL_login.checkUserCor(name.getText(), password.getText())) {
                Parent home_page_parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("lobby.fxml")));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.setScene(home_page_scene);
                app_stage.show();
                    System.out.println("Udalo sie");
                } else {
                    m.changeScene("login-error.fxml");
                    System.out.println("Nie ma przejscia");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void goToRegistration() throws IOException {
        Main m = new Main();
        m.changeScene("register.fxml");
    }

    /**
     * Funkcja odpowiedzjaca z aobsulge przycisku rejestracji
     */
    public void handleButtonPressedRegister(ActionEvent event) throws IOException {
        try {
            System.out.println("Rejestruje...");
            goToRegistration();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Funkcja odpowiadajca za zakoncenie działania apliakcji
     */

    /* private void goToMainPage() throws IOException {
        try {
            Main m = new Main();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("home.fxml").openStream());

            stage.setScene(new Scene(root, 600, 400));
            m.offScene();
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    */
    @FXML
    private void exit(ActionEvent e){
        System.exit(0);
    }
}
