package rest;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import models.Calculator;

@Path("divise")   // => /rest/divise
public class DiviseRest {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response divise(@FormParam("a") int a, @FormParam("b") int b) {

        // Si denominateur == 0 -> erreur
        if (b == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)  // 400
                    .entity("DENOMINATEUR EST NULL")
                    .build();
        }

        Calculator calc = new Calculator(0, 0, 0);
        Calculator resultat = calc.divise(a, b);

        // HTTP 200 OK + objet Calculator en JSON
        return Response
                .ok(resultat)   // body = JSON de Calculator
                .build();
    }
}