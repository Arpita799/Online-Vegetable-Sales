package org.vegetablesales.Repository;






import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vegetablesales.Model.Customer;
import org.vegetablesales.Model.Orders;
@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer>{
	public List<Orders> findByOrderDate(LocalDate date);
	public List<Orders> findByCustomer_CustomerId(Integer customerId);
}
