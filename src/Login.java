import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Login extends JFrame{
    JTextField username;
    JPasswordField password;
    JButton btnLogin;
    JButton btnRegister;
    JLabel passwordLabel;
    JLabel usernameLabel;

    void LoginLayout(List<UserAccount> Users, List<Hotel> Hotels) {
        DefaultListModel<Reservations> Rez = new DefaultListModel<>();
        JFrame l = new JFrame();
        l.setBackground(Color.white);
        l.setTitle("Login");
        l.setSize(1200,800);
        username = new JTextField();
        username.setBounds(550,150,150,35);
        l.add(username);
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(400, 150, 150, 35);
        l.add(usernameLabel);
        password = new JPasswordField();
        password.setBounds(550,250,150,35);
        l.add(password);
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(400, 250, 150,35);
        l.add(passwordLabel);
        btnLogin = new JButton("Login");
        btnLogin.setBounds(475,350,150,35);
        btnLogin.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Username = username.getText();
                        String Password = password.getText();
                        int ver = 0;
                        for(UserAccount i : Users){
                            if(Objects.equals(i.getUsername(), Username) && Objects.equals(i.getPassword(), Password)) {
                                UserAccount User = i;
                                Meniu meniu = new Meniu();
                                try {
                                    String jdbcUrl = "jdbc:mysql://localhost:3306/p3proiectdb";
                                    String username = "root";
                                    String password = "Petcu123";

                                    try{
                                        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                                        Statement statement = connection.createStatement();

                                        ResultSet resultSet = statement.executeQuery("select * from rezervations");

                                        while (resultSet.next()) {
                                            int Id = resultSet.getInt(1);
                                            String In = resultSet.getString(2);
                                            String Out = resultSet.getString(3);
                                            int Rooms = resultSet.getInt(4);
                                            String Hotel = resultSet.getString(5);
                                            int IdUser = resultSet.getInt(6);
                                            if(IdUser == User.getId()) {
                                                Rez.addElement(new Reservations(Id, In, Out, Rooms, Hotel, IdUser));
                                            }
                                        }
                                        System.out.println(Rez);
                                        connection.close();
                                    }
                                    catch(Exception e2){
                                        System.out.println(e2);
                                    }
                                    meniu.MeniuLayout(User,Hotels,Rez);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                l.setVisible(false);
                                ver = 1;
                            }
                        }
                        if(ver==0)
                            JOptionPane.showMessageDialog(null, "Username sau parola incorecta");
                    }
                }
        );
        l.add(btnLogin);
        btnRegister = new JButton("Register");
        btnRegister.setBounds(475,450,150,35);
        btnRegister.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    Register register = new Register();
                    register.RegisterLayout(Users, Hotels);
                    l.setVisible(false);
                    }
                }
        );
        l.add(btnRegister);
        l.setLayout(null);
        l.setVisible(true);
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

