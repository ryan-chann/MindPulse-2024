package edu.mingjun.mindpulse.service;

import edu.mingjun.mindpulse.model.AppointmentRequest;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import freemarker.template.TemplateExceptionHandler;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService{

    private JavaMailSender emailSender;
    private Configuration freemarkerConfig;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
        this.freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        this.freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/");
        this.freemarkerConfig.setDefaultEncoding("UTF-8");
        this.freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        this.freemarkerConfig.setLogTemplateExceptions(false);
        this.freemarkerConfig.setWrapUncheckedExceptions(true);
    }

    private Template getTemplate(String templateName) throws IOException {
        return freemarkerConfig.getTemplate(templateName);
    }

    public void sendBookingConfirmation(String to, AppointmentRequest appointmentRequest) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Booking Confirmed!");

        Template template = freemarkerConfig.getTemplate("booking-confirmation.ftl");

        Map<String, Object> model = new HashMap<>();
        model.put("customerName", appointmentRequest.getFullName());
        model.put("customerPhone", appointmentRequest.getPhoneNumber());
        model.put("customerEmail", appointmentRequest.getEmailAddress());
        model.put("customerNRIC", appointmentRequest.getNric());
        model.put("therapistName", appointmentRequest.getTherapistName());
        model.put("therapistType", appointmentRequest.getFormattedTherapistType());
        model.put("appointmentDate", appointmentRequest.getSelectedDate());
        model.put("appointmentTime", LocalTime.parse(appointmentRequest.getSelectedTime(), DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a")));
        model.put("appointmentMode", appointmentRequest.getFormattedModeNames());
        model.put("appointmentSession", appointmentRequest.getFormattedSessionNames());

        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);
        String htmlContent = stringWriter.toString();

        helper.setText(htmlContent, true);
        emailSender.send(message);
    }

}
