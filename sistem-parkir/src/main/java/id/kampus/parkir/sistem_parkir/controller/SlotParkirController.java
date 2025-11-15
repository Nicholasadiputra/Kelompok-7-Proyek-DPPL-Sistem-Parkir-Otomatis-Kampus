package id.kampus.parkir.sistem_parkir.controller;

import id.kampus.parkir.sistem_parkir.entity.SlotParkir;
import id.kampus.parkir.sistem_parkir.repository.SlotParkirRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slot")
@RequiredArgsConstructor
public class SlotParkirController {

    private final SlotParkirRepo repo;

    @GetMapping
    public List<SlotParkir> getAll() { return repo.findAll(); }

    @PostMapping
    public SlotParkir create(@RequestBody SlotParkir slot) {
        return repo.save(slot);
    }

    @PutMapping("/{id}")
    public SlotParkir update(@PathVariable Long id, @RequestBody SlotParkir s) {
        SlotParkir slot = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot tidak ditemukan"));

        slot.setTersedia(s.isTersedia());
        return repo.save(slot);
    }
}
