/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.controller;

import hungld.cart.CartDTO;
import hungld.order.OrderDAO;
import hungld.order.OrderErrorDTO;
import hungld.orderdetails.OrderDetailsDAO;
import hungld.user.UserDTO;
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
@WebServlet(name = "OrderConfirmController", urlPatterns = {"/OrderConfirmController"})
public class OrderConfirmController extends HttpServlet {

    private static final String NO_USER = "login.html";
    private static final String ERROR = "bill.jsp";
    private static final String SUCCESS = "orderSuccess.html";

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
        boolean check = false;

        OrderErrorDTO error = new OrderErrorDTO("", "", "", "");
        HttpSession session = request.getSession(false);

        try {
            if (session != null) {
                CartDTO cart = (CartDTO) session.getAttribute("CART");

                String fullname = request.getParameter("fullName");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                float total = Float.parseFloat(request.getParameter("total"));

                if (fullname.trim().length() < 5 || fullname.trim().length() > 50) {
                    check = true;
                    error.setUsernameLengthError("Fullname must be contained 5-50 characters");
                }
                if (phone.trim().length() < 10 || phone.trim().length() > 20) {
                    check = true;
                    error.setPhoneLengthError("Phone must be contained 10-20 numbers");
                } else if (!phone.trim().matches("^[0-9]{10,}$")) {
                    check = true;
                    error.setPhoneFormatError("Phone only contain number.");
                }
                if (address.trim().length() < 2 || address.trim().length() > 100) {
                    check = true;
                    error.setAddressLengthError("Address must be contained 2-100 characters");
                }

                if (check) {
                    request.setAttribute("ERROR_ORDER", error);
                } else {
                    String orderID = cart.generateID();

                    String UserID = ((UserDTO) session.getAttribute("LOGIN_USER")).getUserID();
                    OrderDAO daoInfo = new OrderDAO();
                    boolean result = daoInfo.insertOrderToDB(orderID, UserID, fullname, address, phone, total);

                    if (result) {
                        OrderDetailsDAO daoDetail = new OrderDetailsDAO();
                        result = daoDetail.insertOrderDetailsToDB(orderID, cart);
                    }

                    if (result) {
                        session.removeAttribute("CART");
                        url = SUCCESS;
                    }
                }
            } else {
                url = NO_USER;
            }

        } catch (Exception ex) {
            log("Exception at _ OrderConfirmController :" + ex.getMessage());
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
