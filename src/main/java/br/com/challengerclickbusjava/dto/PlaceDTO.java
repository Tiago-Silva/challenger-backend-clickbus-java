package br.com.challengerclickbusjava.dto;

import br.com.challengerclickbusjava.entity.Place;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceDTO {

    @NotNull
    private String name;

    @NotNull
    private String slug;

    @NotNull
    private String city;

    @NotNull
    private String state;

    public PlaceDTO(String name, String slug, String city, String state) {
        this.name = name;
        this.slug = slug;
        this.city = city;
        this.state = state;
    }

    public static PlaceDTO of(String name, String slug, String city, String state) {
        return new PlaceDTO(name, slug, city, state);
    }

    public static Iterable<PlaceDTO> convertToList(List<Place> places) {
        return places.stream().map(Place::convertToDTO).collect(Collectors.toList());
    }

    public Place buildPlace() {
        return Place.of(this.name, this.slug, this.city, this.state);
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
}
