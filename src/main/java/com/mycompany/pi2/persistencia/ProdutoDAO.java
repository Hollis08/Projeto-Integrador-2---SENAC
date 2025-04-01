package com.mycompany.pi2.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author holli
 */
public class ProdutoDAO {
    
    public void cadastrarP(Produto p){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            
        }catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        }finally{
            JPAUtil.closeEntityManager();
        }
        
    }
    
    public void excluir(int id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            Produto p = em.find(Produto.class, id);
            if(p != null){
                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();
                
                JOptionPane.showMessageDialog(null, "Produto excluido com sucesso.");
            } 
        }catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        }finally{
            JPAUtil.closeEntityManager();
        }
    }
   
    public Produto obter(int id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.find(Produto.class, id);
        }finally{
            JPAUtil.closeEntityManager();
        }
    }
    
   
    
    public void atualizar(Produto p){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        }finally{
            JPAUtil.closeEntityManager();
        }
    }
    
    public List<Produto> listar(String codBarra){
        EntityManager em = JPAUtil.getEntityManager();
        List<Produto> produto = null;
        try{
            String txtQuery = "SELECT p from Produto p "+
                    "where (:codBarra is null OR p.codBarra LIKE :codBarra)";
            
            Query consulta = em.createQuery(txtQuery);
            
            consulta.setParameter("codBarra", codBarra.isEmpty() ? null : "%"+codBarra+"%");
            
            produto = consulta.getResultList();
            
        }finally{
          JPAUtil.closeEntityManager();
      }
      return produto;
    }
    
}
