package cn.system;
import cn.dao.StudentDao;
import cn.util.Dbutil;
import cn.util.StringUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class StudentDeleteWindow extends JFrame {
    private JTextField studentIDField;
    private JButton deleteStudentButton;
    private JButton deleteAllButton;
    private JButton backButton;
    private Dbutil dbutil=new Dbutil();
    private StudentDao studentDao=new StudentDao();
    public StudentDeleteWindow() {
        setTitle("学生信息删除窗口");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        studentIDField = new JTextField(15);
        panel.add(new JLabel("学号:"));
        panel.add(studentIDField);

        deleteStudentButton = new JButton("删除指定学生");
        deleteAllButton = new JButton("删除全部");
        backButton = new JButton("返回上一层");
        panel.add(deleteStudentButton);
        panel.add(deleteAllButton);
        panel.add(backButton);
        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                deleStu(e1);
            }
        });

        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                deleAllStu(e2);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panel);
        setVisible(true);
    }
    private void deleStu(ActionEvent event){
        String id=studentIDField.getText();
        if (StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"学号不能为空");
        }else {
            Connection con=null;
            try {
                con=dbutil.getcon();
                studentDao.delete(con,id);
                JOptionPane.showMessageDialog(null,"删除成功");
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
    private void deleAllStu(ActionEvent event) {
        try {
            Connection con=null;
            con=new Dbutil().getcon();
            studentDao.deleteAll(con);
            JOptionPane.showMessageDialog(null,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}