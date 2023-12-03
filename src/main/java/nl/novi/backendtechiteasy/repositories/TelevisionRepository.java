package nl.novi.backendtechiteasy.repositories;

import nl.novi.backendtechiteasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
    @Query("SELECT t FROM Television t WHERE " +
            "(:brand IS NULL OR t.brand = :brand) AND " +
            "(:type IS NULL OR t.type = :type) AND " +
            "(:wifi IS NULL OR t.wifi = :wifi) AND " +
            "(:voiceControl IS NULL OR t.voiceControl = :voiceControl) AND " +
            "(:hdr IS NULL OR t.hdr = :hdr) AND " +
            "(:bluetooth IS NULL OR t.bluetooth = :bluetooth) AND " +
            "(:ambiLight IS NULL OR t.ambiLight = :ambiLight)")
    List<Television> findAllByFilters(
            String brand,
            String type,
            Boolean wifi,
            Boolean voiceControl,
            Boolean hdr,
            Boolean bluetooth,
            Boolean ambiLight
    );
}
