package org.example.proyecto_productos.Carrito_Producto.controller;

import org.example.proyecto_productos.Carrito.models.Carrito;
import org.example.proyecto_productos.Carrito_Producto.models.CarritoProducto;
import org.example.proyecto_productos.Carrito_Producto.service.CarritoProductoService;
import org.example.proyecto_productos.Productos.model.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"*"})
public class CarritoProductoController {

    @Autowired
    private final CarritoProductoService service;

    public CarritoProductoController(CarritoProductoService carritoProductoService) {
        this.service = carritoProductoService;
    }

    @GetMapping("/cart/by-user/")
    public List<CarritoProducto> getAllByClient(@RequestParam("idCliente") Long idCliente) {
        return service.getCartProductsByClient(idCliente);
    }

    @PostMapping("/cart/add")
    public CarritoProducto addCarritoProducto(@RequestBody CarritoProducto carritoProducto) {
        return service.addToCart(carritoProducto);
    }

    @PostMapping("/cart/remove")
    public ResponseEntity<?> removeCarritoProducto(@RequestBody CarritoProducto carritoProducto) {
        service.removeFromCart(carritoProducto);
        return ResponseEntity.ok("Producto removido con éxito");
    }

    @PostMapping("/cart/update")
    public ResponseEntity<?> updateQuantity(@RequestBody CarritoProducto carritoProducto) {
        service.updateQuantity(carritoProducto);
        return ResponseEntity.ok("Cantidad actualizada");
    }
}
