package quarkus.world.tour;

import io.smallrye.mutiny.Multi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/beer")
public class BeerTap {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Beer> menu() {
        return Beer.streamAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/best")
    public Multi<Beer> best() {
        return Beer.byRating(4);
    }
}