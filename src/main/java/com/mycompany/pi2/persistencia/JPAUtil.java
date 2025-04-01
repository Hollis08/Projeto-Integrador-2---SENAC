package com.mycompany.pi2.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class JPAUtil {
    
    private static final String PERSISTENCE_UNIT = "PI-PU";
    
    private static EntityManager em;
    private static EntityManagerFactory mercado;
    
    public static EntityManager getEntityManager(){
        if(mercado == null || !mercado.isOpen())
            mercado = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        
        if(em == null || !em.isOpen()) 
            em = mercado.createEntityManager();

        return em;
    }
    
    public static void closeEntityManager(){
        if(em.isOpen() && em != null){
            em.close();
            mercado.close();
        }
    }
}
