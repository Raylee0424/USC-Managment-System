package usc.managment.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class StudentLeave extends JFrame implements ActionListener {
    
    private Choice cIdNo, cTime;
    private JDateChooser dcDate;
    private JButton submit, cancel;
    
    StudentLeave() {
        setSize(500, 550);
        setLocation(550, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Apply Leave (Student)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        // Search ID
        JLabel lblId = new JLabel("Search by ID Number");
        lblId.setBounds(60, 100, 200, 20);
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblId);
        
        cIdNo = new Choice();
        cIdNo.setBounds(60, 130, 200, 20);
        add(cIdNo);
        loadStudentIds(); // OOP: Helper method
        
        // Date
        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(60, 180, 200, 20);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblDate);
        
        dcDate = new JDateChooser();
        dcDate.setBounds(60, 210, 200, 25);
        add(dcDate);
        
        // Time
        JLabel lblTime = new JLabel("Time Duration");
        lblTime.setBounds(60, 260, 200, 20);
        lblTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblTime);
        
        cTime = new Choice();
        cTime.setBounds(60, 290, 200, 20);
        cTime.add("Full Day");
        cTime.add("Half Day");
        add(cTime);
        
        // Buttons
        submit = new JButton("Submit");
        submit.setBounds(60, 350, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(200, 350, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    private void loadStudentIds() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()) {
                cIdNo.add(rs.getString("idno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyLeave() {
        String idno = cIdNo.getSelectedItem();
        String date = ((JTextField) dcDate.getDateEditor().getUiComponent()).getText();
        String duration = cTime.getSelectedItem();
        
        String query = "insert into studentleave values('"+idno+"', '"+date+"', '"+duration+"')";
        
        try {
            Conn c = new Conn();
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Leave Confirmed");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            applyLeave();
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
        new StudentLeave();
    }
}