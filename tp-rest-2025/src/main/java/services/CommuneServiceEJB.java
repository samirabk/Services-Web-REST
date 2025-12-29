package services;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import models.Commune;

@Stateless
public class CommuneServiceEJB {
	
   //5 ajouter 
	@PersistenceContext(unitName = "tp-jakartaee")
    EntityManager entityManager;

    // 5.1) persister une Commune et retourner sa clé primaire
    public long createCommune(Commune commune) {
        entityManager.persist(commune);   // enregistre en base
        // après persist, l'id auto-généré est rempli dans l'objet
        return commune.getId();
        
      //6 completer CommuneService 
    }
        
     // Q2.a : trouver une commune par son id
        public Commune findCommuneById(long id) {
            return entityManager.find(Commune.class, id);
        }
        
     // Q3.
        public Commune updateCommune(Commune commune) {
            // merge retourne l'entité gérée (après update)
            return entityManager.merge(commune);
        }
            
    // Q4.a : supprimer une commune par son id
            public boolean deleteCommuneById(long id) {
                Commune commune = entityManager.find(Commune.class, id);
                
                if (commune != null) {
                    entityManager.remove(commune); // Supprime de la base
                    return true; // Suppression réussie
                }
                
                return false; // Non trouvée
            }
       
            
            
            public List<Commune> findAllCommune() {
                return entityManager
                        .createQuery("SELECT c FROM Commune c", Commune.class)
                        .getResultList();
            }
            
            

}  