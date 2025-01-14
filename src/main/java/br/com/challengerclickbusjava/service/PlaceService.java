package br.com.challengerclickbusjava.service;
import br.com.challengerclickbusjava.dto.PlaceDTO;
import br.com.challengerclickbusjava.entity.Place;
import br.com.challengerclickbusjava.repository.PlaceRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository repository;
    public PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    public List<Place> findAll() {
        return this.repository.findAll();
    }

    public Optional<Place> findById(@NotNull Long id) {
        return this.repository.findById(id);
    }

    public Place save(@NotNull Place place) {
        return this.repository.save(place);
    }

    public List<Place> findByName(@NotNull String name) {
        return this.repository.findByName(name);
    }

    public Place alter(@NotNull Place place,@NotNull PlaceDTO placeDTO) {
        Place placeEdited = new Place(place, placeDTO);
        this.repository.save(placeEdited);
        return placeEdited;
    }
}
