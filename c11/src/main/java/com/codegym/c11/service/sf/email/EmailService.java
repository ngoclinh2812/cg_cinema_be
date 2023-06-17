package com.codegym.c11.service.sf.email;

import com.codegym.c11.model.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class EmailService {


    private JavaMailSender javaMailSender;

    public Boolean sendTicketConfirmedEmail(Ticket ticket) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("minhnhien22122004@gmail.com");
            String email = ticket.getAccount().getEmail();
            message.setTo(email);

            String bodyText = createBodyText(ticket);
            message.setText(bodyText);

            String subjectText = "Giao dịch thành công";
            message.setSubject(subjectText);

            javaMailSender.send(message);
            System.out.println("Mail Sent...");
            return true; // Return true indicating successful email sending
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
            return false; // Return false indicating failed email sending
        }
    }

    private String createBodyText(Ticket ticket) {
        return "Thông tin vé:\t"
                + "Tên phim: " + ticket.getScheduleMovie().getMovie().getTitle() + " \t"
                + "Rạp: " + " \t"
                + "Phòng chiếu: " + ticket.getScheduleMovie().getRoom().getName() + " \t"
                + "Ngày chiếu: " + ticket.getScheduleMovie().getSchedule().getShowDate() + " \t"
                + "Giờ chiếu: " + ticket.getScheduleMovie().getSchedule().getShowTime() + " \t"
                + "Ghế: " + ticket.getSeat().getName() + " \t"
                + "Tổng cộng: " + " \t"
                + "Cảm ơn quý khách đã lựa chọn CG Cinema!";
    }

    public void sendAccountConfirmEmail(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("cg.cinema11@gmail.com");

            message.setTo(email);

            message.setText("Chúc mừng quý khách đã trở thành hội viên của CG Cinema.");

            message.setSubject("Đăng ký thành công");

            javaMailSender.send(message);
            System.out.println("Mail Sent...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}