package id.kampus.parkir.sistem_parkir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import id.kampus.parkir.sistem_parkir.entity.SlotParkir;

public interface SlotParkirRepo extends JpaRepository<SlotParkir, Long> {
    long countByTersedia(boolean tersedia);
}
