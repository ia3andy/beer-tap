package quarkus.world.tour;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Multi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Beer extends PanacheEntity {

    @NotEmpty
    @Column(unique = true)
    public String name;

    public int rating;

    static Multi<Beer> byRating(int minRating) {
        return Beer.stream("rating >= ?1", Sort.descending("rating"),  minRating);
    }

}
