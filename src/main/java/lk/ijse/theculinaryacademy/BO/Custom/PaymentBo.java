package lk.ijse.theculinaryacademy.BO.Custom;

import lk.ijse.theculinaryacademy.BO.SuperBo;
import lk.ijse.theculinaryacademy.DTO.PaymentDto;
import lk.ijse.theculinaryacademy.Entity.Payment;

import java.io.IOException;
import java.util.List;

public interface PaymentBo extends SuperBo {
    boolean savePayment(PaymentDto paymentDto) throws IOException;
    List<Payment> getPaymentList() throws IOException;
}
