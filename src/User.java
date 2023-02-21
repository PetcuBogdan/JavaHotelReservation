import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.List;

public class User extends JFrame {
    JFrame u;
    JButton btnBack;
    JLabel username;
    JLabel phone;
    JLabel email;
    JLabel changePass;
    JPasswordField newPass;
    JPasswordField newPass2;
    JButton btnSavePass;
    DefaultTableModel tabelRez;

    void UserLayout(UserAccount User, List<Hotel> Hotels, DefaultListModel<Reservations> Rez){
        u = new JFrame("Meniu");
        u.setSize(1200, 800);

        btnBack = new JButton("<");
        btnBack.setBounds(10,10,40,40);
        u.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meniu meniu = new Meniu();
                try {
                    meniu.MeniuLayout(User, Hotels, Rez);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                u.setVisible(false);
            }
        });

        username = new JLabel(" username:  " + User.getUsername());
        username.setBounds(50, 100, 150, 35);
        u.add(username);

        email = new JLabel(" email:  " + User.getEmail());
        email.setBounds(50, 150, 150, 35);
        u.add(email);

        phone = new JLabel(" phone:  " + User.getPhone());
        phone.setBounds(50, 200, 150, 35);
        u.add(phone);

        changePass = new JLabel("Change password");
        changePass.setBounds(50,250,150,35);
        u.add(changePass);

        newPass = new JPasswordField();
        newPass.setBounds(50,300,150,35);
        u.add(newPass);

        newPass2 = new JPasswordField();
        newPass2.setBounds(50,350,150,35);
        u.add(newPass2);

        btnSavePass = new JButton("Save new password");
        btnSavePass.setBounds(50,400,150,35);
        u.add(btnSavePass);
        btnSavePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newPass.getText().equals(newPass2.getText())){
                    User.setPassword(newPass.getText());
                    newPass.setText("");
                    newPass2.setText("");
                    //update la parola in DB
                    }
                else {
                    JOptionPane.showMessageDialog(null, "Parole diferite");
                    newPass2.setText("");
                }
            }
        });

        tabelRez = new DefaultTableModel();
        tabelRez.addColumn("Hotel");
        tabelRez.addColumn("Check-In");
        tabelRez.addColumn("Check-Out");
        tabelRez.addColumn("Rooms");
        tabelRez.addColumn("Remove");

        Table(Rez);

        u.setLayout(null);
        u.setVisible(true);
        u.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void Table(DefaultListModel<Reservations> Rez) {
        while(tabelRez.getRowCount() > 0)
        {
            tabelRez.removeRow(0);
        }
        tabelRez.addRow(new Object[]{"Hotel","Check-In","Check-Out","Rooms","Remove"});
        for (Integer iterator = 0; iterator < Rez.size(); iterator++) {
            tabelRez.addRow(new Object[]{Rez.getElementAt(iterator).getHotel(), Rez.getElementAt(iterator).getCheckIn(), Rez.getElementAt(iterator).getCheckOut(), Rez.getElementAt(iterator).getRooms(), "remove"});
        }
        JTable table = new JTable(tabelRez);
        table.setBounds(300, 50, 700, 700);
        // table.setFillsViewportHeight(true);
        table.setEnabled(false);
        table.setShowHorizontalLines(true);
        table.setRowHeight(50);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (col == 4 && row > 0) {
                    DeleteRez(Rez.getElementAt(row-1));
                    Rez.remove(row-1);
                    System.out.println(row);
                    Table(Rez);
                }
            }
        });
        u.add(table);
    }
    void DeleteRez(Reservations r){
        String jdbcUrl = "jdbc:mysql://localhost:3306/p3proiectdb";
        String username = "root";
        String password = "Petcu123";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            Statement statement = connection.createStatement();

            PreparedStatement ps = connection.prepareStatement("DELETE FROM rezervations WHERE idrezervations = ?");
            ps.setInt(1,r.getIdRez());
            ps.executeUpdate();

            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
