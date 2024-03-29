package com.example.mainApp.Controllers;

import com.example.mainApp.*;
import com.example.mainApp.Hibernate.JavaPostgreAdminHIB_addRoom;
import com.example.mainApp.SQL.JavaPostgreSQL_adding;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminAddController implements Initializable {

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private TextField roomName_textFiled;

    @FXML
    private AnchorPane slider;

    private Stage stage;
    private Scene scene;
    private Parent root;

    String myUserName;
    String roomName;

    int id_uzyt;

    /**
     * Metoda getUserName2 - odpowiada za przekazanie z poprzednich stagow/scen nazwy uzytkownika
     */
    public void getUserName2(String username) {
        myUserName = username;
        id_uzyt = JavaPostgreSQL_adding.getUserId(myUserName);
        //System.out.println(id_uzyt);
    }

    /**
     * Metoda exit - wyjscie z programu
     */
    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Metoda initialize - odpowiada za "przygotowanie" i wykonuje sie przed kazdym wywowałniem @FXML
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Problem jest w tym ze initialazje dzieje sie szybciej niż szczytanie nazyw uzytkownika

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
     * Metoda userLogout - odpowiada za wylogowanie uzytkownika i przejscie do ekranu ponownego logowania
     * !PRACE TRWAJĄ!
     */
    public void userLogout(ActionEvent actionEvent) throws SQLException, IOException {
        //System.out.println("Wylogowuje...");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Metoda addRoomToDB - odpowiada z wywolanie metody z klasy 'JavaPostgreAdminHIB_addRoom' powodująca wpisanie pokoju do bazy danych
     */
    @FXML
    public void addRoomToDB(ActionEvent actionEvent) {
        roomName = roomName_textFiled.getText();
        //System.out.println(roomName);
        JavaPostgreAdminHIB_addRoom.writeRoomToDatabase(roomName);
        AlertBox.display("Uwaga!", "Dodano pokój!");
    }

    /**
     * Metoda addRoom - odpowiada za przejscie do kolejnego stage'a
     */
    @FXML
    public void addRoom(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyAddRoomAdmin.fxml"));
        //}
        root = loader.load();
        AdminAddController adminAddController = loader.getController();
        adminAddController.getUserName2(myUserName);
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
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyEditRoomAdmin.fxml"));
        //}
        root = loader.load();
        AdminEditController adminEditController = loader.getController();
        adminEditController.getUserName2(myUserName);
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
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyShowReserv.fxml"));
        //}
        root = loader.load();
        AdminShowReservController adminShowReservController = loader.getController();
        adminShowReservController.getUserName2(myUserName);
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
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyCancelResrv.fxml"));
        //}
        root = loader.load();
        AdminCancelReservController adminCancelReservController = loader.getController();
        adminCancelReservController.getUserName2(myUserName);
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
        //if (myUserName.equals("admin")) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyContactAdmin.fxml"));
        //}
        root = loader.load();
        AdminContactController adminContactController = loader.getController();
        adminContactController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
