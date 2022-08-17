package com.example.projekt_z_javy;

import javafx.scene.control.DatePicker;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSQL_adding {

    public static final String url = "jdbc:postgresql://ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/de710thmop4rit";
    public static final String user = "dpbwovovhjsruv";
    public static final String password = "20482d0224e13b90ddcba4fd4e828746739cadef005e44a9bbad4acb6a7b64cf";

    static Integer checkNumberOfUser;
    static Integer checkNumberOfRoom;
    static Integer checkNumberOfHour;

    //Funkcja odpowiedzialna z azwrócenie id uzytkownika z bazy danych
    public static int getUserId(String uN) {
        String userName = uN;

        String query = "SELECT id_u FROM uzytkownicy WHERE login = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, userName);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()){
                checkNumberOfUser = resultSet.getInt(1);
                System.out.println(checkNumberOfUser);
            }

            System.out.println("Numer uzytkownika to: " + checkNumberOfUser);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
        return checkNumberOfUser;
    }

    public static int getRoomId(String rN) {
        String roomName = rN;
        String query = "SELECT id_p FROM pokoje WHERE nazwa = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, roomName);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()){
                checkNumberOfRoom = resultSet.getInt(1);
                System.out.println(checkNumberOfRoom);
            }

            System.out.println("Numer id pokoju to: " + checkNumberOfRoom);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
        return checkNumberOfRoom;
    }

    public static int getHourId(Integer pH) {
        Integer pickedHour = pH;
        String query = "SELECT id_h FROM godziny WHERE godzina_od = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, pickedHour);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()){
                checkNumberOfHour = resultSet.getInt(1);
                System.out.println(checkNumberOfHour);
            }

            System.out.println("Numer id pokoju to: " + checkNumberOfHour);
            pst.close();
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

        return checkNumberOfHour;
    }

    public static void writeReservToDatabase(Date pD, Integer room, Integer hour, Integer userNum) throws SQLException {

        Date pickedDate = pD;
        Integer roomName = room;
        Integer pickedHour = hour;
        Integer userNumber= userNum;

                        /* Tutaj trzeba zmienić w tym  wzorze */
        String query = "INSERT INTO rezerwacje(id_p, id_u, id_h,data) VALUES(?, ?, ?,?)";


        //ID_p - pokoje -> po naziwe sprawdzam id i odysłam tutaj
        //ID_u - pobieram nazwe tak jak wczesniej i sprawdzam w bazie danych
        //ID_h - na ten moment wystarczy sprawdzić godzine od jakie ma id
        //date - mozna wpisac odrau

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, roomName);
            pst.setInt(2, userNumber);
            pst.setInt(3, pickedHour);
            pst.setDate(4, pickedDate);

            pst.executeUpdate(); //zwraca boolena (true/false)
            System.out.println("Sucessfully created!");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }
    }

    /**
     *Metoda odpowiedzialna za sprawdzenie powtórzenia loginu lub maila, rejestrującego sie uzytkownika
     *
     */

    public static boolean checkDatabase(String userName, String userEmail) throws SQLException {

        boolean loginisko = false;
        String name = userName;
        String email2 = userEmail;


        String query = "SELECT login FROM uzytkownicy WHERE (login = ? OR email = ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            email2.toLowerCase();

            pst.setString(1, name);
            pst.setString(2, email2);

            ResultSet resultSet = pst.executeQuery(); //executeQuery zwaraca nam wartosc podana przez selecta

            while (resultSet.next()){
                String checkUser = resultSet.getString(1);
                System.out.println(checkUser);

                if (!checkUser.isEmpty()) {
                    loginisko = true;
                    System.out.println("Jest");
                    break;
                }
            //pst.executeUpdate();
        }

            System.out.println("okkk");
            resultSet.close();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgreSQL_adding.class.getName());
            lgr.log(Level.SEVERE,ex.getMessage(),ex);
        }

        return loginisko;
    }
}
