/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.controller;

import hungld.user.UserDAO;
import hungld.user.UserDTO;
import hungld.user.UserUpdateErrorDTO;
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
@WebServlet(name = "UserUpdateController", urlPatterns = {"/UserUpdateController"})
public class UserUpdateController extends HttpServlet {
    private static final String ERROR = "update.jsp";
    private static final String SUCCESS = "UserSearchController";
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
        
        UserUpdateErrorDTO userError = new UserUpdateErrorDTO("", "", "", "", "", "");
        String url = ERROR;
        
        try {
            String userID = request.getParameter("userID");
            userID = userID.trim();
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirm = request.getParameter("confirm");
            
            boolean check = false;
                
            if (userID.length() < 3 || userID.length() > 10) {
                check = true;
                userError.setUserIDError("User ID [3,10]");
            }
            if (fullName.length() < 5 || fullName.length() > 50) {
                check = true;
                userError.setFullnameError("Full Name [5,50]");
            }
            if (roleID.length() < 2 || roleID.length() > 10) {
                check = true;
                userError.setRoleIDError("Role ID [2,10]");
            }
            
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            String userIDLogin = ((UserDTO)session.getAttribute("LOGIN_USER")).getUserID();
            boolean checkPass = dao.checkPassword(userID, oldPassword);
            
            if (!checkPass){
                check = true;
                userError.setPasswordError("Current password is not correct !");
            }
            if (!newPassword.equals(confirm)) {
                check = true;
                userError.setConfirmError("Confirm is not matched");
            }
            
            if (check){
                request.setAttribute("ERROR", userError);
            } else {
                UserDTO user = new UserDTO(userID, fullName, roleID, newPassword);
                dao.updateUser(user);
                // update lai session neu dang tu update chinh minh
                if (userIDLogin.equals(userID)){
                    session.setAttribute("LOGIN_USER", user);
                }
                url = SUCCESS;
            }
            
        } catch (Exception ex){
            if (ex.toString().contains("duplicate")){
                userError.setUserIDError("Duplicate UserID !");
                request.setAttribute("ERROR", userError);
            }
            log("Exception at _ UserUpdateController :" + ex.getMessage());
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
