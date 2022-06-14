package com.mono.api.abo;

import com.mono.api.abo.access.Abo;
import com.mono.api.abo.internal.AboManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/abo")
public class AboController {

    private final AboManagement aboManagement;

    @Autowired
    public AboController(AboManagement aboManagement) {
        this.aboManagement = aboManagement;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Abo>> getAllAbos() {
        return ResponseEntity.ok(aboManagement.getAllAbos());
    }

    @PostMapping(path = "/insert/{productNr}")
    public ResponseEntity<Void> addAbo(@PathVariable Integer productNr) {
        aboManagement.addAbo(productNr);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{aboId}")
    public ResponseEntity<Void> cancelAbo(@PathVariable Integer aboId) {
        aboManagement.deleteAbo(aboId);
        return ResponseEntity.ok().build();
    }
}
