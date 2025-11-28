package id.kampus.parkir.sistem_parkir.service;

import id.kampus.parkir.sistem_parkir.entity.PermohonanIzinParkir;
import id.kampus.parkir.sistem_parkir.repository.PermohonanIzinParkirRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermohonanIzinParkirService {

    private final PermohonanIzinParkirRepo repo;

    public PermohonanIzinParkirService(PermohonanIzinParkirRepo repo) {
        this.repo = repo;
    }

    public List<PermohonanIzinParkir> findAll() {
        return repo.findAll();
    }

    public PermohonanIzinParkir save(PermohonanIzinParkir p) {
        return repo.save(p);
    }
}
