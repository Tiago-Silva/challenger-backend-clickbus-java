package br.com.challengerclickbusjava.entity;
import br.com.challengerclickbusjava.dto.PlaceDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @jakarta.validation.constraints.NotNull
    private String name;

    @jakarta.validation.constraints.NotNull
    private String slug;

    @jakarta.validation.constraints.NotNull
    private String city;

    @NotNull
    private String state;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Place() {
    }

    public Place(String name, String slug, String city, String state) {
        this.name = name;
        this.slug = slug;
        this.city = city;
        this.state = state;
        this.createdAt = LocalDateTime.now();
    }

    public Place(Place place, PlaceDTO placeDTO) {
        this.id = place.getId();
        this.name = placeDTO.getName();
        this.slug = placeDTO.getSlug();
        this.city = placeDTO.getCity();
        this.state = placeDTO.getState();
        this.createdAt = place.getCreatedAt();
        this.updatedAt = LocalDateTime.now();
    }

    public static Place of(String name, String slug, String city, String state) {
        return new Place(name, slug, city, state);
    }

    public PlaceDTO convertToDTO() {
        return PlaceDTO.of(this.name, this.slug, this.city, this.state);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
