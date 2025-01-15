package org.example.proyecto_productos.Pedido.tasks;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.proyecto_productos.Clientes.model.Cliente;
import org.example.proyecto_productos.Pedido.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class PedidoEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Enviar un correo de verificación de cuenta.
     *
     * @param pedido               Modelo del Pedido
     */

    public void send_email_pedido_confirmed(Pedido pedido) {
        try {
            Context context = new Context();
            context.setVariable("pedido", pedido);
            context.setVariable(
                "direccionString", getFullAddress(pedido.getCliente())
            );
            context.setVariable("cliente", pedido.getCliente());
            String html = templateEngine.process("pedidos/send_email_pedido_detail.html", context);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(pedido.getCliente().getEmail());
            helper.setSubject("Compra confirmada");
            helper.setText(html, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo electrónico", e);
        }
    }

    private String getFullAddress(Cliente cliente) {
        if (cliente == null) {
            return "Dirección no disponible";
        }
        String calle = cliente.getCalle() != null ? cliente.getCalle() : "";
        String numero = cliente.getNumero() != null ? cliente.getNumero() : "";
        String colonia = cliente.getColonia() != null ? cliente.getColonia() : "";
        String codigoPostal = cliente.getCodigoPostal() != null ? cliente.getCodigoPostal() : "";
        String direccion = calle + " " + numero;
        direccion = direccion.trim() + " - " + colonia;
        direccion = direccion.trim() + ", " + codigoPostal;
        return direccion;
    }
}
