package cn.system;
import cn.dao.StudentDao;
import cn.information.Student;
import cn.util.Dbutil;
import cn.util.StringUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class StudentQueryWindow extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField locationField;
    private JTextField departmentField;
    private JScrollPane scrollPane;
    private JButton confirmButton;
    private JButton resetButton;
    private JButton backButton;
    private JTable table;
    private JFrame jFrame;
    private Dbutil dbutil=new Dbutil();
    private StudentDao studentDao=new StudentDao();
    private Connection con=null;
    public StudentQueryWindow() {
        jFrame=new JFrame("学生信息查询窗口");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        locationField = new JTextField();
        departmentField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("学号:"));
        panel.add(idField);
        panel.add(new JLabel("姓名:"));
        panel.add(nameField);
        panel.add(new JLabel("年龄:"));
        panel.add(ageField);
        panel.add(new JLabel("籍贯:"));
        panel.add(locationField);
        panel.add(new JLabel("学院:"));
        panel.add(departmentField);

        jFrame.add(panel,BorderLayout.NORTH);
        String columns[] ={"学号","姓名","年龄","籍贯","学院"};
        DefaultTableModel model=new DefaultTableModel(columns,0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        jFrame.add(scrollPane, BorderLayout.CENTER);

        jFrame.setTitle("学生信息查询窗口");
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        jFrame.setVisible(true);
        JPanel bottonJpanel = new JPanel();
        confirmButton = new JButton("确认");
        resetButton = new JButton("重置");
        backButton = new JButton("返回上一层");
        bottonJpanel.add(confirmButton);
        bottonJpanel.add(resetButton);
        bottonJpanel.add(new JLabel());
        bottonJpanel.add(backButton);
        jFrame.add(bottonJpanel,BorderLayout.SOUTH);
        filltable(new Student());
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                action1(e1);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                action2(e2);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
    }
    //确认按钮的事件
    private void action1(ActionEvent e){
        String id = idField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String location = locationField.getText();
        String department = departmentField.getText();
        if (StringUtil.isnotEmpty(id) || StringUtil.isnotEmpty(name) || StringUtil.isnotEmpty(age) || StringUtil.isnotEmpty(location) || StringUtil.isnotEmpty(department)) {
            int sage=0;
            Student student = new Student(id, name,sage, location, department);
            filltable(student);
        }else {
            JOptionPane.showMessageDialog(null,"查询信息不能全为空");
        }
    }
    //重置按钮的事件
    private void action2(ActionEvent e){
        this.idField.setText("");
        this.nameField.setText("");
        this.ageField.setText("");
        this.locationField.setText("");
        this.departmentField.setText("");
    }
    private void filltable(Student student){
        DefaultTableModel dtm =(DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        Connection con=null;
        try {
            con=dbutil.getcon();
            ResultSet resultSet = studentDao.query(con,student);
            while (resultSet.next()){
                Vector v=new Vector();
                v.add(resultSet.getString("id"));
                v.add(resultSet.getString("name"));
                v.add(resultSet.getString("age"));
                v.add(resultSet.getString("address"));
                v.add(resultSet.getString("academy"));
                dtm.addRow(v);
            }
        }catch (Exception er){
            er.printStackTrace();
        }finally {
            try {
                dbutil.closecon(con);
            }catch (Exception err){
                err.printStackTrace();
            }

        }
    }
}