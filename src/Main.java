import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Hotel> Hotels = new ArrayList<>();
        List<Reservations> Rez = new ArrayList<>();
        List<UserAccount> Users = new LinkedList<>();
        String jdbcUrl = "jdbc:mysql://localhost:3306/p3proiectdb";
        String username = "root";
        String password = "Petcu123";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from hotels");

            while (resultSet.next()) {
                int Id = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                String Address = resultSet.getString(3);
                int Star = resultSet.getInt(4);
                float Price = resultSet.getFloat(5);
                String Image = resultSet.getString(6);
                String Facility = resultSet.getString(7);
                int Room = resultSet.getInt(8);
                String Description = resultSet.getString(9);
                Hotels.add(new Hotel(Id, Name, Address, Star, Price, Image, Facility, Room, Description));
            }

            ResultSet usersSet = statement.executeQuery("select * from users");

            while (usersSet.next()) {
                int Id = usersSet.getInt(1);
                String Username = usersSet.getString(2);
                String Password = usersSet.getString(3);
                String Name = usersSet.getString(4);
                String Email = usersSet.getString(5);
                String Phone = usersSet.getString(6);
                Users.add(new UserAccount(Id,Username, Name, Password, Email, Phone));
            }
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

        //System.out.println(Hotels);
        Login login = new Login();
        login.LoginLayout(Users,Hotels);
    }
}