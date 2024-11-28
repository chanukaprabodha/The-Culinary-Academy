package lk.ijse.theculinaryacademy.DAO.Custom;

import lk.ijse.theculinaryacademy.DAO.CrudDao;
import lk.ijse.theculinaryacademy.Entity.Payment;

import java.io.IOException;

public interface PaymentDao extends CrudDao<Payment> {
    String getCurrentId() throws IOException;

}
