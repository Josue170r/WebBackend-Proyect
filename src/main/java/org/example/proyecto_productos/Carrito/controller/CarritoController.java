package org.example.proyecto_productos.Carrito.controller;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Carrito.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"*"})
public class CarritoController {
    @Autowired
    private CarritoService service;

    @GetMapping("/carritos")
    @ResponseStatus(HttpStatus.OK)
    public List<Carrito> listarCarritos() {
        return service.redAllCarritos();
    }

    @GetMapping("/carritos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Carrito buscarCarritoPorId(@PathVariable Long id) {
        return service.readCarrito(id);
    }

    @PostMapping("/carritos")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito guardarCarrito(@RequestBody Carrito carrito) {
        return service.createCarrito(carrito);
    }

    @PutMapping("/carritos")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito actualizarCarrito(@RequestBody Carrito carrito) {
        return service.createCarrito(carrito);
    }

    @DeleteMapping("/carritos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCarrito(@PathVariable Long id) {
        service.deleteCarrito(id);
    }
}
