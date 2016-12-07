/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.imagestorage.utils;

import py.com.daas.imagestorage.models.*;
import py.com.daas.imagestorage.utils.exceptions.IllegalOrphanException;
import py.com.daas.imagestorage.utils.exceptions.NonexistentEntityException;
import py.com.daas.imagestorage.utils.exceptions.PreexistingEntityException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Derlis Argüello
 */
public class RgbImageJpaController implements Serializable {
    
    private EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("py.com.tesisdt_ImageStorage_jar_1.0PU");
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RgbImage rgbImage) throws PreexistingEntityException, Exception {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rgbImage);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRgbImage(rgbImage.getIdRgbImage()) != null) {
                throw new PreexistingEntityException("RgbImage " + rgbImage + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RgbImage rgbImage) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RgbImage persistentRgbImage = em.find(RgbImage.class, rgbImage.getIdRgbImage());
            RgbImageDataChannelGreen rgbImageDataChannelGreenOld = persistentRgbImage.getRgbImageDataChannelGreen();
            RgbImageDataChannelGreen rgbImageDataChannelGreenNew = rgbImage.getRgbImageDataChannelGreen();
            RgbImageDataChannelRed rgbImageDataChannelRedOld = persistentRgbImage.getRgbImageDataChannelRed();
            RgbImageDataChannelRed rgbImageDataChannelRedNew = rgbImage.getRgbImageDataChannelRed();
            RgbImageDataChannelBlue rgbImageDataChannelBlueOld = persistentRgbImage.getRgbImageDataChannelBlue();
            RgbImageDataChannelBlue rgbImageDataChannelBlueNew = rgbImage.getRgbImageDataChannelBlue();
            List<RgbWindow> windowListOld = persistentRgbImage.getWindowList();
            List<RgbWindow> windowListNew = rgbImage.getWindowList();
            List<String> illegalOrphanMessages = null;
            if (rgbImageDataChannelGreenOld != null && !rgbImageDataChannelGreenOld.equals(rgbImageDataChannelGreenNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain RgbImageDataChannelGreen " + rgbImageDataChannelGreenOld + " since its idRgbImage field is not nullable.");
            }
            if (rgbImageDataChannelRedOld != null && !rgbImageDataChannelRedOld.equals(rgbImageDataChannelRedNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain RgbImageDataChannelRed " + rgbImageDataChannelRedOld + " since its idRgbImage field is not nullable.");
            }
            if (rgbImageDataChannelBlueOld != null && !rgbImageDataChannelBlueOld.equals(rgbImageDataChannelBlueNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain RgbImageDataChannelBlue " + rgbImageDataChannelBlueOld + " since its idRgbImage field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (rgbImageDataChannelGreenNew != null) {
                rgbImageDataChannelGreenNew = em.getReference(rgbImageDataChannelGreenNew.getClass(), rgbImageDataChannelGreenNew.getIdRgbImageDataChannelGreen());
                rgbImage.setRgbImageDataChannelGreen(rgbImageDataChannelGreenNew);
            }
            if (rgbImageDataChannelRedNew != null) {
                rgbImageDataChannelRedNew = em.getReference(rgbImageDataChannelRedNew.getClass(), rgbImageDataChannelRedNew.getIdRgbImageDataChannelRed());
                rgbImage.setRgbImageDataChannelRed(rgbImageDataChannelRedNew);
            }
            if (rgbImageDataChannelBlueNew != null) {
                rgbImageDataChannelBlueNew = em.getReference(rgbImageDataChannelBlueNew.getClass(), rgbImageDataChannelBlueNew.getIdRgbImageDataChannelBlue());
                rgbImage.setRgbImageDataChannelBlue(rgbImageDataChannelBlueNew);
            }
            List<RgbWindow> attachedWindowListNew = new ArrayList<RgbWindow>();
            for (RgbWindow windowListNewWindowToAttach : windowListNew) {
                windowListNewWindowToAttach = em.getReference(windowListNewWindowToAttach.getClass(), windowListNewWindowToAttach.getIdRgbWindow());
                attachedWindowListNew.add(windowListNewWindowToAttach);
            }
            windowListNew = attachedWindowListNew;
            rgbImage.setWindowList(windowListNew);
            rgbImage = em.merge(rgbImage);
            if (rgbImageDataChannelGreenNew != null && !rgbImageDataChannelGreenNew.equals(rgbImageDataChannelGreenOld)) {
                RgbImage oldIdRgbImageOfRgbImageDataChannelGreen = rgbImageDataChannelGreenNew.getIdRgbImage();
                if (oldIdRgbImageOfRgbImageDataChannelGreen != null) {
                    oldIdRgbImageOfRgbImageDataChannelGreen.setRgbImageDataChannelGreen(null);
                    oldIdRgbImageOfRgbImageDataChannelGreen = em.merge(oldIdRgbImageOfRgbImageDataChannelGreen);
                }
                rgbImageDataChannelGreenNew.setIdRgbImage(rgbImage);
                rgbImageDataChannelGreenNew = em.merge(rgbImageDataChannelGreenNew);
            }
            if (rgbImageDataChannelRedNew != null && !rgbImageDataChannelRedNew.equals(rgbImageDataChannelRedOld)) {
                RgbImage oldIdRgbImageOfRgbImageDataChannelRed = rgbImageDataChannelRedNew.getIdRgbImage();
                if (oldIdRgbImageOfRgbImageDataChannelRed != null) {
                    oldIdRgbImageOfRgbImageDataChannelRed.setRgbImageDataChannelRed(null);
                    oldIdRgbImageOfRgbImageDataChannelRed = em.merge(oldIdRgbImageOfRgbImageDataChannelRed);
                }
                rgbImageDataChannelRedNew.setIdRgbImage(rgbImage);
                rgbImageDataChannelRedNew = em.merge(rgbImageDataChannelRedNew);
            }
            if (rgbImageDataChannelBlueNew != null && !rgbImageDataChannelBlueNew.equals(rgbImageDataChannelBlueOld)) {
                RgbImage oldIdRgbImageOfRgbImageDataChannelBlue = rgbImageDataChannelBlueNew.getIdRgbImage();
                if (oldIdRgbImageOfRgbImageDataChannelBlue != null) {
                    oldIdRgbImageOfRgbImageDataChannelBlue.setRgbImageDataChannelBlue(null);
                    oldIdRgbImageOfRgbImageDataChannelBlue = em.merge(oldIdRgbImageOfRgbImageDataChannelBlue);
                }
                rgbImageDataChannelBlueNew.setIdRgbImage(rgbImage);
                rgbImageDataChannelBlueNew = em.merge(rgbImageDataChannelBlueNew);
            }
            for (RgbWindow windowListOldWindow : windowListOld) {
                if (!windowListNew.contains(windowListOldWindow)) {
                    windowListOldWindow.setIdRgbImage(null);
                    windowListOldWindow = em.merge(windowListOldWindow);
                }
            }
            for (RgbWindow windowListNewWindow : windowListNew) {
                if (!windowListOld.contains(windowListNewWindow)) {
                    RgbImage oldIdRgbImageOfWindowListNewWindow = windowListNewWindow.getIdRgbImage();
                    windowListNewWindow.setIdRgbImage(rgbImage);
                    windowListNewWindow = em.merge(windowListNewWindow);
                    if (oldIdRgbImageOfWindowListNewWindow != null && !oldIdRgbImageOfWindowListNewWindow.equals(rgbImage)) {
                        oldIdRgbImageOfWindowListNewWindow.getWindowList().remove(windowListNewWindow);
                        oldIdRgbImageOfWindowListNewWindow = em.merge(oldIdRgbImageOfWindowListNewWindow);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rgbImage.getIdRgbImage();
                if (findRgbImage(id) == null) {
                    throw new NonexistentEntityException("The rgbImage with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RgbImage rgbImage;
            try {
                rgbImage = em.getReference(RgbImage.class, id);
                rgbImage.getIdRgbImage();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rgbImage with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            RgbImageDataChannelGreen rgbImageDataChannelGreenOrphanCheck = rgbImage.getRgbImageDataChannelGreen();
            if (rgbImageDataChannelGreenOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RgbImage (" + rgbImage + ") cannot be destroyed since the RgbImageDataChannelGreen " + rgbImageDataChannelGreenOrphanCheck + " in its rgbImageDataChannelGreen field has a non-nullable idRgbImage field.");
            }
            RgbImageDataChannelRed rgbImageDataChannelRedOrphanCheck = rgbImage.getRgbImageDataChannelRed();
            if (rgbImageDataChannelRedOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RgbImage (" + rgbImage + ") cannot be destroyed since the RgbImageDataChannelRed " + rgbImageDataChannelRedOrphanCheck + " in its rgbImageDataChannelRed field has a non-nullable idRgbImage field.");
            }
            RgbImageDataChannelBlue rgbImageDataChannelBlueOrphanCheck = rgbImage.getRgbImageDataChannelBlue();
            if (rgbImageDataChannelBlueOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RgbImage (" + rgbImage + ") cannot be destroyed since the RgbImageDataChannelBlue " + rgbImageDataChannelBlueOrphanCheck + " in its rgbImageDataChannelBlue field has a non-nullable idRgbImage field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<RgbWindow> windowList = rgbImage.getWindowList();
            for (RgbWindow windowListWindow : windowList) {
                windowListWindow.setIdRgbImage(null);
                windowListWindow = em.merge(windowListWindow);
            }
            em.remove(rgbImage);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RgbImage> findRgbImageEntities() {
        return findRgbImageEntities(true, -1, -1);
    }

    public List<RgbImage> findRgbImageEntities(int maxResults, int firstResult) {
        return findRgbImageEntities(false, maxResults, firstResult);
    }

    private List<RgbImage> findRgbImageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RgbImage.class));
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

    public RgbImage findRgbImage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RgbImage.class, id);
        } finally {
            em.close();
        }
    }

    public int getRgbImageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RgbImage> rt = cq.from(RgbImage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Window> getWindowsList(int idRgbImage, int roiWindow) throws Exception {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("RgbImage.getWindosList")
                .setParameter("idRgbImage", idRgbImage)
                .setParameter("windowCount", roiWindow);
        
        List<RgbWindow> windowsList = (List<RgbWindow>)query.getResultList();
        if(windowsList.isEmpty()){
            throw new Exception("No se encontraron datos para la imagen " + idRgbImage + " y cantidad de ventanas " + roiWindow);
        }
        List<Window> wList = new ArrayList<>();
        Window window;
        List<RgbWindow> subList;
        //inicializamos los rois
        for (int i = 0; i < windowsList.size(); i+=3) {
            subList = windowsList.subList(i, i+3);
            window = new Window(subList);
            wList.add(window);
        }
           
        return wList;
    }
    
    public List<RgbImage> getImageTest(int minIndex, int maxIndex){
        EntityManager em = getEntityManager();
        return (List<RgbImage>)em.createNamedQuery("RgbImage.getImageTest")
                .setParameter("minIndex", Double.valueOf(minIndex))
                .setParameter("maxIndex", Double.valueOf(maxIndex))
                .getResultList();
    }
    
    public List<RgbImage> getNoiseImageByNoise(String noiseName, Double description){
        EntityManager em = getEntityManager();
        return (List<RgbImage>)em.createNamedQuery("RgbImage.getNoiseImageByNoise")
                .setParameter("noiseName", noiseName)
                .setParameter("description", description)
                .getResultList();
    }
    
//    public List<Integer> getWindowListByImage(RgbImage  rgbImage) {
//        EntityManager em = getEntityManager();
//        return (List<Integer>)em.createNamedQuery("RgbImage.getWindowListByImage")
//                .setParameter("noiseName", noiseName)
//                .setParameter("description", description)
//                .getResultList();
//    }

}