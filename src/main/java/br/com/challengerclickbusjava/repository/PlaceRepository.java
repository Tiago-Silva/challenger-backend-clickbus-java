package br.com.challengerclickbusjava.repository;

import br.com.challengerclickbusjava.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByName(String name);
}
