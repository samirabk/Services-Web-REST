package services;

import jakarta.ejb.EJB;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import models.Commune;
import services.CommuneServiceEJB;

@Path("communeService") 
public class CommuneServiceRS {

    @EJB
    private CommuneServiceEJB communeService;

    // 1.b + 1.c : POST /rest/communeService/create
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @FormParam("codePostal") int codePostal,
            @FormParam("nom") String nom) {

        // Création de l'entité
        Commune commune = new Commune(codePostal, nom);

        // Appel de l'EJB pour persister en base
        long id = communeService.createCommune(commune);

        return Response
                .status(Response.Status.CREATED) // Code 201
                .entity("Commune créée en base avec l'ID : " + id)
                .build();
    }
    
    
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findById(@PathParam("id") long id) {

        Commune commune = communeService.findCommuneById(id);

        if (commune == null) {
            // 404 si la commune n'existe pas
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Commune avec id " + id + " non trouvée")
                    .build();
        }

        return Response.ok(commune).build();
        
    }
    
    
 // Q3.b : PUT /rest/communeService/{id}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("id") long id,
            @FormParam("codePostal") int codePostal,
            @FormParam("nom") String nom) {

        // On vérifie d'abord si la commune existe
        Commune existing = communeService.findCommuneById(id);

        if (existing != null) {
            // Elle existe → on la met à jour
            existing.setCodePostal(codePostal);
            existing.setNom(nom);

            Commune updated = communeService.updateCommune(existing);

            return Response
                    .status(Response.Status.OK) // 200
                    .entity("Commune mise à jour : id = " + updated.getId())
                    .build();
        } else {
            // Elle n'existe pas → on la crée avec cet id
            Commune newCommune = new Commune(codePostal, nom);
            // On fixe l'id manuellement (JPA acceptera avec merge)
            newCommune.setId(id);

            Commune created = communeService.updateCommune(newCommune);

            return Response
                    .status(Response.Status.CREATED) // 201
                    .entity("Commune créée avec id = " + created.getId())
                    .build();
        }
    }
    
    
 // Q4.b : DELETE /rest/communeService/{id}
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {

        boolean deleted = communeService.deleteCommuneById(id);

        if (!deleted) {
            // 404 si la commune n'existe pas
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Impossible de supprimer : la commune avec l'id " + id + " n'existe pas.")
                    .build();
        }

        // 200 OK si la suppression a fonctionné
        return Response.ok("Commune avec l'id " + id + " supprimée avec succès.").build();
    }
    
    
}
