package cn.system;
import cn.dao.UserDao;
import cn.util.Dbutil;
import cn.util.StringUtil;
import cn.information.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoginFrame extends JFrame {
    private JButton loginButton;
    private JButton exitButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Dbutil dbutil=new Dbutil();
    private UserDao userDao=new UserDao();

    public LoginFrame() {
        setTitle("登录界面");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel usernameLabel = new JLabel("用户名:");
        JLabel passwordLabel = new JLabel("密码:");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        loginButton = new JButton("登录");
        exitButton = new JButton("重置");

        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(exitButton);
        // 添加登录按钮的点击事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                loginActionPerformed(e1);
            }
        });

        // 添加退出按钮的点击事件
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                resetValue(arg0);
            }
        });

        add(panel);
        setVisible(true);
    }

    private void loginActionPerformed(ActionEvent e) {
        String un=this.usernameField.getText();
        String pw= new String(this.passwordField.getPassword());
        if(StringUtil.isEmpty(un)){
            JOptionPane.showMessageDialog(null,"用户名不能为空");
            return;
        }
        if (StringUtil.isEmpty(pw)){
            JOptionPane.showMessageDialog(null,"密码不能为空");
            return;
        }
        User user=new User(un,pw);
        Connection con=null;
        try {
            con=dbutil.getcon();
            User currentUser = userDao.login(con,user);
            if (currentUser!=null){
                JOptionPane.showMessageDialog(null,"登录成功");
                new MainMenu();
                setVisible(false);
            }else {
                JOptionPane.showMessageDialog(null,"登录失败");
            }
        }catch (Exception e2){
            e2.printStackTrace();
        }finally {
            try {
                dbutil.closecon(con);
            }catch (Exception er){
                er.printStackTrace();
            }
        }
    }
    private void resetValue(ActionEvent e) {
        this.usernameField.setText("");
        this.passwordField.setText("");
    }
}