/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.order;

import hungld.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class OrderDAO implements Serializable {
    
    public boolean insertOrderToDB(String orderID, String UserID, String fullName, String address, String phone, float total) 
            throws SQLException, NamingException{
	Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "INSERT INTO tblOrders(orderID, userID, fullName, address, phone, total)\n"
                        + "VALUES(?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
		
		stm.setString(1, orderID);
                stm.setString(2, UserID);
		stm.setString(3, fullName);
		stm.setString(4, address);
		stm.setString(5, phone);
                stm.setFloat(6, total);
		int row = stm.executeUpdate();

		if(row > 0){
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
}
