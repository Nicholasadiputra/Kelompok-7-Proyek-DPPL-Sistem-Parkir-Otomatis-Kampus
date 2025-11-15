package id.kampus.parkir.sistem_parkir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import id.kampus.parkir.sistem_parkir.entity.Kendaraan;

public interface KendaraanRepo extends JpaRepository<Kendaraan, Long> {
}

