/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.controller;

import hungld.cart.CartDTO;
import hungld.flower.FlowerDAO;
import hungld.flower.FlowerDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartAddController", urlPatterns = {"/CartAddController"})
public class CartAddController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ItemsLoadController";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = ERROR;
        
        try {
            String productID = request.getParameter("productID");
            FlowerDAO dao = new FlowerDAO();
            FlowerDTO flower = dao.findFlowerByID(productID);
            
            String temp_productName = flower.getProductName();
            int temp_quantity = 1;
            float temp_price = (float) flower.getPrice();
            String temp_categoryID = flower.getCategoryID();
            
            FlowerDTO temp_flower = new FlowerDTO(productID, temp_productName, temp_quantity, temp_price, temp_categoryID);
            
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO)session.getAttribute("CART");
            
            if(cart==null){
                cart = new CartDTO();
            }
            
            cart.add(temp_flower);
            session.setAttribute("CART", cart);
            url = SUCCESS;
            
            request.setAttribute("MESSAGE", "Ban da them vao: " + temp_productName + " thanh cong !");
        } catch(Exception ex) {
            log("Exception at _ CartAddController :" + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
