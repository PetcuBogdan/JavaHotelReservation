import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class Meniu extends JFrame {
    JFrame m;
    JLabel searchDestLabel;
    JLabel dateInLabel;
    JLabel dateOutLabel;
    JLabel numberPLabel;
    JLabel numberOfStarsLabel;
    JLabel facilitiesLabel;
    JTextField searchDest;
    JDatePickerImpl dateIn;
    JDatePickerImpl dateOut;
    JButton btnUser;
    JButton btnSearch;
    JTextField numberP;
    JComboBox numberOfStars;
    DefaultListModel<Hotel> CopyHotels;
    DefaultTableModel model;
    SqlDateModel modelDateOut;
    SqlDateModel modelDateIn;

    <Iterator> void MeniuLayout(UserAccount User, List<Hotel> Hotels, DefaultListModel<Reservations> Rez) throws IOException {
        m = new JFrame("Meniu");
        m.setBackground(Color.white);
        m.setSize(1200, 800);
        CopyHotels = new DefaultListModel<>();
        for (Hotel hotel : Hotels) {
            CopyHotels.addElement(hotel);
        }

        btnUser = new JButton(User.getUsername());
        btnUser.setBounds(1050,50,100,35);
        m.add(btnUser);
        btnUser.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        User user = new User();
                        user.UserLayout(User,Hotels, Rez);
                        m.setVisible(false);
                    }
                }
        );

        searchDestLabel = new JLabel(" Search destination");
        searchDestLabel.setBounds(25, 50, 150, 35);
        m.add(searchDestLabel);

        searchDest = new JTextField();
        searchDest.setBounds(25, 80, 150, 35);
        m.add(searchDest);

        dateInLabel = new JLabel(" Check-In Date");
        dateInLabel.setBounds(25, 110, 150, 35);
        m.add(dateInLabel);

        modelDateIn = new SqlDateModel();
        Properties p = new Properties();
        modelDateIn.setSelected(true);
        p.put("text.day", "Day");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl panel = new JDatePanelImpl(modelDateIn,p);
        dateIn = new JDatePickerImpl(panel, new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) throws ParseException {
                return text;
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if(value != null) {
                    Calendar cal = (Calendar) value;
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = format.format(cal.getTime());
                    return strDate;
                }
            return "";
            }
        });
        dateIn.setBounds(25, 140, 150, 35);
        m.add(dateIn);

        dateOutLabel = new JLabel(" Check-out Date");
        dateOutLabel.setBounds(25, 170, 150, 35);
        m.add(dateOutLabel);

        modelDateOut = new SqlDateModel();
        Properties p2 = new Properties();
        modelDateOut.setSelected(true);
        p2.put("text.day", "Day");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        JDatePanelImpl panel2 = new JDatePanelImpl(modelDateOut,p2);
        dateOut = new JDatePickerImpl(panel2, new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) throws ParseException {
                return text;
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if(value != null) {
                    Calendar cal = (Calendar) value;
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = format.format(cal.getTime());
                    return strDate;
                }
                return "";
            }
        });
        dateOut.setBounds(25, 200, 150, 35);
        m.add(dateOut);

        numberPLabel = new JLabel(" Number of people");
        numberPLabel.setBounds(25, 230, 150, 35);
        m.add(numberPLabel);

        numberP = new JTextField();
        numberP.setBounds(25, 260, 150, 35);
        m.add(numberP);

        numberOfStarsLabel = new JLabel(" Number of stars:");
        numberOfStarsLabel.setBounds(25, 290, 150, 35);
        m.add(numberOfStarsLabel);

        String stars[] = {"","2", "3", "4", "5"};
        numberOfStars = new JComboBox(stars);
        numberOfStars.setBounds(25, 320, 150, 35);
        m.add(numberOfStars);

        facilitiesLabel = new JLabel(" Facilities:");
        facilitiesLabel.setBounds(25, 350, 150, 35);
        m.add(facilitiesLabel);

        JCheckBox pool = new JCheckBox("pool", false);
        JCheckBox spa = new JCheckBox("spa", false);
        JCheckBox parking = new JCheckBox("parking", false);
        JCheckBox restaurant = new JCheckBox("restaurant", false);
        JCheckBox roomService = new JCheckBox("room service", false);
        pool.setBounds(25, 380, 150, 35);
        spa.setBounds(25,400, 150,35);
        parking.setBounds(25,420, 150,35);
        restaurant.setBounds(25,440, 150,35);
        roomService.setBounds(25,460, 150,35);
        m.add(pool);
        m.add(spa);
        m.add(parking);
        m.add(restaurant);
        m.add(roomService);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(25, 510, 150,35);
        m.add(btnSearch);
        btnSearch.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CopyHotels = new DefaultListModel<>();
                        for (Hotel hotel : Hotels) {
                            CopyHotels.addElement(hotel);
                        }
                        while(model.getRowCount() > 0)
                        {
                            model.removeRow(0);
                        }
                        String destination = searchDest.getText();
                        Integer stars = numberOfStars.getSelectedIndex()+1;

                        if(pool.isSelected()){
                            String Pool = "pool";
                            for (Integer iterator = 0; iterator < CopyHotels.size(); iterator++) {
                                if (!CopyHotels.getElementAt(iterator).getFacilities().contains(Pool)) {
                                    System.out.println(CopyHotels.getElementAt(iterator).getName());
                                    CopyHotels.remove(iterator);
                                    iterator--;
                                }
                            }
                        }

                        if(spa.isSelected()){
                            String Spa = "spa";
                            for (Integer iterator = 0; iterator < CopyHotels.size(); iterator++) {
                                if (!CopyHotels.getElementAt(iterator).getFacilities().contains(Spa)) {
                                    CopyHotels.remove(iterator);
                                    iterator--;
                                }
                            }
                        }

                        if(parking.isSelected()){
                            String Parking = "parking";
                            for (Integer iterator = 0; iterator < CopyHotels.size(); iterator++) {
                                if (!CopyHotels.getElementAt(iterator).getFacilities().contains(Parking)) {
                                    CopyHotels.remove(iterator);
                                    iterator--;
                                }
                            }
                        }

                        if(restaurant.isSelected()){
                            String Restaurant = "restaurant";
                            for (Integer iterator = 0; iterator < CopyHotels.size(); iterator++) {
                                if (!CopyHotels.getElementAt(iterator).getFacilities().contains(Restaurant)) {
                                    CopyHotels.remove(iterator);
                                    iterator--;
                                }
                            }
                        }

                        if(roomService.isSelected()){
                            String RoomService = "roomservice";
                            for (Integer iterator = 0; iterator < CopyHotels.size(); iterator++) {
                                if (!CopyHotels.getElementAt(iterator).getFacilities().contains(RoomService)) {
                                    CopyHotels.remove(iterator);
                                    iterator--;
                                }
                            }
                        }

                        if(stars >=2 ) {
                            for (Integer iterator = 0; iterator < CopyHotels.size(); iterator++) {
                                if (stars != CopyHotels.getElementAt(iterator).getStar()) {
                                    CopyHotels.remove(iterator);
                                    iterator--;
                                }
                            }
                        }

                        if(!destination.equals("")){
                            for (Integer iterator = 0; iterator < CopyHotels.size(); iterator++) {
                                if (!CopyHotels.getElementAt(iterator).getAdress().contains(destination)) {
                                    CopyHotels.remove(iterator);
                                    iterator--;
                                }
                            }
                            System.out.println(CopyHotels.size());
                       }

                        Table(User, Hotels, Rez);
                    }
                }
        );

        model = new DefaultTableModel();

        model.addColumn("Hotel");
        model.addColumn("Pret");
        model.addColumn("Adresa");

        Table(User, Hotels, Rez);

        m.setLayout(null);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void Table(UserAccount User, List<Hotel> Hotels, DefaultListModel<Reservations> Rez) {
        for (Integer iterator = 0; iterator<CopyHotels.size();iterator++) {
            model.addRow(new Object[] { CopyHotels.getElementAt(iterator).name, "Price per night: " + CopyHotels.getElementAt(iterator).price + "$", CopyHotels.getElementAt(iterator).adress });
        }
        JTable table = new JTable(model);
        table.setBounds(300, 50, 700, 700);
        // table.setFillsViewportHeight(true);
        table.setEnabled(false);
        table.setShowHorizontalLines(true);
        table.setRowHeight(50);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String people = numberP.getText();
                int rooms = 0;
                if(!people.equals("")){
                int room = Integer.parseInt(people);
                rooms = (room+1)/2;
                }

                Date In = modelDateIn.getValue();
                Date Out = modelDateOut.getValue();
                long Days = (Out.getTime() - In.getTime())/(1000*60*60*24);
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 0) {
                    PaginaHotel meniu = new PaginaHotel();
                    try {
                        meniu.PaginaHotelLayout(User, row, CopyHotels, Days,In, Out,rooms, Rez);
                    } catch (BadLocationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        m.add(table);
    }
}