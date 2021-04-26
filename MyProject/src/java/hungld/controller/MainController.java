/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    private static final String ERROR_PAGE = "error.jsp";
    private static final String LOGIN_CONTROLLER = "UserLoginController";
    private static final String SEARCH_CONTROLLER = "UserSearchController";
    private static final String LOGOUT_CONTROLLER = "UserLogoutController";
    private static final String DELETE_CONTROLLER = "UserDeleteController";
    private static final String UPDATE_PAGE = "update.jsp";
    private static final String UPDATE_CONTROLLER = "UserUpdateController";
    private static final String INSERT_CONTROLLER = "UserInsertController";
    private static final String ADD_CONTROLLER = "CartAddController";
    private static final String VIEW = "viewCart.jsp";
    private static final String REMOVE_CONTROLLER = "CartRemoveController";
    // update quantity in shopping cart
    private static final String EDIT_CONTROLLER = "CartEditController";
    //
    private static final String ORDER = "bill.jsp";
    private static final String ORDER_CONFIRM_CONTROLLER = "OrderConfirmController";
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
                
        String action = request.getParameter("action");
        String url = ERROR_PAGE;
        
        try {
            if (action.equals("Login")){
                url = LOGIN_CONTROLLER;
            } else if (action.equals("Search")){
                url = SEARCH_CONTROLLER;
            } else if (action.equals("Logout")){
                url = LOGOUT_CONTROLLER;
            } else if (action.equals("Delete")){
                url = DELETE_CONTROLLER;
            } else if (action.equals("Update")){
                url = UPDATE_PAGE;
            } else if (action.equals("Confirm")){
                url = UPDATE_CONTROLLER;
            } else if(action.equals("Insert")){
                url = INSERT_CONTROLLER;
            } else if (action.equals("Add")){
                url = ADD_CONTROLLER;
            } else if (action.equals("View")){
                url = VIEW;
            } else if (action.equals("Remove")){
                url = REMOVE_CONTROLLER;
            } else if (action.equals("Edit")){
                url = EDIT_CONTROLLER;
            } else if (action.equals("Order")){
                url = ORDER;
            } else if (action.equals("Confirm Order")){
                url = ORDER_CONFIRM_CONTROLLER;
            }
        } catch(Exception ex) {
            log("Exception at _ MainController :" + ex.getMessage());
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
