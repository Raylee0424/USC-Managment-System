package usc.managment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {
    
    Project() {
        setSize(1540, 850);
        
        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);
        
        // Menu Setup
        JMenuBar mb = new JMenuBar();
        
        // New Information
        JMenu newInformation = createMenu("New Information", Color.BLUE, mb);
        createMenuItem("New Faculty Information", newInformation);
        createMenuItem("New Student Information", newInformation);
        
        // Details
        JMenu details = createMenu("View Details", Color.RED, mb);
        createMenuItem("View Faculty Details", details);
        createMenuItem("View Student Details", details);
        
        // Leave
        JMenu leave = createMenu("Apply Leave", Color.BLUE, mb);
        createMenuItem("Faculty Leave", leave);
        createMenuItem("Student Leave", leave);
        
        // Leave Details
        JMenu leaveDetails = createMenu("Leave Details", Color.RED, mb);
        createMenuItem("Faculty Leave Details", leaveDetails);
        createMenuItem("Student Leave Details", leaveDetails);
        
        // Exams
        JMenu exam = createMenu("Examination", Color.BLUE, mb);
        createMenuItem("Examination Results", exam);
        createMenuItem("Enter Marks", exam);
        
        // Update Info
        JMenu updateInfo = createMenu("Update Details", Color.RED, mb);
        createMenuItem("Update Faculty Details", updateInfo);
        createMenuItem("Update Student Details", updateInfo);
               
        // Exit
        JMenu exit = createMenu("Exit", Color.RED, mb);
        createMenuItem("Exit", exit);
        
        setJMenuBar(mb);
        setVisible(true);
    }
    
    // Helper Method 1: Creates a Menu and adds it to the bar
    private JMenu createMenu(String title, Color color, JMenuBar bar) {
        JMenu menu = new JMenu(title);
        menu.setForeground(color);
        bar.add(menu);
        return menu;
    }
    
    // Helper Method 2: Creates an Item, adds style, listener, and adds to parent menu
    private void createMenuItem(String title, JMenu parent) {
        JMenuItem item = new JMenuItem(title);
        item.setBackground(Color.WHITE);
        item.addActionListener(this);
        parent.add(item);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        
        // Using switch for cleaner logic (Java 7+)
        switch (msg) {
            case "Exit": setVisible(false); break;
            case "Calculator": launchApp("calc.exe"); break;
            case "Notepad": launchApp("notepad.exe"); break;
            case "New Faculty Information": new AddTeacher(); break;
            case "New Student Information": new AddStudent(); break;
            case "View Faculty Details": new TeacherDetails(); break;
            case "View Student Details": new StudentDetails(); break;
            case "Faculty Leave": new TeacherLeave(); break;
            case "Student Leave": new StudentLeave(); break;
            case "Faculty Leave Details": new TeacherLeaveDetails(); break;
            case "Student Leave Details": new StudentLeaveDetails(); break;
            case "Update Faculty Details": new UpdateTeacher(); break;
            case "Update Student Details": new UpdateStudent(); break;
            case "Enter Marks": new EnterMarks(); break;
            case "Examination Results": new ExaminationDetails(); break;
        }
    }
    
    // Helper to handle External Apps exception handling
    private void launchApp(String command) {
        try {
            Runtime.getRuntime().exec(command);
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
        new Project();
    }
}