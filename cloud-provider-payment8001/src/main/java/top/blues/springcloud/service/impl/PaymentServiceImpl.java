package top.blues.springcloud.service.impl;

import org.springframework.stereotype.Service;
import top.blues.springcloud.dao.PaymentDao;
import top.blues.springcloud.entities.Payment;
import top.blues.springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author fsy
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentDao.getPayment(id);
    }
}
