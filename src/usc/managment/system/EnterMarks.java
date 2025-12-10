package usc.managment.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class EnterMarks extends JFrame implements ActionListener {
    
    private Choice cIdNo;
    private JComboBox<String> cbSemester;
    private JTextField tfSub1, tfSub2, tfSub3, tfSub4, tfSub5;
    private JTextField tfMarks1, tfMarks2, tfMarks3, tfMarks4, tfMarks5;
    private JButton cancel, submit;
    
    EnterMarks() {
        setSize(1000, 500);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/exam.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 40, 400, 300);
        add(image);
        
        JLabel heading = new JLabel("Enter Marks of Student");
        heading.setBounds(50, 0, 500, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel lblIdSelect = new JLabel("Select ID Number");
        lblIdSelect.setBounds(50, 70, 150, 20);
        add(lblIdSelect);
        
        cIdNo = new Choice();
        cIdNo.setBounds(200, 70, 150, 20);
        add(cIdNo);
        loadStudentIds();
        
        JLabel lblSem = new JLabel("Select Semester");
        lblSem.setBounds(50, 110, 150, 20);
        add(lblSem);
        
        String semester[] = {"1st Semester", "2nd Semester", "3rd Semester", "4th Semester"};
        cbSemester = new JComboBox<>(semester);
        cbSemester.setBounds(200, 110, 150, 20);
        cbSemester.setBackground(Color.WHITE);
        add(cbSemester);
        
        JLabel lblEnterSub = new JLabel("Enter Subject");
        lblEnterSub.setBounds(100, 150, 200, 40);
        add(lblEnterSub);
        
        JLabel lblEnterMarks = new JLabel("Enter Marks");
        lblEnterMarks.setBounds(320, 150, 200, 40);
        add(lblEnterMarks);
        
        // --- Subject and Marks Fields ---
        // (Positioned manually to preserve your layout)
        tfSub1 = createTextField(50, 200);
        tfSub2 = createTextField(50, 230);
        tfSub3 = createTextField(50, 260);
        tfSub4 = createTextField(50, 290);
        tfSub5 = createTextField(50, 320);
        
        tfMarks1 = createTextField(250, 200);
        tfMarks2 = createTextField(250, 230);
        tfMarks3 = createTextField(250, 260);
        tfMarks4 = createTextField(250, 290);
        tfMarks5 = createTextField(250, 320);
        
        // Buttons
        submit = new JButton("Submit");
        submit.setBounds(70, 360, 150, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Back");
        cancel.setBounds(280, 360, 150, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    // OOP Helper: Reduces code duplication for TextFields
    private JTextField createTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 200, 20);
        add(tf);
        return tf;
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
    
    private void saveMarks() {
        try {
            Conn c = new Conn();
            String query1 = "insert into subject values('"+cIdNo.getSelectedItem()+"', '"+cbSemester.getSelectedItem()+"', '"+tfSub1.getText()+"', '"+tfSub2.getText()+"', '"+tfSub3.getText()+"', '"+tfSub4.getText()+"', '"+tfSub5.getText()+"')";
            String query2 = "insert into marks values('"+cIdNo.getSelectedItem()+"', '"+cbSemester.getSelectedItem()+"', '"+tfMarks1.getText()+"', '"+tfMarks2.getText()+"', '"+tfMarks3.getText()+"', '"+tfMarks4.getText()+"', '"+tfMarks5.getText()+"')";
        
            c.s.executeUpdate(query1);
            c.s.executeUpdate(query2);
            
            JOptionPane.showMessageDialog(null, "Marks Inserted Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            saveMarks();
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
        new EnterMarks();
    }
}