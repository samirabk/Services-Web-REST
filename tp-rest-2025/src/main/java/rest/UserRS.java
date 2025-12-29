package rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import models.User;
import services.UserService;

@Path("userService")
public class UserRS {

    @Inject        // CDI (pas @EJB)
    private UserService userService;

    // POST /rest/userService/create
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @FormParam("login") String login,
            @FormParam("password") String password) {

        User user = new User(login, password);
        Long id = userService.createUser(user);

        return Response
                .status(Response.Status.CREATED)
                .entity("User créé avec id = " + id)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("User " + id + " non trouvé")
                           .build();
        }
        return Response.ok(user).build();
    }
}