package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Godziny;
import com.example.mainApp.projekt_z_javy.entity.Pokoje;
import com.example.mainApp.projekt_z_javy.entity.Uzytkownicy;
import com.example.mainApp.sql.JavaPostgreSQL_register;
import jakarta.persistence.*;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AddingController implements Initializable {

    //private ObservableList<Integer> hourList = FXCollections.observableArrayList(8,10,12,14,16,18);
    private ObservableList<String> hourList = FXCollections.observableArrayList();
    private ObservableList<String> roomList = FXCollections.observableArrayList();

    LocalDate myDate = null;
    String myUserName;

    @FXML
    private Button reservButton;
    @FXML
    private ChoiceBox roomChoiceBox;
    @FXML
    private ChoiceBox hourChoiceBox;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane slider;

    private Stage stage;
    private Scene scene;
    private Parent root;

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    private ObservableList<Integer> reservationList = FXCollections.observableArrayList();

    public void getUserName(String username){
        myUserName = username;
        //System.out.println(myUserName);
    }

    @FXML
    private void exit(ActionEvent e) {
        System.exit(0);
    }

    public void fillRoomComboBox(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            List<Pokoje> listaPokoi;
            entityTransaction.begin();

            //Trzeba pozmieniac na stringi
            TypedQuery<Pokoje> listOfRooms = entityManager.createQuery("SELECT pok FROM Pokoje pok", Pokoje.class);
            listaPokoi = listOfRooms.getResultList();

            List<String> names = listaPokoi
                    .stream()
                    .map(Pokoje::getNazwa)
                    .collect(Collectors.toList());

            roomList.addAll(names);


            entityTransaction.commit();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void fillHourComboBox(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            List<Godziny> listaGodzin;
            entityTransaction.begin();


            //Trzeba pozmieniac na stringi
            TypedQuery<Godziny> listOfHours = entityManager.createQuery("SELECT god FROM Godziny god", Godziny.class);
            listaGodzin = listOfHours.getResultList();
            //System.out.println(listaGodzin.size());

            for (Godziny godziny : listaGodzin) {
                switch (godziny.getIdH()) {
                    case 1:
                        hourList.add("8-10");
                        break;
                    case 2:
                        hourList.add("10-12");
                        break;
                    case 3:
                        hourList.add("12-14");
                        break;
                    case 4:
                        hourList.add("14-16");
                        break;
                    case 5:
                        hourList.add("16-18");
                        break;
                    case 6:
                        hourList.add("18-20");
                        break;
                }
            }


            /*List<Integer> hours = listaGodzin
                    .stream()
                    .map(Godziny::getGodzinaOd)
                    .collect(Collectors.toList());

            hourList.addAll(hours);

             */


            entityTransaction.commit();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        /*String query = "SELECT godzina_od FROM godziny";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                String h = rs.getString("godzina_od");
                switch(h) {
                    case "8": hourList.add("8-10"); break;
                    case "10": hourList.add("10-12"); break;
                    case "12": hourList.add("12-14"); break;
                    case "14": hourList.add("14-16"); break;
                    case "16": hourList.add("16-18"); break;
                    case "18": hourList.add("18-20"); break;
                }
            }
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_register.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

         */

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillRoomComboBox();
        fillHourComboBox();
        hourChoiceBox.setItems(hourList);
        roomChoiceBox.setItems(roomList);



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
    

    public void userLogout(ActionEvent actionEvent) throws SQLException, IOException {
        //System.out.println("Wylogowuje...");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void addReservation(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        root = loader.load();
        AddingController addingController = loader.getController();
        addingController.getUserName(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyDelete.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyDelete.fxml"));
        root = loader.load();
        DeletingController deletingController = loader.getController();
        deletingController.getUserName1(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyDelete.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void myReservation(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyHistory_v2.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyHistory_v2.fxml"));
        root = loader.load();
        TableViewController tableViewController = loader.getController();
        tableViewController.getUserName3(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyNew.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void otherReservation(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader;
        if(myUserName.equals("admin"))
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        else
            loader = new FXMLLoader(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        root = loader.load();
        OtherController otherController = loader.getController();
        otherController.getUserName2(myUserName);
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("lobbyOther.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getDate(ActionEvent actionEvent) {
        myDate = dataPicker.getValue();
        //System.out.println(myDate);
    }

    //Po nacisnieciu guzika
    @FXML
    public void makeReservation(ActionEvent actionEvent) throws SQLException {
        //Deklaracja zmiennych
        String wholeWord = (String) hourChoiceBox.getValue();
        char firstChar = wholeWord.charAt(0), secoundChar = wholeWord.charAt(1);
        int id_of_hour = 0;

        //Metody i warunki

        if(firstChar=='8'){
            id_of_hour = 8;
        } else if(firstChar=='1') {
            if (secoundChar == '0'){
                id_of_hour = 10;
            } else if (secoundChar == '2') {
                id_of_hour = 12;
            } else if (secoundChar == '4') {
                id_of_hour = 14;
            } else if (secoundChar == '6') {
                id_of_hour = 16;
            } else if (secoundChar == '8') {
                id_of_hour = 18;
            }
        }

        //int id_h = JavaPostgreSQL_adding.getHourId((Integer) hourChoiceBox.getValue());
        int id_h = JavaPostgreHIB_adding.getHourId(id_of_hour);
        int id_r = JavaPostgreHIB_adding.getRoomId((String) roomChoiceBox.getValue());
        int id_u = JavaPostgreHIB_adding.getUserId(myUserName);
        LocalDate localDate = LocalDate.now();

        if(id_h > 0 && id_r > 0 && id_u >0) {
            if(localDate.isAfter(myDate))
            {
                //System.out.println("wybrana data jest przed aktualna data");
                AlertBox.display("Uwaga!", "Wybrana data jest przed aktualną datą!");
            } else if (JavaPostgreHIB_adding.checkDatabase(Date.valueOf(myDate), id_r, id_h)) {
                //System.out.println("Niestety wybrany termin jest zajety");
                AlertBox.display("Uwaga!", "Wybrany termin jest już zajęty!");
            } else {
                JavaPostgreHIB_adding.writeReservToDatabase(Date.valueOf(myDate), id_r, id_h, id_u);
                AlertBox.display("Uwaga!", "Twoja rezerwacja została zapisana!");
            }
        }
    }

}
