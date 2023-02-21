import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Register extends JFrame {
    JTextField username;
    JPasswordField password;
    JPasswordField verPassword;
    JTextField name;
    JTextField phoneNumber;
    JTextField email;
    JLabel passwordLabel;
    JLabel verPasswordLabel;
    JLabel usernameLabel;
    JLabel nameLabel;
    JLabel phoneNumberLabel;
    JLabel emailLabel;
    JButton btnLogin;
    JButton btnRegister;

    void RegisterLayout(List<UserAccount> Users, List<Hotel> Hotels) {
        JFrame r = new JFrame();
        r.setBackground(Color.white);
        r.setTitle("Register");
        r.setSize(1200,800);
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(250,200,150,35);
        r.add(usernameLabel);
        username = new JTextField();
        username.setBounds(400,200,150,35);
        r.add(username);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(250,300,150,35);
        r.add(passwordLabel);
        password = new JPasswordField();
        password.setBounds(400,300,150,35);
        r.add(password);
        verPasswordLabel = new JLabel("Confirm password:");
        verPasswordLabel.setBounds(250,400,150,35);
        r.add(verPasswordLabel);
        verPassword  = new JPasswordField();
        verPassword.setBounds(400, 400, 150,35);
        r.add(verPassword);
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(650,200,150,35);
        r.add(nameLabel);
        name = new JTextField();
        name.setBounds(800, 200,150,35);
        r.add(name);
        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(650,300,150,35);
        r.add(emailLabel);
        email = new JTextField();
        email.setBounds(800,300,150,35);
        r.add(email);
        phoneNumberLabel = new JLabel("Phone number:");
        phoneNumberLabel.setBounds(650, 400,150,35);
        r.add(phoneNumberLabel);
        phoneNumber = new JTextField();
        phoneNumber.setBounds(800,400,150,35);
        r.add(phoneNumber);
        btnRegister = new JButton("Register");
        btnRegister.setBounds(325,500,150,35);
        btnRegister.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = 1;
                        String Username = username.getText();
                        String Password = password.getText();
                        String VerPassword = verPassword.getText();
                        String Name = name.getText();
                        String Email = email.getText();
                        String Phone = phoneNumber.getText();
                        List <Reservations> Rez = new ArrayList<>();
                        int ver = 0;
                        if(Password.equals(VerPassword) && Username != null && Password != null && Name != null && Email != null && Phone != null) {
                            UserAccount NewUser = new UserAccount(id,Username, Name, Password, Email, Phone);
                            NewUser.saveUser();
                            Users.add(NewUser);
                            Meniu meniu = new Meniu();
                            try {
                                meniu.MeniuLayout(NewUser, Hotels, (DefaultListModel<Reservations>) Rez);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            r.setVisible(false);
                            ver = 1;
                        }
                        if (ver == 0){
                            JOptionPane.showMessageDialog(null, "Completati toate campurile");
                        }
                    }
                }
        );
        r.add(btnRegister);
        btnLogin = new JButton("Login");
        btnLogin.setBounds(725,500,150,35);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.LoginLayout(Users,Hotels);
                r.setVisible(false);
            }
        });
        r.add(btnLogin);
        r.setLayout(null);
        r.setVisible(true);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
