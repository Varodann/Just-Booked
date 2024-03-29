package com.example.mainApp.Controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminLobbyController implements Initializable {

    String nameOfUser;

    @FXML
    private Label labelName;
    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane slider;

    /**
     * Metoda initialize - odpowiada za "przygotowanie" i wykonuje sie przed kazdym wywowałniem @FXML
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        slider.setTranslateX(-200);

        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-200);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-200);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });
    }

    /**
     * Metoda displayName - odpowiada za wyswietlenie nazwy uzytkownika w lobby
     */
    public void displayName(String userName) {
        labelName.setText("Witaj " + userName + " !");
    }

    /**
     * Metoda getName - odpowiada za przekazanie z poprzednich stagow/scen nazwy uzytkownika
     */
    public void getName(String userName) {
        nameOfUser = userName;
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Metoda addRoom - odpowiada za przejscie do kolejnego stage'a
     */
    @FXML
    public void addRoom(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (nameOfUser.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyAddRoomAdmin.fxml"));
        //}
        root = loader.load();
        AdminAddController adminAddController = loader.getController();
        adminAddController.getUserName2(nameOfUser);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda editRoom - odpowiada za przejscie do kolejnego stage'a
     */
    @FXML
    public void editRoom(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (nameOfUser.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyEditRoomAdmin.fxml"));
        //}
        root = loader.load();
        AdminEditController adminEditController = loader.getController();
        adminEditController.getUserName2(nameOfUser);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda showReservations - odpowiada za przejscie do kolejnego stage'a
     */
    @FXML
    public void showReservations(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (nameOfUser.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyShowReserv.fxml"));
        //}
        root = loader.load();
        AdminShowReservController adminShowReservController = loader.getController();
        adminShowReservController.getUserName2(nameOfUser);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda cancelReservation - odpowiada za przejscie do kolejnego stage'a
     */
    @FXML
    public void cancelReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (nameOfUser.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyCancelResrv.fxml"));
        //}
        root = loader.load();
        AdminCancelReservController adminCancelReservController = loader.getController();
        adminCancelReservController.getUserName2(nameOfUser);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda contactAdmin - odpowiada za przejscie do kolejnego stage'a
     */
    @FXML
    public void contactAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (nameOfUser.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyContactAdmin.fxml"));
        //}
        root = loader.load();
        AdminContactController adminContactController = loader.getController();
        adminContactController.getUserName2(nameOfUser);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda exit - wyjscie z programu
     */
    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }

}
