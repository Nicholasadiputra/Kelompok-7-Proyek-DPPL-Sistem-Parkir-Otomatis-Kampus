package id.kampus.parkir.sistem_parkir.controller;

import id.kampus.parkir.sistem_parkir.dto.RingkasanDTO;
import id.kampus.parkir.sistem_parkir.repository.PenggunaRepo;
import id.kampus.parkir.sistem_parkir.repository.KendaraanRepo;
import id.kampus.parkir.sistem_parkir.repository.SlotParkirRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ringkasan")
@RequiredArgsConstructor
public class RingkasanController {

    private final PenggunaRepo penggunaRepo;
    private final KendaraanRepo kendaraanRepo;
    private final SlotParkirRepo slotRepo;

    @GetMapping
    public RingkasanDTO getRingkasan() {

        long totalPengguna = penggunaRepo.count();
        long totalKendaraan = kendaraanRepo.count();
        long slotKosong = slotRepo.countByTersedia(true);

        return new RingkasanDTO(totalPengguna, totalKendaraan, slotKosong);
    }
}
