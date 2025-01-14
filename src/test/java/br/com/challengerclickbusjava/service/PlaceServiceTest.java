package br.com.challengerclickbusjava.service;

import br.com.challengerclickbusjava.dto.PlaceDTO;
import br.com.challengerclickbusjava.entity.Place;
import br.com.challengerclickbusjava.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {

    public static final String NAME_PLACE = "Butanta";

    @Mock
    private PlaceRepository repository;

    @InjectMocks
    private PlaceService service;

    private Place place;

    @BeforeEach
    void setUp() {
        place = Place.of(NAME_PLACE, "bt", "Sao Paulo", "SP");
    }

    @Test
    void whenFindByIdOk() {
        when(repository.findById(1L)).thenReturn(Optional.of(place));

        Place actual = service.findById(1L).get();

        assertEquals(place.getName(), actual.getName());
        assertEquals(place.getSlug(), actual.getSlug());
        assertEquals(place.getCity(), actual.getCity());
        assertEquals(place.getState(), actual.getState());
        assertEquals(place.getCreatedAt(), actual.getCreatedAt());
        assertNull(actual.getUpdatedAt());
        verify(repository, atLeastOnce()).findById(anyLong());
    }


    @Test
    void whenFindByIdThenReturnEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertFalse(service.findById(1L).isPresent());
        verify(repository, atLeastOnce()).findById(anyLong());
    }

    @Test
    void whenFindByNameOk() {
        when(repository.findByName(NAME_PLACE)).thenReturn(Collections.singletonList(place));

        List<Place> actual = service.findByName(NAME_PLACE);

        assertEquals(1, actual.size());
        verify(repository, atLeastOnce()).findByName(NAME_PLACE);
    }

    @Test
    void whenFindByNameNotFound() {
        when(repository.findByName(NAME_PLACE)).thenReturn(null);

        assertNull(service.findByName(NAME_PLACE));
        verify(repository, atLeastOnce()).findByName(NAME_PLACE);
    }

    @Test
    void whenSaveOk() {
        when(repository.save(any(Place.class))).thenReturn(place);

        Place actual = service.save(place);

        assertEquals(place, actual);
        verify(repository, atLeastOnce()).save(any(Place.class));
    }

    @Test
    void whenFindAllOk() {
        List<Place> places = List.of(
                Place.of("place01", "slug01", "city01", "state01"),
                Place.of("place02", "slug02", "city02", "state02")
        );

        when(repository.findAll()).thenReturn(places);

        List<Place> actual = service.findAll();

        assertEquals(places.size(), actual.size());
        assertEquals(places.get(0).getName(), actual.get(0).getName());
        assertEquals(places.get(1).getName(), actual.get(1).getName());
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    void whenAlterPlaceOk() {
        String editedName = "Lorem Ipsum";
        PlaceDTO placeDTO = PlaceDTO.of(editedName, place.getSlug(), place.getCity(), place.getState());
        when(repository.save(any(Place.class))).thenReturn(place);

        Place edited = service.alter(place, placeDTO);

        assertNotNull(edited);
        assertEquals(editedName, edited.getName());
        assertEquals(place.getSlug(), edited.getSlug());
        assertEquals(place.getCity(), edited.getCity());
        assertEquals(place.getCreatedAt(), edited.getCreatedAt());
        assertNotNull(edited.getUpdatedAt());
    }
}
