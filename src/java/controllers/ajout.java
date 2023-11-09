/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Etudiant;

/**
 *
 * @author hammi
 */
@WebServlet(name = "ajout", urlPatterns = {"/ajout"})
public class ajout extends HttpServlet {

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
      /*
        
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("DalyProjetPU"); // etablir une connex mon app base de donneeés 
               /* classe permerttre manipuler base de classe db */
    /* create statement           EntityManager   em = emf.createEntityManager();
        int id= Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        
        Etudiant e = new Etudiant (id,nom,prenom);
        
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        */
    //// ta3 l classe 
     /* conection */ EntityManagerFactory emf = Persistence.createEntityManagerFactory("DalyProjetPU"); // etablir une connex mon app base de donneeés 
               /* classe permerttre manipuler base de classe db */
    /* create statement*/           EntityManager   em = emf.createEntityManager();
               int id = Integer.parseInt(request.getParameter("id"));
               String nom = request.getParameter("nom");
               String prenom = request.getParameter("prenom");
             
               Etudiant e = new Etudiant(id,nom,prenom);
               
               em.getTransaction().begin();
               em.persist(e);
               em.getTransaction().commit();
               em.close();
               emf.close();
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

  
