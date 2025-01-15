package org.example.proyecto_productos.TokenValidator.controller;

import org.example.proyecto_productos.TokenValidator.models.ValidateTokenRequest;
import org.example.proyecto_productos.TokenValidator.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"*"})
public class VerificationTokenController {

    @Autowired
    private VerificationTokenService verificationTokenService;

    @GetMapping("/send-token")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> sendVerificationCode(@RequestParam("email") String email) {
        verificationTokenService.sendVerificationToken(email);
        return ResponseEntity.ok("Código de verificación enviado con éxito");
    }

    @PostMapping("/validate-token")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> validateVerificationCode(@RequestBody ValidateTokenRequest tokenRequest) {
        verificationTokenService.validateToken(tokenRequest);
        return ResponseEntity.ok("Cuenta verificada exitósamente");
    }

}
