package br.com.challengerclickbusjava.controller;
import br.com.challengerclickbusjava.dto.PlaceDTO;
import br.com.challengerclickbusjava.entity.Place;
import br.com.challengerclickbusjava.exception.PlaceNotFoundException;
import br.com.challengerclickbusjava.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Api("places")
@RestController
@RequestMapping("places")
public class PlaceController {

    private final PlaceService service;
    public PlaceController(PlaceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PlaceDTO> create(@Valid @RequestBody PlaceDTO dto) {
        return new ResponseEntity<PlaceDTO>(service.save(dto.buildPlace()).convertToDTO(), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PlaceDTO> findById(@PathVariable Long id) {
        return service.findById(id)
                      .map(place -> ResponseEntity.ok(place.convertToDTO()))
                      .orElseThrow(() -> new PlaceNotFoundException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Iterable<PlaceDTO>> findById(@PathVariable String name) {
        Iterable<PlaceDTO> places = PlaceDTO.convertToList(this.service.findByName(name));
        if (!places.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(places);
    }

    @GetMapping
    public ResponseEntity<Iterable<PlaceDTO>> findAll() {
        Iterable<PlaceDTO> places = PlaceDTO.convertToList(service.findAll());
        return ResponseEntity.ok(places);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaceDTO> alter(@PathVariable Long id, @RequestBody @Valid PlaceDTO placeDTO) {
        Place place = service.findById(id).orElseThrow(null);
        return new ResponseEntity<PlaceDTO>(service.alter(place, placeDTO).convertToDTO(), HttpStatus.OK);
    }
}
