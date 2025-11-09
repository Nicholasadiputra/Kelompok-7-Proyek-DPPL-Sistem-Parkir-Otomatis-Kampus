package id.kampus.parkir.sistem_parkir.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KendaraanController {

    @GetMapping("/kendaraan/test")
    public String test(){
        return "kendaraan ok";
    }
}