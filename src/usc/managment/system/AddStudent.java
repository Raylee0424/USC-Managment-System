package usc.managment.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class AddStudent extends JFrame implements ActionListener {
    
    JTextField tffname, tflname, tfaddress, tfphone, tfemail;
    JLabel labelidno;
    JDateChooser dcdob;
    JComboBox cbcourse;
    JButton submit, cancel;
    
    //Gives out random ID number
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    AddStudent() {
        setSize(900, 700);
        setLocation(350, 50);
        
        setLayout(null);
        
        JLabel heading = new JLabel("New Student Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
        JLabel lblfname = new JLabel("First Name");
        lblfname.setBounds(50, 150, 100, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);
        
        tffname = new JTextField();
        tffname.setBounds(200, 150, 150, 30);
        add(tffname);
        
        JLabel lbllname = new JLabel("Last Name");
        lbllname.setBounds(400, 150, 200, 30);
        lbllname.setFont(new Font("serif", Font.BOLD, 20));
        add(lbllname);
        
        tflname = new JTextField();
        tflname.setBounds(600, 150, 150, 30);
        add(tflname);
        
        JLabel lblidno = new JLabel("Student ID");
        lblidno.setBounds(50, 200, 200, 30);
        lblidno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblidno);
        
        labelidno = new JLabel("180"+first4);
        labelidno.setBounds(200, 200, 200, 30);
        labelidno.setFont(new Font("serif", Font.BOLD, 20));
        add(labelidno);
        
        JLabel lbldob = new JLabel("Date of Birth");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);
        
        dcdob = new JDateChooser();
        dcdob.setBounds(600, 200, 150, 30);
        add(dcdob);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(50, 250, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);
        
        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(400, 250, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);
        
        JLabel lblcourse = new JLabel("Course");
        lblcourse.setBounds(400, 300, 200, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);
        
        String course[] = {"BSCS", "BSIT", "BSIS"};
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(600, 300, 150, 30);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);
        
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
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
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
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        // Buttons Fix
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        new AddStudent();
    }
}
