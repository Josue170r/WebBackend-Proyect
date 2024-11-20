package org.example.proyecto_productos.Productos.controller;

import lombok.RequiredArgsConstructor;
import org.example.proyecto_productos.Productos.model.Productos;
import org.example.proyecto_productos.Productos.service.ProductosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Endpoints disponibles:

POST /api/v1/productos - Crear producto
PUT /api/v1/productos/{id} - Actualizar producto
DELETE /api/v1/productos/{id} - Eliminar producto
GET /api/v1/productos/{id} - Obtener producto por ID
GET /api/v1/productos - Listar todos los productos
GET /api/v1/productos/proveedor/{idProveedor} - Productos por proveedor
GET /api/v1/productos/categoria/{idCategoria} - Productos por categoría
GET /api/v1/productos/bajo-stock/{stockMinimo} - Productos con stock bajo
PATCH /api/v1/productos/{id}/stock - Actualizar stock
 */

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class ProductosController {

    private final ProductosService productosService;

    @PostMapping
    public ResponseEntity<Productos> crearProducto(@RequestBody Productos producto) {
        return new ResponseEntity<>(productosService.guardarProducto(producto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable Long id, @RequestBody Productos producto) {
        return ResponseEntity.ok(productosService.actualizarProducto(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productosService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productosService.obtenerProductoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Productos>> listarProductos() {
        return ResponseEntity.ok(productosService.listarProductos());
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<Productos>> obtenerProductosPorProveedor(@PathVariable Long idProveedor) {
        return ResponseEntity.ok(productosService.obtenerProductosPorProveedor(idProveedor));
    }

//    @GetMapping("/categoria/{idCategoria}")
//    public ResponseEntity<List<Productos>> obtenerProductosPorCategoria(@PathVariable Long idCategoria) {
//        return ResponseEntity.ok(productosService.obtenerProductosPorCategoria(idCategoria));
//    }

    @GetMapping("/bajo-stock/{stockMinimo}")
    public ResponseEntity<List<Productos>> obtenerProductosConBajoStock(@PathVariable Integer stockMinimo) {
        return ResponseEntity.ok(productosService.obtenerProductosConBajoStock(stockMinimo));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> actualizarStock(
            @PathVariable Long id,
            @RequestParam Integer cantidad) {
        productosService.actualizarStock(id, cantidad);
        return ResponseEntity.ok().build();
    }
}
