/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.imagestorage.utils;

import py.com.daas.imagestorage.models.RgbImage;
import py.com.daas.imagestorage.models.RgbImageDataChannelBlue;
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
public class RgbImageDataChannelBlueJpaController implements Serializable {

    public RgbImageDataChannelBlueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RgbImageDataChannelBlue rgbImageDataChannelBlue) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        RgbImage idRgbImageOrphanCheck = rgbImageDataChannelBlue.getIdRgbImage();
        if (idRgbImageOrphanCheck != null) {
            RgbImageDataChannelBlue oldRgbImageDataChannelBlueOfIdRgbImage = idRgbImageOrphanCheck.getRgbImageDataChannelBlue();
            if (oldRgbImageDataChannelBlueOfIdRgbImage != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The RgbImage " + idRgbImageOrphanCheck + " already has an item of type RgbImageDataChannelBlue whose idRgbImage column cannot be null. Please make another selection for the idRgbImage field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RgbImage idRgbImage = rgbImageDataChannelBlue.getIdRgbImage();
            if (idRgbImage != null) {
                idRgbImage = em.getReference(idRgbImage.getClass(), idRgbImage.getIdRgbImage());
                rgbImageDataChannelBlue.setIdRgbImage(idRgbImage);
            }
            em.persist(rgbImageDataChannelBlue);
            if (idRgbImage != null) {
                idRgbImage.setRgbImageDataChannelBlue(rgbImageDataChannelBlue);
                idRgbImage = em.merge(idRgbImage);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRgbImageDataChannelBlue(rgbImageDataChannelBlue.getIdRgbImageDataChannelBlue()) != null) {
                throw new PreexistingEntityException("RgbImageDataChannelBlue " + rgbImageDataChannelBlue + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RgbImageDataChannelBlue rgbImageDataChannelBlue) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RgbImageDataChannelBlue persistentRgbImageDataChannelBlue = em.find(RgbImageDataChannelBlue.class, rgbImageDataChannelBlue.getIdRgbImageDataChannelBlue());
            RgbImage idRgbImageOld = persistentRgbImageDataChannelBlue.getIdRgbImage();
            RgbImage idRgbImageNew = rgbImageDataChannelBlue.getIdRgbImage();
            List<String> illegalOrphanMessages = null;
            if (idRgbImageNew != null && !idRgbImageNew.equals(idRgbImageOld)) {
                RgbImageDataChannelBlue oldRgbImageDataChannelBlueOfIdRgbImage = idRgbImageNew.getRgbImageDataChannelBlue();
                if (oldRgbImageDataChannelBlueOfIdRgbImage != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The RgbImage " + idRgbImageNew + " already has an item of type RgbImageDataChannelBlue whose idRgbImage column cannot be null. Please make another selection for the idRgbImage field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRgbImageNew != null) {
                idRgbImageNew = em.getReference(idRgbImageNew.getClass(), idRgbImageNew.getIdRgbImage());
                rgbImageDataChannelBlue.setIdRgbImage(idRgbImageNew);
            }
            rgbImageDataChannelBlue = em.merge(rgbImageDataChannelBlue);
            if (idRgbImageOld != null && !idRgbImageOld.equals(idRgbImageNew)) {
                idRgbImageOld.setRgbImageDataChannelBlue(null);
                idRgbImageOld = em.merge(idRgbImageOld);
            }
            if (idRgbImageNew != null && !idRgbImageNew.equals(idRgbImageOld)) {
                idRgbImageNew.setRgbImageDataChannelBlue(rgbImageDataChannelBlue);
                idRgbImageNew = em.merge(idRgbImageNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rgbImageDataChannelBlue.getIdRgbImageDataChannelBlue();
                if (findRgbImageDataChannelBlue(id) == null) {
                    throw new NonexistentEntityException("The rgbImageDataChannelBlue with id " + id + " no longer exists.");
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
            RgbImageDataChannelBlue rgbImageDataChannelBlue;
            try {
                rgbImageDataChannelBlue = em.getReference(RgbImageDataChannelBlue.class, id);
                rgbImageDataChannelBlue.getIdRgbImageDataChannelBlue();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rgbImageDataChannelBlue with id " + id + " no longer exists.", enfe);
            }
            RgbImage idRgbImage = rgbImageDataChannelBlue.getIdRgbImage();
            if (idRgbImage != null) {
                idRgbImage.setRgbImageDataChannelBlue(null);
                idRgbImage = em.merge(idRgbImage);
            }
            em.remove(rgbImageDataChannelBlue);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RgbImageDataChannelBlue> findRgbImageDataChannelBlueEntities() {
        return findRgbImageDataChannelBlueEntities(true, -1, -1);
    }

    public List<RgbImageDataChannelBlue> findRgbImageDataChannelBlueEntities(int maxResults, int firstResult) {
        return findRgbImageDataChannelBlueEntities(false, maxResults, firstResult);
    }

    private List<RgbImageDataChannelBlue> findRgbImageDataChannelBlueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RgbImageDataChannelBlue.class));
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

    public RgbImageDataChannelBlue findRgbImageDataChannelBlue(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RgbImageDataChannelBlue.class, id);
        } finally {
            em.close();
        }
    }

    public int getRgbImageDataChannelBlueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RgbImageDataChannelBlue> rt = cq.from(RgbImageDataChannelBlue.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
