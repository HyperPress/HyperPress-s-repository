package cn.dao;

import cn.information.Student;
import cn.util.StringUtil;

import java.sql.*;

public class StudentDao {
    public int add(Connection con, Student student)throws Exception{
        String sql="insert into student values(?,?,?,?,?)";
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setString(1,student.getId());
        preparedStatement.setString(2,student.getName());
        preparedStatement.setInt(3,student.getAge());
        preparedStatement.setString(4,student.getAddress());
        preparedStatement.setString(5,student.getAcademy());
        return preparedStatement.executeUpdate();
    }
    public int deleteAll(Connection con) throws Exception {
        String sql="delete from student";
        Statement stmt= con.createStatement();
        return stmt.executeUpdate(sql);
    }
    public int delete(Connection con,String id)throws Exception{
        String sql="delete from student where id=?";
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setString(1,id);
        return stmt.executeUpdate();
    }
    public int updatastu(Connection con,Student student){
        String sql="update from student where id =? and name =?";
        return 0;
    }
    public ResultSet query(Connection con,Student student)throws Exception{
        StringBuffer sql=new StringBuffer("select * from student");
        if (StringUtil.isnotEmpty(student.getId())){
            sql.append(" and id like '%"+student.getId()+"%'");
        }
        if (StringUtil.isnotEmpty(student.getName())){
            sql.append(" and name like'%"+student.getName()+"%'");
        }
        if (StringUtil.isnotEmpty(student.getAddress())){
            sql.append(" and name like'%"+student.getAddress()+"%'");
        }
        if (StringUtil.isnotEmpty(student.getAcademy())){
            sql.append(" and academy like'%"+student.getAcademy()+"%'");
        }
        String s=sql.toString();
        PreparedStatement stms=con.prepareStatement(s.replaceFirst("and","where"));
        return stms.executeQuery();
    }
}
