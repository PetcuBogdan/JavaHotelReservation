import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaginaHotel extends JFrame {
    JButton btnBack;
    JButton btnRezervation;
    JLabel name;
    JTextArea description;
    JLabel price;
    JLabel imagine;
    String hotelName;
    void PaginaHotelLayout(UserAccount User, int x, DefaultListModel<Hotel> Hotels, long Days, Date In, Date Out, int rooms, DefaultListModel<Reservations> Rez) throws BadLocationException {
        JFrame h = new JFrame("Hotel page");
        h.setBackground(Color.white);
        h.setSize(1200, 800);
        System.out.println(x);
        int i = 0;
        Hotel CurrentHotel = new Hotel(0,"","", 0,0,"", "",0,"");
        for (Integer iterator = 0; iterator < Hotels.size(); iterator++) {
            if(x == iterator){
                CurrentHotel = Hotels.getElementAt(iterator);
            }
        }
        hotelName = CurrentHotel.getName();
        btnBack = new JButton("<");
        btnBack.setBounds(10,10,40,40);
        h.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                h.setVisible(false);
            }
        });

        btnRezervation = new JButton("Book");
        btnRezervation.setBounds(1050,50,80,40);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
                String DateIn = date_format.format(In);
                String DateOut = date_format.format(Out);
                Reservations r1 = new Reservations(1,DateIn,DateOut,rooms,hotelName,User.getId());
                AddReservation(r1);
                Rez.addElement(r1);
                h.setVisible(false);
            }
        });
        h.add(btnRezervation);

        name = new JLabel(CurrentHotel.name);
        name.setBounds(50,50,250,40);
        h.add(name);
        System.out.println(Days);
        price = new JLabel("Total cost: " + String.valueOf(CurrentHotel.getPrice()*Days*rooms));
        price.setBounds(50,100,250, 40);
        h.add(price);

        imagine = new JLabel(new ImageIcon("/Users/petcubogdan/Desktop/P3_UI/UI/imagini/"+CurrentHotel.image));
        imagine.setBounds(200,150, 800, 250);
        h.add(imagine);

        String hdescription = PasrseDescription(CurrentHotel.description);
        description = new JTextArea(5,20);
        description.setText(hdescription);
        description.setBounds(50, 450, 1100, 250);
        description.setEditable(false);
        description.setBackground(h.getBackground());
        h.add(description);

        h.setLayout(null);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String PasrseDescription(String description){
        String[] words = description.split("\\s+");

        // Initialize a new string builder
        StringBuilder sb = new StringBuilder();

        // Initialize a counter to track the number of words added to the current line
        int lineWordCount = 0;

        // Iterate over the words array
        for (String word : words) {
            // If the current line has 25 or more words, add a newline character
            if (lineWordCount >= 24) {
                sb.append("\n");
                lineWordCount = 0;
            }

            // Append the current word to the string builder
            sb.append(word);
            // Add a space after the word, unless it is the last word
            if (!word.equals(words[words.length - 1])) {
                sb.append(" ");
            }

            // Increment the line word count
            lineWordCount++;
        }

        // Return the resulting string
        return sb.toString();
    }

    void AddReservation(Reservations r){
        String jdbcUrl = "jdbc:mysql://localhost:3306/p3proiectdb";
        String username = "root";
        String password = "Petcu123";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            Statement statement = connection.createStatement();

            PreparedStatement ps = connection.prepareStatement("INSERT  INTO  rezervations (checkin, checkout, room, hotel,userid) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,r.getCheckIn());
            ps.setString(2,r.getCheckOut());
            ps.setInt(3,r.getRooms());
            ps.setString(4,r.getHotel());
            ps.setInt(5,r.getUserID());

            int row = ps.executeUpdate();
            long id = 0;
            if (row > 0) {
                // get the ID back
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
