package com.backend.LaBonaMar.service;

import com.backend.LaBonaMar.entity.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConfirmationEmail(Reservation reservationDto, String managementUrl){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(reservationDto.getEmail());
        message.setSubject("La Bona Mar - Reservation confirmed");
        message.setText(
                "Hello " + reservationDto.getName() + ", \n" +
                        "Your reservation has been confirmed. \n" +
                        "Reservation details: \n" +
                        "Name: " + reservationDto.getName() + "\n" +
                        "Number Of person: " + reservationDto.getNumOfPerson() + "\n" +
                        "Date and Time: " + reservationDto.getDate() + "  " + reservationDto.getTime() + "\n" +
                        "You can reschedule or cancel your reservation using the link below \n " +
                        managementUrl + "\n" +
                        "Thank your for your reservation, we look forward welcoming you"
        );
        mailSender.send(message);
    }
    public void sendUpdatedEmail(Reservation reservationDto, String managementUrl){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(reservationDto.getEmail());
        message.setSubject("La Bona Mar - Reservation Updated");
        message.setText(
                "Hello " + reservationDto.getName() + ", \n" +
                        "Your reservation has been Updated. \n" +
                        "Reservation details: \n" +
                        "Name: " + reservationDto.getName() + "\n" +
                        "Number Of person: " + reservationDto.getNumOfPerson() + "\n" +
                        "Date and Time: " + reservationDto.getDate() + "  " + reservationDto.getTime() + "\n" +
                        "You can reschedule or cancel your reservation using the link below \n " +
                        managementUrl + "\n" +
                        "Thank your for your reservation, we look forward welcoming you"
        );
        mailSender.send(message);
    }
    }

}
