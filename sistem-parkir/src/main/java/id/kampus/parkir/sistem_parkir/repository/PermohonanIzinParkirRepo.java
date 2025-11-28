package id.kampus.parkir.sistem_parkir.repository;

import id.kampus.parkir.sistem_parkir.entity.PermohonanIzinParkir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermohonanIzinParkirRepo extends JpaRepository<PermohonanIzinParkir, Long> {
}
