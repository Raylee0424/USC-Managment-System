package usc.managment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateTeacher extends JFrame implements ActionListener {
    
    // Encapsulation
    private JTextField tfDepartment, tfAddress, tfPhone, tfEmail, tfSubject;
    private JLabel labelIdNo, labelLname, labelFname, labelDob;
    private JButton submit, cancel;
    private Choice cIdNo;
    
    UpdateTeacher() {
        setSize(900, 650);
        setLocation(350, 50);
        setLayout(null);
        
        JLabel heading = new JLabel("Update Teacher Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        JLabel lblIdSelect = new JLabel("Select ID Number");
        lblIdSelect.setBounds(50, 100, 200, 20);
        lblIdSelect.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblIdSelect);
        
        cIdNo = new Choice();
        cIdNo.setBounds(250, 100, 200, 20);
        add(cIdNo);
        
        // Load IDs from DB
        loadTeacherIds();
        
        // --- UI Setup using Helper Methods ---
        addLabel("First Name", 50, 150);
        labelFname = createDataLabel(200, 150);
        add(labelFname);
        
        addLabel("Last Name", 400, 150);
        labelLname = createDataLabel(600, 150);
        add(labelLname);
        
        addLabel("ID Number", 50, 200);
        labelIdNo = createDataLabel(200, 200);
        add(labelIdNo);
        
        addLabel("Date of Birth", 400, 200);
        labelDob = createDataLabel(600, 200);
        add(labelDob);
        
        addLabel("Address", 50, 250);
        tfAddress = new JTextField();
        tfAddress.setBounds(200, 250, 150, 30);
        add(tfAddress);
        
        addLabel("Phone Number", 400, 250);
        tfPhone = new JTextField();
        tfPhone.setBounds(600, 250, 150, 30);
        add(tfPhone);
        
        addLabel("Email", 50, 300);
        tfEmail = new JTextField();
        tfEmail.setBounds(200, 300, 150, 30);
        add(tfEmail);
        
        addLabel("Department", 400, 300);
        tfDepartment = new JTextField(); // Maps to 'course' col in your DB
        tfDepartment.setBounds(600, 300, 150, 30);
        tfDepartment.setBackground(Color.WHITE);
        add(tfDepartment);
        
        addLabel("Class Subject", 50, 350);
        tfSubject = new JTextField();
        tfSubject.setBounds(200, 350, 150, 30);
        tfSubject.setBackground(Color.WHITE);
        add(tfSubject);
        
        // Populate initial data
        populateTeacherData(cIdNo.getSelectedItem());
        
        // Listener for changes
        cIdNo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                populateTeacherData(cIdNo.getSelectedItem());
            }
        });
        
        // Buttons
        submit = new JButton("Submit");
        submit.setBounds(250, 500, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 500, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    // --- Helper Methods ---
    
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 30);
        label.setFont(new Font("serif", Font.BOLD, 20));
        add(label);
    }

    private JLabel createDataLabel(int x, int y) {
        JLabel l = new JLabel();
        l.setBounds(x, y, 150, 30);
        l.setFont(new Font("Tahoma", Font.PLAIN, 18));
        return l;
    }
    
    private void loadTeacherIds() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");
            while(rs.next()) {
                cIdNo.add(rs.getString("idno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void populateTeacherData(String id) {
        try {
            Conn c = new Conn();
            String query = "select * from teacher where idno='"+id+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                labelFname.setText(rs.getString("fname"));
                labelLname.setText(rs.getString("lname"));
                labelDob.setText(rs.getString("dob"));
                tfAddress.setText(rs.getString("address"));
                tfPhone.setText(rs.getString("phone"));
                tfEmail.setText(rs.getString("email"));
                labelIdNo.setText(rs.getString("idno"));
                tfDepartment.setText(rs.getString("course")); // NOTE: Your DB uses 'course' for dept
                tfSubject.setText(rs.getString("subject"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateData() {
        String idno = labelIdNo.getText();
        String address = tfAddress.getText();
        String phone = tfPhone.getText();
        String email = tfEmail.getText();
        String course = tfDepartment.getText();
        String subject = tfSubject.getText();
        
        try {
            String query = "update teacher set address='"+address+"', phone='"+phone+"', email='"+email+"', course='"+course+"', subject='"+subject+"' where idno='"+idno+"'";
            Conn con = new Conn();
            con.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Teacher Details Updated Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            updateData();
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new UpdateTeacher();
    }
}