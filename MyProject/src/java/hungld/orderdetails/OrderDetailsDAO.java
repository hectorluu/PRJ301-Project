/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.orderdetails;

import hungld.cart.CartDTO;
import hungld.flower.FlowerDTO;
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
public class OrderDetailsDAO implements Serializable {
    public boolean insertOrderDetailsToDB(String orderID, CartDTO cart) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        int row = 0;

        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "INSERT INTO tblOrderDetails(orderID, productID, productName, quantity)\n"
                        + "VALUES(?,?,?,?)";
                stm = con.prepareStatement(sql);

                for (FlowerDTO flower: cart.getCart().values()) {
                    stm.setString(1, orderID);
                    stm.setString(2, flower.getProductID());
                    stm.setString(3, flower.getProductName());
                    stm.setInt(4, flower.getQuantity());

                    row += stm.executeUpdate();
                }

                if (row == cart.getCart().values().size()) {
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
