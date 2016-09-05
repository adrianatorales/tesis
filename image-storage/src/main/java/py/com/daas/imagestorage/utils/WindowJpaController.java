/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.imagestorage.utils;

import py.com.daas.imagestorage.models.RgbImage;
import py.com.daas.imagestorage.models.RgbWindow;
import py.com.daas.imagestorage.utils.exceptions.NonexistentEntityException;
import py.com.daas.imagestorage.utils.exceptions.PreexistingEntityException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Derlis Argüello
 */
public class WindowJpaController implements Serializable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("py.com.tesisdt_ImageStorage_jar_1.0PU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RgbWindow window) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            RgbImage idRgbImage = window.getIdRgbImage();
//            if (idRgbImage != null) {
//                idRgbImage = em.getReference(idRgbImage.getClass(), idRgbImage.getIdRgbImage());
//                window.setIdRgbImage(idRgbImage);
//            }
            em.persist(window);
//            if (idRgbImage != null) {
//                idRgbImage.getWindowList().add(window);
//                idRgbImage = em.merge(idRgbImage);
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findWindow(window.getIdRgbWindow()) != null) {
                throw new PreexistingEntityException("Window " + window + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RgbWindow window) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RgbWindow persistentWindow = em.find(RgbWindow.class, window.getIdRgbWindow());
            RgbImage idRgbImageOld = persistentWindow.getIdRgbImage();
            RgbImage idRgbImageNew = window.getIdRgbImage();
            if (idRgbImageNew != null) {
                idRgbImageNew = em.getReference(idRgbImageNew.getClass(), idRgbImageNew.getIdRgbImage());
                window.setIdRgbImage(idRgbImageNew);
            }
            window = em.merge(window);
            if (idRgbImageOld != null && !idRgbImageOld.equals(idRgbImageNew)) {
                idRgbImageOld.getWindowList().remove(window);
                idRgbImageOld = em.merge(idRgbImageOld);
            }
            if (idRgbImageNew != null && !idRgbImageNew.equals(idRgbImageOld)) {
                idRgbImageNew.getWindowList().add(window);
                idRgbImageNew = em.merge(idRgbImageNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = window.getIdRgbWindow();
                if (findWindow(id) == null) {
                    throw new NonexistentEntityException("The window with id " + id + " no longer exists.");
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
            RgbWindow window;
            try {
                window = em.getReference(RgbWindow.class, id);
                window.getIdRgbWindow();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The window with id " + id + " no longer exists.", enfe);
            }
            RgbImage idRgbImage = window.getIdRgbImage();
            if (idRgbImage != null) {
                idRgbImage.getWindowList().remove(window);
                idRgbImage = em.merge(idRgbImage);
            }
            em.remove(window);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RgbWindow> findWindowEntities() {
        return findWindowEntities(true, -1, -1);
    }

    public List<RgbWindow> findWindowEntities(int maxResults, int firstResult) {
        return findWindowEntities(false, maxResults, firstResult);
    }

    private List<RgbWindow> findWindowEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RgbWindow.class));
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

    public RgbWindow findWindow(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RgbWindow.class, id);
        } finally {
            em.close();
        }
    }

    public int getWindowCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RgbWindow> rt = cq.from(RgbWindow.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
