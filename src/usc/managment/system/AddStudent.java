package usc.managment.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class AddStudent extends JFrame implements ActionListener {
    
    
    private JTextField tffname, tflname, tfaddress, tfphone, tfemail;
    private JLabel labelidno;
    private JDateChooser dcdob;
    private JComboBox<String> cbcourse;
    private JButton submit, cancel;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    AddStudent() {
        setupWindow();
        initializeComponents();
        
        setVisible(true);
    }
    
    // Helper method for Window setup
    private void setupWindow() {
        setSize(900, 700);
        setLocation(350, 50);
        setLayout(null);
    }

    // Helper method for adding components
    private void initializeComponents() {
        JLabel heading = new JLabel("New Student Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
        // --- Input Fields ---
        // Note: I grouped these for readability, but kept your exact positioning
        
        // First Name
        addLabel("First Name", 50, 150);
        tffname = new JTextField();
        tffname.setBounds(200, 150, 150, 30);
        add(tffname);
        
        // Last Name
        addLabel("Last Name", 400, 150);
        tflname = new JTextField();
        tflname.setBounds(600, 150, 150, 30);
        add(tflname);
        
        // Student ID
        addLabel("Student ID", 50, 200);
        labelidno = new JLabel("180" + first4);
        labelidno.setBounds(200, 200, 200, 30);
        labelidno.setFont(new Font("serif", Font.BOLD, 20));
        add(labelidno);
        
        // Date of Birth
        addLabel("Date of Birth", 400, 200);
        dcdob = new JDateChooser();
        dcdob.setBounds(600, 200, 150, 30);
        add(dcdob);
        
        // Address
        addLabel("Address", 50, 250);
        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);
        
        // Phone
        addLabel("Phone Number", 400, 250);
        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);
        
        // Email
        addLabel("Email", 50, 300);
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);
        
        // Course
        addLabel("Course", 400, 300);
        String course[] = {"BSCS", "BSIT", "BSIS"};
        cbcourse = new JComboBox<>(course);
        cbcourse.setBounds(600, 300, 150, 30);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);
        
        // --- Buttons ---
        submit = new JButton("Submit");
        submit.setBounds(250, 550, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
    }
    
    // A helper method to create labels since they are all similar
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 30);
        label.setFont(new Font("serif", Font.BOLD, 20));
        add(label);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            saveStudent();
        } else {
            setVisible(false);
        }
    }

    // Separating the logic of "Saving" from the event handling
    private void saveStudent() {
        String fname = tffname.getText();
        String lname = tflname.getText();
        String idno = labelidno.getText();
        String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
        String address = tfaddress.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String course = (String) cbcourse.getSelectedItem();
        
        try {
            String query = "insert into student values('"+fname+"', '"+lname+"', '"+idno+"', '"+dob+"', '"+address+"', '"+phone+"', '"+email+"', '"+course+"')";
            
            Conn con = new Conn();
            con.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Student Details Inserted Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new AddStudent();
    }
}