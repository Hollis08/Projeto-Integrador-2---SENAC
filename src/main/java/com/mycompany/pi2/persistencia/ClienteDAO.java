package com.mycompany.pi2.persistencia;

import jakarta.persistence.EntityManager;

/**
 *
 * @author holli
 */
public class ClienteDAO {
    
    public void cadastrarC(Cliente c){
        EntityManager em = JPAUtil.getEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            
        }catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        }finally{
            JPAUtil.closeEntityManager();
        }
        
    
    }
    
    
}
