package com.anizzzz.sfmovies.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "movie_data")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class MovieData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title = "";
    @JsonAlias(value = "release_year")
    private String releaseYear = "";
    private String locations = "";

    @JsonAlias(value = "fun_facts")
    @Column(name = "fun_facts", columnDefinition = "text")
    private String funFacts = "";

    @JsonAlias(value = "production_company")
    @Column(name = "product_company", columnDefinition = "text")
    private String productionCompany = "";
    private String distributor = "";
    private String writer = "";

    @JsonAlias(value = "actor_1")
    @Column(name = "actor_1", columnDefinition = "text")
    private String actor1 = "";
    @JsonAlias(value = "actor_2")
    @Column(name = "actor_2", columnDefinition = "text")
    private String actor2 = "";
    @JsonAlias(value = "actor_3")
    @Column(name = "actor_3", columnDefinition = "text")
    private String actor3 = "";

    private String latitude = "";
    private String longitude = "";

    public MovieData(String title, String releaseYear, String locations, String funFacts,
                     String productionCompany, String distributor, String writer, String actor1,
                     String actor2, String actor3) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.locations = locations;
        this.funFacts = funFacts;
        this.productionCompany = productionCompany;
        this.distributor = distributor;
        this.writer = writer;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }

    public MovieData trimAll(){
        this.setTitle(this.getTitle().trim());
        this.setReleaseYear(this.getReleaseYear().trim());
        this.setLocations(this.getLocations().trim());
        this.setFunFacts(this.getFunFacts().trim());
        this.setProductionCompany(this.getProductionCompany().trim());
        this.setDistributor(this.getDistributor().trim());
        this.setWriter(this.getWriter().trim());
        this.setActor1(this.getActor1().trim());
        this.setActor2(this.getActor2().trim());
        this.setActor3(this.getActor3().trim());
        this.setLatitude(this.getLatitude().trim());
        this.setLongitude(this.getLongitude().trim());
        return this;
    }
}
