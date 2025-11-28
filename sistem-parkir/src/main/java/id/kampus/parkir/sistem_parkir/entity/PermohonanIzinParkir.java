package id.kampus.parkir.sistem_parkir.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class PermohonanIzinParkir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tanggal;

    private boolean statusAktif;

    @ManyToOne
    @JoinColumn(name = "pengguna_id")
    private Pengguna pengguna;
}
