package com.example.projekt_z_javy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class Lobby {

    @FXML
    private Button wylogujButton;

    @FXML
    private Button dodajButton;

    @FXML
    private Button edytujButton;

    @FXML
    private Button wyswietlButton;


    public void userLogout(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("Wylogowuje...");
        Main m = new Main();
        m.changeScene("login-view.fxml");
        m.onScene();
    }

    public void goToAddReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Dodaje rezerwacje...");
    }

    public void goToEditReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Edytuje rezerwacje...");
    }

    public void goToShowReservation(ActionEvent actionEvent) throws SQLException {
        System.out.println("Wyswietlam rezerwacje...");
    }

}



