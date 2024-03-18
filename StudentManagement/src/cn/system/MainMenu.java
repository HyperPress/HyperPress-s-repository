package cn.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton addStudentButton;
    private JButton deleteStudentButton;
    private JButton searchStudentButton;
    private JButton updateStudentButton;
    public MainMenu() {
        setTitle("学生信息管理系统");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        addStudentButton = new JButton("添加学生信息");
        deleteStudentButton = new JButton("删除学生信息");
        searchStudentButton = new JButton("查询学生信息");
        updateStudentButton = new JButton("修改学生信息");
        panel.add(addStudentButton);
        panel.add(deleteStudentButton);
        panel.add(searchStudentButton);
        panel.add(updateStudentButton);
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                Saddperfomed(e1);
            }
        });

        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                Sdeleteperfomer(e2);
            }
        });

        searchStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3) {
                queryperform(e3);
            }
        });
        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e4) {
                Supdateperfomed(e4);
            }
        });
        add(panel);
        setVisible(true);
    }

    private void Sdeleteperfomer(ActionEvent e) {
        new StudentDeleteWindow();
    }
    private void queryperform(ActionEvent e){new StudentQueryWindow();}
    private void Saddperfomed(ActionEvent e) {
        new StudentInfoWindow();
    }
    private void Supdateperfomed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"此功能维护中");
    }
}