package rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import models.Commune;

import java.util.HashMap;
import java.util.Map;

@Path("commune")
public class CommuneRS {
	
	//note
	//On peut avec deux méthodes
	//ArrayList : Tu dois parcourir la liste pour trouver un élément (sauf si tu connais son index). stream().filter() est très utile ici.
	//HashMap : Si tu as la clé, utilise get(), put(), remove(). C'est beaucoup plus efficace que de streamer et filtrer. 
	//Si tu n'as pas la clé et que tu dois chercher sur une valeur, alors stream() redevient utile (par exemple, communes.values().stream().filter(c -> c.getNom().startsWith("V"))).

    // Map de communes : clé = codePostal, valeur = Commune
    private static Map<Integer, Commune> communes = new HashMap<>();

    static {
        communes.put(93, new Commune(93, "Villetaneuse"));
        communes.put(75, new Commune(75, "Paris"));
        communes.put(94, new Commune(94, "Vitry"));
        communes.put(91, new Commune(91, "Massy"));
        communes.put(95, new Commune(95, "Sarcelle"));
    }

    // Q3 : GET /rest/commune/93/Villetaneuse  
    @GET
    @Path("/{codePostal}/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public Commune getCommuneFromPath(
            @PathParam("codePostal") int codePostal,
            @PathParam("nom") String nom) {
        return new Commune(codePostal, nom);
    }

    // READ : GET /rest/commune/93
    @GET
    @Path("/{codePostal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommuneByCodePostal(@PathParam("codePostal") int codePostal) {

        Commune commune = communes.get(codePostal); // accès direct par clé

        if (commune == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Commune avec code postal " + codePostal + " non trouvée")
                    .build();
        }

        return Response.ok(commune).build();
    }

    // CREATE : POST /rest/commune/create
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCommune(
            @FormParam("codePostal") int codePostal,
            @FormParam("nom") String nom) {

        Commune newCommune = new Commune(codePostal, nom);
        communes.put(codePostal, newCommune); // ajoute ou remplace

        String message = "Commune ajoutée. Nombre total de communes : " + communes.size();
        return Response.status(Response.Status.CREATED)
                .entity(message)
                .build();
    }

    // UPDATE : PUT /rest/commune/93
    @PUT
    @Path("/{codePostal}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCommune(
            @PathParam("codePostal") int codePostal,
            @FormParam("nom") String nouveauNom) {

        Commune commune = communes.get(codePostal);

        if (commune == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Commune avec code postal " + codePostal + " non trouvée")
                    .build();
        }

        commune.setNom(nouveauNom); // on modifie l’objet existant

        return Response.ok(commune).build();
    }

    // DELETE : DELETE /rest/commune/93
    @DELETE
    @Path("/{codePostal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCommune(@PathParam("codePostal") int codePostal) {

        Commune removed = communes.remove(codePostal); // supprime par clé

        if (removed == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Commune avec code postal " + codePostal + " non trouvée")
                    .build();
        }

        String message = "Commune supprimée. Nombre de communes restantes : " + communes.size();
        return Response.ok(message).build();
    }
}