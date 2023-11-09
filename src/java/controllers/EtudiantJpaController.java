/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Etudiant;

/**
 *
 * @author hammi
 */
public class EtudiantJpaController implements Serializable {

    public EtudiantJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Etudiant etudiant) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin(); // transcation sql prep 
            em.persist(etudiant);  // insert into req
            em.getTransaction().commit();  // ok bara mrigel 
        } catch (Exception ex) {
            if (findEtudiant(etudiant.getId()) != null) {
                throw new PreexistingEntityException("Etudiant " + etudiant + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Etudiant etudiant) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            etudiant = em.merge(etudiant);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = etudiant.getId();
                if (findEtudiant(id) == null) {
                    throw new NonexistentEntityException("The etudiant with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Etudiant etudiant;
            try {
                etudiant = em.getReference(Etudiant.class, id);
                etudiant.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The etudiant with id " + id + " no longer exists.", enfe);
            }
            em.remove(etudiant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Etudiant> findEtudiantEntities() {
        return findEtudiantEntities(true, -1, -1);
    }

    public List<Etudiant> findEtudiantEntities(int maxResults, int firstResult) {
        return findEtudiantEntities(false, maxResults, firstResult);
    }

    private List<Etudiant> findEtudiantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Etudiant.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Etudiant findEtudiant(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Etudiant.class, id);
        } finally {
            em.close();
        }
    }

    public int getEtudiantCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Etudiant> rt = cq.from(Etudiant.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
