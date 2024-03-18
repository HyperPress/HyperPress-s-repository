package cn.system;
import cn.dao.StudentDao;
import cn.information.Student;
import cn.util.Dbutil;
import cn.util.StringUtil;
import cn.information.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class StudentInfoWindow extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField collegeField;
    private JButton addButton;
    private JButton backButton;
    private Dbutil dbutil=new Dbutil();
    private StudentDao studentDao=new StudentDao();
    public StudentInfoWindow() {
        setTitle("学生信息输入窗口");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel idLabel = new JLabel("学号:");
        JLabel nameLabel = new JLabel("姓名:");
        JLabel ageLabel = new JLabel("年龄:");
        JLabel addressLabel = new JLabel("籍贯:");
        JLabel collegeLabel = new JLabel("学院:");

        idField = new JTextField(10);
        nameField = new JTextField(10);
        ageField = new JTextField(10);
        addressField = new JTextField(10);
        collegeField = new JTextField(10);

        addButton = new JButton("添加");
        backButton = new JButton("返回上一层");
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(collegeLabel);
        panel.add(collegeField);
        panel.add(addButton);
        panel.add(backButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                try {
                    Addperform(e1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                Backperform(e2);
            }
        });

        add(panel);
        setVisible(true);
    }

    private void Addperform(ActionEvent e){
        String id = idField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String hometown = addressField.getText();
        String college = collegeField.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"学号不能为空");
        }else if (StringUtil.isEmpty(name)){
            JOptionPane.showMessageDialog(null,"姓名不能为空");
        } else {
            Student student=new Student(id,name,age,hometown,college);
            Connection con=null;
            try {
                con =dbutil.getcon();
                studentDao.add(con,student);
                JOptionPane.showMessageDialog(null, "添加成功");
            }catch (Exception exception){
                exception.printStackTrace();
            }finally {
                try {
                    dbutil.closecon(con);
                }catch (Exception er){
                    er.printStackTrace();
                }

            }
        }
    }
    private void Backperform(ActionEvent e){
        dispose();
    }
}