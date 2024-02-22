import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Main extends JFrame {

    private JTextField idField, usernameField;
    private JPasswordField passwordField, rePasswordField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JCheckBox javaCheckbox, pythonCheckbox, cSharpCheckbox;
    private JComboBox<String> countryComboBox;
    private JButton submitButton, resetButton;

    public Main() {
        initializeComponents();
        addComponentsToFrame();
        setupEventListeners();
    }

    private void initializeComponents() {
        idField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        rePasswordField = new JPasswordField(20);

        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        javaCheckbox = new JCheckBox("Java");
        pythonCheckbox = new JCheckBox("Python");
        cSharpCheckbox = new JCheckBox("C#");

        String[] countries = {"Select Country", "USA", "Canada", "UK", "India"};
        countryComboBox = new JComboBox<>(countries);

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
    }

    private void addComponentsToFrame() {
        setLayout(new GridLayout(10, 2));

        add(new JLabel("ID:"));
        add(idField);

        add(new JLabel("Username:"));
        add(usernameField);

        add(new JLabel("Password:"));
        add(passwordField);

        add(new JLabel("Re-enter Password:"));
        add(rePasswordField);

        add(new JLabel("Gender:"));
        add(maleRadioButton);
        add(new JLabel(""));
        add(femaleRadioButton);

        add(new JLabel("Course:"));
        add(javaCheckbox);
        add(pythonCheckbox);
        add(cSharpCheckbox);

        add(new JLabel("Country:"));
        add(countryComboBox);

        add(submitButton);
        add(resetButton);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupEventListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    insertDataIntoDatabase();
                    displayRecord();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }

    private boolean validateForm() {
        if (idField.getText().isEmpty() || usernameField.getText().isEmpty() ||
                String.valueOf(passwordField.getPassword()).isEmpty() ||
                String.valueOf(rePasswordField.getPassword()).isEmpty() ||
                countryComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!String.valueOf(passwordField.getPassword()).equals(String.valueOf(rePasswordField.getPassword()))) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mysql";
        String user = "root";
        String password = "12345";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found", e);
        }

        return DriverManager.getConnection(url, user, password);
    }
}

    private void insertDataIntoDatabase() {
        // Perform database insertion using PreparedStatement
        try {
            Connection connection = DatabaseConnection.getConnection();

            String query = "INSERT INTO user_registration (id, username, password, gender, course, country) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, idField.getText());
            preparedStatement.setString(2, usernameField.getText());
            preparedStatement.setString(3, String.valueOf(passwordField.getPassword()));
            preparedStatement.setString(4, maleRadioButton.isSelected() ? "Male" : "Female");
            StringBuilder course = new StringBuilder();
            if (javaCheckbox.isSelected()) course.append("Java ");
            if (pythonCheckbox.isSelected()) course.append("Python ");
            if (cSharpCheckbox.isSelected()) course.append("C# ");
            preparedStatement.setString(5, course.toString().trim());
            preparedStatement.setString(6, (String) countryComboBox.getSelectedItem());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            JOptionPane.showMessageDialog(this, "Registration successful.", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error inserting data into the database.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void displayRecord() {
    try {
        Connection connection = DatabaseConnection.getConnection();

        String query = "SELECT * FROM user_registration";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        System.out.println("Registration Information:");
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String gender = resultSet.getString("gender");
            String course = resultSet.getString("course");
            String country = resultSet.getString("country");

            System.out.println("ID: " + id);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Gender: " + gender);
            System.out.println("Course: " + course);
            System.out.println("Country: " + country);
            System.out.println("--------------");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}


    private void resetForm() {
        idField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        rePasswordField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        javaCheckbox.setSelected(false);
        pythonCheckbox.setSelected(false);
        cSharpCheckbox.setSelected(false);
        countryComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new Main();
    }
}


//database
//
//-- Create a new database
//CREATE DATABASE mysql;
//
//-- Switch to the newly created database
//USE mysql;
//
//-- Create the 'user_registration' table
//CREATE TABLE user_registration (
//    id VARCHAR(20),
//    username VARCHAR(50),
//    password VARCHAR(50),
//    gender VARCHAR(10),
//    course VARCHAR(100),
//    country VARCHAR(50)
//);

