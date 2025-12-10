package usc.managment.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class AddTeacher extends JFrame implements ActionListener {
    
    // Encapsulation: Private fields
    private JTextField tffname, tflname, tfaddress, tfphone, tfemail;
    private JLabel labelidno;
    private JDateChooser dcdob;
    private JComboBox<String> cbdepartment, cbsubject;
    private JButton submit, cancel;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    AddTeacher() {
        setupWindow();
        initializeComponents();
        setVisible(true);
    }

    private void setupWindow() {
        setSize(900, 700);
        setLocation(350, 50);
        setLayout(null);
    }
    
    private void initializeComponents() {
        JLabel heading = new JLabel("New Teacher Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
        // --- Input Fields (Using helper method addLabel for cleaner code) ---
        
        addLabel("First Name", 50, 150);
        tffname = new JTextField();
        tffname.setBounds(200, 150, 150, 30);
        add(tffname);
        
        addLabel("Last Name", 400, 150);
        tflname = new JTextField();
        tflname.setBounds(600, 150, 150, 30);
        add(tflname);
        
        addLabel("Teacher ID", 50, 200);
        labelidno = new JLabel("180"+first4);
        labelidno.setBounds(200, 200, 200, 30);
        labelidno.setFont(new Font("serif", Font.BOLD, 20));
        add(labelidno);
        
        addLabel("Date of Birth", 400, 200);
        dcdob = new JDateChooser();
        dcdob.setBounds(600, 200, 150, 30);
        add(dcdob);
        
        addLabel("Address", 50, 250);
        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);
        
        addLabel("Phone Number", 400, 250);
        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);
        
        addLabel("Email", 50, 300);
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);
        
        addLabel("Department", 400, 300);
        String department[] = {"DCISM", "SOE", "SAS"};
        cbdepartment = new JComboBox<>(department);
        cbdepartment.setBounds(600, 300, 150, 30);
        cbdepartment.setBackground(Color.WHITE);
        add(cbdepartment);
        
        addLabel("Class Subject", 50, 350);
        String subject[] = {"OOP", "DSA", "PROG2"};
        cbsubject = new JComboBox<>(subject);
        cbsubject.setBounds(200, 350, 150, 30);
        cbsubject.setBackground(Color.WHITE);
        add(cbsubject);
        
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
    
    // Helper to reduce repetition
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 30);
        label.setFont(new Font("serif", Font.BOLD, 20));
        add(label);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            submitData();
        } else {
            setVisible(false);
        }
    }
    
    // Logic extraction (SRP)
    private void submitData() {
        String fname = tffname.getText();
        String lname = tflname.getText();
        String idno = labelidno.getText();
        String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
        String address = tfaddress.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String department = (String) cbdepartment.getSelectedItem();
        String subject = (String) cbsubject.getSelectedItem();
        
        try {
            String query = "insert into teacher values('"+fname+"', '"+lname+"', '"+idno+"', '"+dob+"', '"+address+"', '"+phone+"', '"+email+"', '"+department+"', '"+subject+"')";
            Conn con = new Conn();
            con.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Teacher Details Inserted Successfully");
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
        new AddTeacher();
    }
}