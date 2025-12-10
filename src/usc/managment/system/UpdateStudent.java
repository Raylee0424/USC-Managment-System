package usc.managment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateStudent extends JFrame implements ActionListener {
    
    private JTextField tfcourse, tfaddress, tfphone, tfemail;
    private JLabel labelidno, labellname, labelfname, labeldob;
    private JButton submit, cancel;
    private Choice cidno;
    
    UpdateStudent() {
        setSize(900, 650);
        setLocation(350, 50);
        setLayout(null);
        
        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        JLabel lblidno = new JLabel("Select ID Number");
        lblidno.setBounds(50, 100, 200, 20);
        lblidno.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblidno);
        
        cidno = new Choice();
        cidno.setBounds(250, 100, 200, 20);
        add(cidno);
        
        // Load initial ID list
        loadStudentIds();
        
        // --- Setup Labels and Fields (Simplified for readability) ---
        addLabel("First Name", 50, 150);
        labelfname = createDataLabel(200, 150);
        add(labelfname);
        
        addLabel("Last Name", 400, 150);
        labellname = createDataLabel(600, 150);
        add(labellname);
        
        addLabel("Id Number", 50, 200);
        labelidno = createDataLabel(200, 200);
        add(labelidno);
        
        addLabel("Date of Birth", 400, 200);
        labeldob = createDataLabel(600, 200);
        add(labeldob);
        
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
        
        addLabel("Course", 400, 300);
        tfcourse = new JTextField();
        tfcourse.setBounds(600, 300, 150, 30);
        tfcourse.setBackground(Color.WHITE);
        add(tfcourse);
        
        // Fetch data for the initially selected ID
        populateStudentData(cidno.getSelectedItem());
        
        // Listener for change events
        cidno.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
               populateStudentData(cidno.getSelectedItem());
            }
        });
        
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
    
    // Helper for Labels
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 30);
        label.setFont(new Font("serif", Font.BOLD, 20));
        add(label);
    }

    // Helper for Data Labels (Non-editable)
    private JLabel createDataLabel(int x, int y) {
        JLabel l = new JLabel();
        l.setBounds(x, y, 150, 30);
        l.setFont(new Font("Tahoma", Font.PLAIN, 18));
        return l;
    }

    // Logic to populate the drop down list
    private void loadStudentIds() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()) {
                cidno.add(rs.getString("idno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Logic to fetch student specific data
    private void populateStudentData(String id) {
        try {
            Conn c = new Conn();
            String query = "select * from student where idno='"+id+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                labelfname.setText(rs.getString("fname"));
                labellname.setText(rs.getString("lname"));
                labeldob.setText(rs.getString("dob"));
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                labelidno.setText(rs.getString("idno"));
                tfcourse.setText(rs.getString("course"));
            }
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
    
    private void updateData() {
        String idno = labelidno.getText();
        String address = tfaddress.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String course = tfcourse.getText();
        
        try {
            String query = "update student set address='"+address+"', phone='"+phone+"', email='"+email+"', course='"+course+"' where idno='"+idno+"'";
            Conn con = new Conn();
            con.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Student Details Updated Successfully");
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
        new UpdateStudent();
    }
}