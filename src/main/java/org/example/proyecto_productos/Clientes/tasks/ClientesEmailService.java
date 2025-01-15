package org.example.proyecto_productos.Clientes.tasks;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.proyecto_productos.Clientes.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class ClientesEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Enviar un correo de verificación de cuenta.
     *
     * @param cliente               Modelo del Cliente
     * @param verification_code     Código de verificación
     */

    public void send_verification_email(Cliente cliente, String verification_code) {
        try {
            Context context = new Context();
            context.setVariable("username", cliente.getUserName());
            context.setVariable("verification_code", verification_code);
            String html = templateEngine.process("clientes/send_email_verification.html", context);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(cliente.getEmail());
            helper.setSubject("Verifica tu cuenta");
            helper.setText(html, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo electrónico", e);
        }
    }
}
