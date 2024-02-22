package unit4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Registration extends JFrame {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField t1, t2;
    JPasswordField p1, p2;
    JRadioButton r1, r2;
    JCheckBox c1, c2, c3;
    JComboBox<String> countryComboBox;
    JButton b1, b2;

    public void setRegistration() {
        l1 = new JLabel("Id");
        l2 = new JLabel("Username");
        l3 = new JLabel("Password");
        l4 = new JLabel("Repassword");
        l5 = new JLabel("Gender");
        l6 = new JLabel("Course");
        l7 = new JLabel("Country");
        l8 = new JLabel("Result");
        t1 = new JTextField(20);
        t2 = new JTextField(20);
        p1 = new JPasswordField(20);
        p2 = new JPasswordField(20);
        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        c1 = new JCheckBox("Java");
        c2 = new JCheckBox("C");
        c3 = new JCheckBox("Rust");
        countryComboBox = new JComboBox<>(new String[]{"USA", "Canada", "UK", "Australia", "India"});
        b1 = new JButton("Submit");
        b2 = new JButton("Reset");

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(p1);
        add(l4);
        add(p2);
        add(l5);
        add(r1);
        add(r2);
        add(l6);
        add(c1);
        add(c2);
        add(c3);
        add(l7);
        add(countryComboBox);
        add(b1);
        add(b2);
        add(l8);

        setVisible(true);
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Action listener for Submit button
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t1.getText();
                String uname = t2.getText();
                String pass = new String(p1.getPassword());
                String repass = new String(p2.getPassword());
                String gender = (r1.isSelected()) ? "Male" : "Female";
                String course = "";
                if (c1.isSelected()) {
                    course += "Java,";
                }
                if (c2.isSelected()) {
                    course += "C,";
                }
                if (c3.isSelected()) {
                    course += "Rust,";
                }
                course = course.isEmpty() ? "" : course.substring(0, course.length() - 1); // Remove the last comma

                String country = (String) countryComboBox.getSelectedItem();

                if (id.isEmpty() || uname.isEmpty() || pass.isEmpty() || repass.isEmpty() || (!pass.equals(repass))) {
                    l8.setText("Please fill all fields and ensure passwords match.");
                    return; // Stop execution if validation fails
                }

                // Connect to the database
                String url = "jdbc:mysql://localhost:3306/mysql";
                String user = "root";
                String password = "12345";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    // Insert data into database using prepared statement
                    String insQuery = "INSERT INTO tbl_reg (id, uname, pass, repass, gender, course, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(insQuery)) {
                        statement.setString(1, id);
                        statement.setString(2, uname);
                        statement.setString(3, pass);
                        statement.setString(4, repass);
                        statement.setString(5, gender);
                        statement.setString(6, course);
                        statement.setString(7, country);
                        int res = statement.executeUpdate();
                        if (res >= 1) {
                            l8.setText("Record has been inserted successfully. ID: " + id + ", Username: " + uname + ", Gender: " + gender + ", Course: " + course + ", Country: " + country);
                        }
                    }
                } catch (SQLException se) {
                    System.out.println("SQL ERROR: " + se);
                    l8.setText("Error inserting data into database.");
                }
            }
        });

        // Action listener for Reset button
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                p1.setText("");
                p2.setText("");
                r1.setSelected(false);
                r2.setSelected(false);
                c1.setSelected(false);
                c2.setSelected(false);
                c3.setSelected(false);
                countryComboBox.setSelectedIndex(0);
                l8.setText("");
            }
        });
    }
}

public class Registration1 {
    public static void main(String[] args) {
        Registration reg = new Registration();
        reg.setRegistration();
    }
}


//USE your_database_name; 
//
//CREATE TABLE tbl_reg (
//    id VARCHAR(10) PRIMARY KEY,
//    uname VARCHAR(50),
//    pass VARCHAR(50),
//    repass VARCHAR(50),
//    gender VARCHAR(10),
//    course VARCHAR(100),
//    country VARCHAR(50)
//);

//CREATE DATABASE prime;
