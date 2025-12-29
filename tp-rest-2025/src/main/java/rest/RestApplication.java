package rest;


import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class RestApplication extends Application {
    // Needed to enable Jakarta REST and specify path.
}
