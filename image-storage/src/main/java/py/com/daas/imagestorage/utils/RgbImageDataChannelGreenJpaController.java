/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.imagestorage.utils;

import py.com.daas.imagestorage.models.RgbImage;
import py.com.daas.imagestorage.models.RgbImageDataChannelGreen;
import py.com.daas.imagestorage.utils.exceptions.IllegalOrphanException;
import py.com.daas.imagestorage.utils.exceptions.NonexistentEntityException;
import py.com.daas.imagestorage.utils.exceptions.PreexistingEntityException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Derlis Argüello
 */
public class RgbImageDataChannelGreenJpaController implements Serializable {

    public RgbImageDataChannelGreenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RgbImageDataChannelGreen rgbImageDataChannelGreen) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        RgbImage idRgbImageOrphanCheck = rgbImageDataChannelGreen.getIdRgbImage();
        if (idRgbImageOrphanCheck != null) {
            RgbImageDataChannelGreen oldRgbImageDataChannelGreenOfIdRgbImage = idRgbImageOrphanCheck.getRgbImageDataChannelGreen();
            if (oldRgbImageDataChannelGreenOfIdRgbImage != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The RgbImage " + idRgbImageOrphanCheck + " already has an item of type RgbImageDataChannelGreen whose idRgbImage column cannot be null. Please make another selection for the idRgbImage field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RgbImage idRgbImage = rgbImageDataChannelGreen.getIdRgbImage();
            if (idRgbImage != null) {
                idRgbImage = em.getReference(idRgbImage.getClass(), idRgbImage.getIdRgbImage());
                rgbImageDataChannelGreen.setIdRgbImage(idRgbImage);
            }
            em.persist(rgbImageDataChannelGreen);
            if (idRgbImage != null) {
                idRgbImage.setRgbImageDataChannelGreen(rgbImageDataChannelGreen);
                idRgbImage = em.merge(idRgbImage);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRgbImageDataChannelGreen(rgbImageDataChannelGreen.getIdRgbImageDataChannelGreen()) != null) {
                throw new PreexistingEntityException("RgbImageDataChannelGreen " + rgbImageDataChannelGreen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RgbImageDataChannelGreen rgbImageDataChannelGreen) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RgbImageDataChannelGreen persistentRgbImageDataChannelGreen = em.find(RgbImageDataChannelGreen.class, rgbImageDataChannelGreen.getIdRgbImageDataChannelGreen());
            RgbImage idRgbImageOld = persistentRgbImageDataChannelGreen.getIdRgbImage();
            RgbImage idRgbImageNew = rgbImageDataChannelGreen.getIdRgbImage();
            List<String> illegalOrphanMessages = null;
            if (idRgbImageNew != null && !idRgbImageNew.equals(idRgbImageOld)) {
                RgbImageDataChannelGreen oldRgbImageDataChannelGreenOfIdRgbImage = idRgbImageNew.getRgbImageDataChannelGreen();
                if (oldRgbImageDataChannelGreenOfIdRgbImage != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The RgbImage " + idRgbImageNew + " already has an item of type RgbImageDataChannelGreen whose idRgbImage column cannot be null. Please make another selection for the idRgbImage field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRgbImageNew != null) {
                idRgbImageNew = em.getReference(idRgbImageNew.getClass(), idRgbImageNew.getIdRgbImage());
                rgbImageDataChannelGreen.setIdRgbImage(idRgbImageNew);
            }
            rgbImageDataChannelGreen = em.merge(rgbImageDataChannelGreen);
            if (idRgbImageOld != null && !idRgbImageOld.equals(idRgbImageNew)) {
                idRgbImageOld.setRgbImageDataChannelGreen(null);
                idRgbImageOld = em.merge(idRgbImageOld);
            }
            if (idRgbImageNew != null && !idRgbImageNew.equals(idRgbImageOld)) {
                idRgbImageNew.setRgbImageDataChannelGreen(rgbImageDataChannelGreen);
                idRgbImageNew = em.merge(idRgbImageNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rgbImageDataChannelGreen.getIdRgbImageDataChannelGreen();
                if (findRgbImageDataChannelGreen(id) == null) {
                    throw new NonexistentEntityException("The rgbImageDataChannelGreen with id " + id + " no longer exists.");
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
            RgbImageDataChannelGreen rgbImageDataChannelGreen;
            try {
                rgbImageDataChannelGreen = em.getReference(RgbImageDataChannelGreen.class, id);
                rgbImageDataChannelGreen.getIdRgbImageDataChannelGreen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rgbImageDataChannelGreen with id " + id + " no longer exists.", enfe);
            }
            RgbImage idRgbImage = rgbImageDataChannelGreen.getIdRgbImage();
            if (idRgbImage != null) {
                idRgbImage.setRgbImageDataChannelGreen(null);
                idRgbImage = em.merge(idRgbImage);
            }
            em.remove(rgbImageDataChannelGreen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RgbImageDataChannelGreen> findRgbImageDataChannelGreenEntities() {
        return findRgbImageDataChannelGreenEntities(true, -1, -1);
    }

    public List<RgbImageDataChannelGreen> findRgbImageDataChannelGreenEntities(int maxResults, int firstResult) {
        return findRgbImageDataChannelGreenEntities(false, maxResults, firstResult);
    }

    private List<RgbImageDataChannelGreen> findRgbImageDataChannelGreenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RgbImageDataChannelGreen.class));
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

    public RgbImageDataChannelGreen findRgbImageDataChannelGreen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RgbImageDataChannelGreen.class, id);
        } finally {
            em.close();
        }
    }

    public int getRgbImageDataChannelGreenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RgbImageDataChannelGreen> rt = cq.from(RgbImageDataChannelGreen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
