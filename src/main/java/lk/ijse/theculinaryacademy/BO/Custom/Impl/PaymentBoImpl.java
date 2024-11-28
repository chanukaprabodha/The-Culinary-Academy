package lk.ijse.theculinaryacademy.BO.Custom.Impl;

import lk.ijse.theculinaryacademy.BO.Custom.PaymentBo;
import lk.ijse.theculinaryacademy.DAO.Custom.PaymentDao;
import lk.ijse.theculinaryacademy.DAO.DaoFactory;
import lk.ijse.theculinaryacademy.DTO.PaymentDto;
import lk.ijse.theculinaryacademy.Entity.Payment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBo {
    PaymentDao paymentDao = (PaymentDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.PAYMENT);
    @Override
    public boolean savePayment(PaymentDto paymentDto) throws IOException {
        Payment payment = new Payment(
                paymentDto.getPay_id(),
                paymentDto.getPay_date(),
                paymentDto.getPay_amount(),
                paymentDto.getStatus(),
                paymentDto.getPay_amount(),
                paymentDto.getBalance_amount(),
                paymentDto.getStudent_course()
        );
        return paymentDao.save(payment);
    }

    @Override
    public List<Payment> getPaymentList() throws IOException {
        List<Payment> paymentList = new ArrayList<>();
        List<Payment> payments = paymentDao.getAll();
        for (Payment payment : payments) {
            paymentList.add(new Payment(
                    payment.getPay_id(),
                    payment.getPay_date(),
                    payment.getPay_amount(),
                    payment.getStatus(),
                    payment.getUpfront_amount(),
                    payment.getBalance_amount(),
                    payment.getStudent_course()
            ));
        }
        return paymentList;
    }
}
