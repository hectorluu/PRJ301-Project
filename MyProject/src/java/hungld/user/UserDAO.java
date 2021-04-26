/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.user;

import hungld.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class UserDAO implements Serializable {
    
    public UserDTO checkLogin(String userID, String password)
            throws SQLException, NamingException, ClassNotFoundException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // 1. Connect DB
            con = DBUtils.getConnection();
            if (con != null) {
                // 2. Create SQL String
                String sql = "SELECT fullName,roleID\n"
                        + "FROM tblUsers\n"
                        + "WHERE userID = ? AND password = ?";
                // 3. Create Statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                // 4. Execute Query
                rs = stm.executeQuery();
                
                // 5. Process result
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, fullName, roleID, "");
                } // end if rs is existed
            } // end if con is opened
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException, NamingException {
        List<UserDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBUtils.getConnection();
            if (con != null){
                String sql = "SELECT userID, fullName, roleID\n"
                        + "FROM tblUsers\n"
                        + "WHERE fullName like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()){
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    list.add(new UserDTO(userID, fullName, roleID, "***"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return list;
    }
    
    public boolean deleteUser(String userID)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBUtils.getConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "DELETE FROM tblUsers\n"
                        + "WHERE userID = ?";
                //3. Create statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                //4. Execute Query
                int rowEffect = stm.executeUpdate();
                //5. Process result
                if (rowEffect > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean updateUser(UserDTO user)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBUtils.getConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "UPDATE tblUsers\n"
                        + "SET fullName = ?,\n"
                        + "roleID = ?\n"
                        + "WHERE userID = ?";
                //3. Create statement and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getFullName());
                stm.setString(2, user.getRoleID());
                stm.setString(3, user.getUserID());
                
                //4. Execute Query
                int rowEffect = stm.executeUpdate();
                //5. Process result
                if (rowEffect > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean checkDuplicate(String userID) throws SQLException, NamingException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm =null;
        ResultSet rs = null;
        try {
            conn= DBUtils.getConnection();
            if(conn!=null){
                String sql="SELECT userID\n"
                        + "FROM tblUsers\n"
                        + "WHERE userID = ?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs=stm.executeQuery();
                if(rs.next()){
                    check=true;
                }
            }
        } finally{
             if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean insertUser(UserDTO user) throws SQLException, NamingException{
        boolean check=false;
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="INSERT INTO tblUsers(userID, fullname, roleID, password)\n"
                        + "VALUES(?,?,?,?)";
                stm= conn.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getRoleID());
                stm.setString(4, user.getPassword());
                check=stm.executeUpdate()==0?false:true;
            }
        } finally{
           
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean checkPassword(String userID, String oldPassword) throws SQLException, NamingException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm =null;
        ResultSet rs = null;
        try {
            conn= DBUtils.getConnection();
            if(conn!=null){
                String sql="SELECT userID\n"
                        + "FROM tblUsers\n"
                        + "WHERE password = ?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, oldPassword);
                rs=stm.executeQuery();
                if(rs.next()){
                    check=true;
                }
            }
        } finally{
             if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    
}
