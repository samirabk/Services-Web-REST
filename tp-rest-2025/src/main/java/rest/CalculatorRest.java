package rest;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import models.Calculator;


@Path("calcul")

public class CalculatorRest {

	@GET
	@Path("add2/{a}/{b}")
	@Produces(MediaType.APPLICATION_JSON)
	public Calculator add(@PathParam("a") int a, @PathParam("b") int b) {
		Calculator calc = new Calculator(0, 0, 0);
		return calc.add(a, b);
	}

	// : GET /rest/add?a=10&b=10
	@GET
	@Path("add1")
	@Produces(MediaType.APPLICATION_JSON)
	public Calculator add1(@QueryParam("a") int a, @QueryParam("b") int b) {
		Calculator calc = new Calculator(0, 0, 0);
		return calc.add(a, b);
	}

	@POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Calculator add2(@FormParam("a") int a, @FormParam("b") int b) {
        Calculator calc = new Calculator(0, 0, 0);
        return calc.add(a, b);
    }

    @POST
    @Path("add/multiply")
    @Produces(MediaType.APPLICATION_XML)
    public String multiply(@FormParam("a") int a, @FormParam("b") int b) {
        int result = a * b;
        return "<calculator>" +
                   "<chiffre1>" + a + "</chiffre1>" +
                   "<chiffre2>" + b + "</chiffre2>" +
                   "<result>" + result + "</result>" +
               "</calculator>";
    }

	
}