/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.imagestorage.utils;

import py.com.daas.imagestorage.models.RgbImage;
import py.com.daas.imagestorage.models.RgbImageDataChannelRed;
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
public class RgbImageDataChannelRedJpaController implements Serializable {

    public RgbImageDataChannelRedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RgbImageDataChannelRed rgbImageDataChannelRed) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        RgbImage idRgbImageOrphanCheck = rgbImageDataChannelRed.getIdRgbImage();
        if (idRgbImageOrphanCheck != null) {
            RgbImageDataChannelRed oldRgbImageDataChannelRedOfIdRgbImage = idRgbImageOrphanCheck.getRgbImageDataChannelRed();
            if (oldRgbImageDataChannelRedOfIdRgbImage != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The RgbImage " + idRgbImageOrphanCheck + " already has an item of type RgbImageDataChannelRed whose idRgbImage column cannot be null. Please make another selection for the idRgbImage field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RgbImage idRgbImage = rgbImageDataChannelRed.getIdRgbImage();
            if (idRgbImage != null) {
                idRgbImage = em.getReference(idRgbImage.getClass(), idRgbImage.getIdRgbImage());
                rgbImageDataChannelRed.setIdRgbImage(idRgbImage);
            }
            em.persist(rgbImageDataChannelRed);
            if (idRgbImage != null) {
                idRgbImage.setRgbImageDataChannelRed(rgbImageDataChannelRed);
                idRgbImage = em.merge(idRgbImage);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRgbImageDataChannelRed(rgbImageDataChannelRed.getIdRgbImageDataChannelRed()) != null) {
                throw new PreexistingEntityException("RgbImageDataChannelRed " + rgbImageDataChannelRed + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RgbImageDataChannelRed rgbImageDataChannelRed) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RgbImageDataChannelRed persistentRgbImageDataChannelRed = em.find(RgbImageDataChannelRed.class, rgbImageDataChannelRed.getIdRgbImageDataChannelRed());
            RgbImage idRgbImageOld = persistentRgbImageDataChannelRed.getIdRgbImage();
            RgbImage idRgbImageNew = rgbImageDataChannelRed.getIdRgbImage();
            List<String> illegalOrphanMessages = null;
            if (idRgbImageNew != null && !idRgbImageNew.equals(idRgbImageOld)) {
                RgbImageDataChannelRed oldRgbImageDataChannelRedOfIdRgbImage = idRgbImageNew.getRgbImageDataChannelRed();
                if (oldRgbImageDataChannelRedOfIdRgbImage != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The RgbImage " + idRgbImageNew + " already has an item of type RgbImageDataChannelRed whose idRgbImage column cannot be null. Please make another selection for the idRgbImage field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRgbImageNew != null) {
                idRgbImageNew = em.getReference(idRgbImageNew.getClass(), idRgbImageNew.getIdRgbImage());
                rgbImageDataChannelRed.setIdRgbImage(idRgbImageNew);
            }
            rgbImageDataChannelRed = em.merge(rgbImageDataChannelRed);
            if (idRgbImageOld != null && !idRgbImageOld.equals(idRgbImageNew)) {
                idRgbImageOld.setRgbImageDataChannelRed(null);
                idRgbImageOld = em.merge(idRgbImageOld);
            }
            if (idRgbImageNew != null && !idRgbImageNew.equals(idRgbImageOld)) {
                idRgbImageNew.setRgbImageDataChannelRed(rgbImageDataChannelRed);
                idRgbImageNew = em.merge(idRgbImageNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rgbImageDataChannelRed.getIdRgbImageDataChannelRed();
                if (findRgbImageDataChannelRed(id) == null) {
                    throw new NonexistentEntityException("The rgbImageDataChannelRed with id " + id + " no longer exists.");
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
            RgbImageDataChannelRed rgbImageDataChannelRed;
            try {
                rgbImageDataChannelRed = em.getReference(RgbImageDataChannelRed.class, id);
                rgbImageDataChannelRed.getIdRgbImageDataChannelRed();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rgbImageDataChannelRed with id " + id + " no longer exists.", enfe);
            }
            RgbImage idRgbImage = rgbImageDataChannelRed.getIdRgbImage();
            if (idRgbImage != null) {
                idRgbImage.setRgbImageDataChannelRed(null);
                idRgbImage = em.merge(idRgbImage);
            }
            em.remove(rgbImageDataChannelRed);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RgbImageDataChannelRed> findRgbImageDataChannelRedEntities() {
        return findRgbImageDataChannelRedEntities(true, -1, -1);
    }

    public List<RgbImageDataChannelRed> findRgbImageDataChannelRedEntities(int maxResults, int firstResult) {
        return findRgbImageDataChannelRedEntities(false, maxResults, firstResult);
    }

    private List<RgbImageDataChannelRed> findRgbImageDataChannelRedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RgbImageDataChannelRed.class));
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

    public RgbImageDataChannelRed findRgbImageDataChannelRed(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RgbImageDataChannelRed.class, id);
        } finally {
            em.close();
        }
    }

    public int getRgbImageDataChannelRedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RgbImageDataChannelRed> rt = cq.from(RgbImageDataChannelRed.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
