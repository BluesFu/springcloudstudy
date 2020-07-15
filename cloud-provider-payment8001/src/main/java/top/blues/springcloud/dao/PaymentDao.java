package top.blues.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.blues.springcloud.entities.Payment;

/**
 * @author fsy
 */
@Mapper
public interface PaymentDao {
    /**创建payment
     * @param payment
     * @return int
     */
    public int create(Payment payment);

    /**查找payment
     * @param id
     * @return Payment
     */
    public Payment getPayment(@Param("id") Long id);
}
