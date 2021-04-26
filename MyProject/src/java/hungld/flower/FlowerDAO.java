/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.flower;

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
public class FlowerDAO implements Serializable {
    public List<FlowerDTO> getListFlower() throws SQLException, NamingException {
        List<FlowerDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBUtils.getConnection();
            if (con != null){
                String sql = "SELECT productID, productName, quantity, price, categoryID\n"
                        + "FROM tblProducts";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    // lam tron den 2 so thap phan
                    price = (float) (Math.ceil(price * 100) / 100);
                    String categoryID = rs.getString("categoryID");
                    list.add(new FlowerDTO(productID, productName, quantity, price, categoryID));
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
    
    public FlowerDTO findFlowerByID(String productID) 
            throws SQLException, NamingException {
        FlowerDTO flower = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT productName, quantity, price, categoryID\n"
                        + "FROM tblProducts\n"
                        + "WHERE productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String productName = rs.getString("productName");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    // lam tron den 2 so thap phan
                    price = (float) (Math.ceil(price * 100) / 100);
                    String categoryID = rs.getString("categoryID");
                    flower = new FlowerDTO(productID, productName, quantity, price, categoryID);
                }
            }//end if con is not null
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
        return flower;
    }
}
