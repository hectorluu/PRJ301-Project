/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.cart;

import hungld.flower.FlowerDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class CartDTO {

    public Map<String, FlowerDTO> cart;

    public CartDTO() {
    }

    public CartDTO(Map<String, FlowerDTO> cart) {
        this.cart = cart;
    }

    public Map<String, FlowerDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, FlowerDTO> cart) {
        this.cart = cart;
    }

    public void add(FlowerDTO flower) {
        //1. check existed cart
        if (cart == null) {
            cart = new HashMap<String, FlowerDTO>();
        }
        //2. Check eixsted item
        if (cart.containsKey(flower.getProductID())) {
            int quantity = cart.get(flower.getProductID()).getQuantity();
            flower.setQuantity(quantity + flower.getQuantity());
        }
        //3. update product in cart
        cart.put(flower.getProductID(), flower);
    }

    public void delete(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void update(String id, FlowerDTO flower) {
        if (this.cart == null) {
            return;
        }

        if (this.cart.containsKey(id)) {
            this.cart.replace(id, flower);
        }
    }

    public String generateID(){
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy-HHmmss");
        Date date = new Date();
	String dateString = formatter.format(date);
	
	String randomString = ""; 
        for (int i = 0; i < 6; i++) {
	    int ran = new Random().nextInt(10);
	    randomString += ran;
	}
	
	String id = dateString + "-" + randomString;
        
        return id;
    }
}
