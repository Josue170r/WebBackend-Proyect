package org.example.proyecto_productos.Pedido.service.impl;

import org.example.proyecto_productos.Carrito_Producto.models.CarritoProducto;
import org.example.proyecto_productos.Carrito_Producto.repository.CarritoProductoRepository;
import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Clientes.repository.ClienteRepository;
import org.example.proyecto_productos.Pedido.model.Pedido;
import org.example.proyecto_productos.Pedido.model.PedidoDetalle;
import org.example.proyecto_productos.Pedido.repository.PedidoDetalleRepository;
import org.example.proyecto_productos.Pedido.repository.PedidoRepository;
import org.example.proyecto_productos.Pedido.service.PedidoService;
import org.example.proyecto_productos.Pedido.tasks.PedidoEmailService;
import org.example.proyecto_productos.Productos.model.Productos;
import org.example.proyecto_productos.Productos.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    @Autowired
    private final ProductosRepository productoRepository;
    @Autowired
    private final PedidoDetalleRepository pedidoDetalleRepository;
    @Autowired
    private final CarritoProductoRepository carritoRepository;
    @Autowired
    private final PedidoEmailService emailService;

    Random random = new Random();

    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            ProductosRepository productoRepository,
            PedidoDetalleRepository pedidoDetalleRepository,
            CarritoProductoRepository carritoRepository,
            PedidoEmailService emailService
    ) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.pedidoDetalleRepository = pedidoDetalleRepository;
        this.carritoRepository = carritoRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public Pedido savePedido(Pedido pedido, Long idCliente) {
        Optional<Cliente> cliente = clienteRepository.findByIdCliente(idCliente);
        if (cliente.isPresent()) {
            Long idCarrito = cliente.get().getCarrito().getIdCarrito();
            pedido.setCliente(cliente.get());

            String folio = generateFolio();
            pedido.setNumeroFolio(folio);

            Pedido savedPedido = pedidoRepository.save(pedido);
            List<CarritoProducto> productos = carritoRepository.findByCarritoIdCarrito(idCarrito);
            generatePedidoDetalle(productos, savedPedido);
            carritoRepository.deleteAllByCarritoIdCarrito(idCarrito);
            return savedPedido;
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    @Override
    public Pedido getPedidoById(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public List<Pedido> getPedidosByClienteId(Long idCliente) {
        return pedidoRepository.findByClienteIdCliente(idCliente);
    }

    @Override
    public void deletePedido(Integer id) {
        pedidoRepository.deleteById(id);
    }

    public void generatePedidoDetalle(List<CarritoProducto> productos, Pedido pedido) {
        List<PedidoDetalle> productosByPedido = new ArrayList<>();
        for (CarritoProducto carritoProducto : productos) {
            PedidoDetalle pedidoDetalle = new PedidoDetalle();
            Productos producto = carritoProducto.getProducto();
            int cantidad = carritoProducto.getCantidad();
            pedidoDetalle.setPedido(pedido);
            pedidoDetalle.setCantidad(cantidad);
            pedidoDetalle.setProducto(producto);
            updateProductStock(producto, cantidad);
            pedidoDetalleRepository.save(pedidoDetalle);
            productosByPedido.add(pedidoDetalle);
        }
        pedido.setProductos(productosByPedido);
        emailService.send_email_pedido_confirmed(pedido);
    }

    public String generateFolio() {
        StringBuilder folioBuilder = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            int digit = random.nextInt(10); // Genera un número entre 0 y 9
            folioBuilder.append(digit);
        }
        return folioBuilder.toString();
    }

    public void updateProductStock(Productos producto, int cantidad) {
        int stock = producto.getStock();
        if (stock < cantidad) {
            throw new IllegalStateException("Stock insuficiente");
        }
        producto.setStock(stock - cantidad);
        productoRepository.save(producto);
    }
}
