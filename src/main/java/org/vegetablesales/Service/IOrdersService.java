package org.vegetablesales.Service;

import java.time.LocalDate;
import java.util.List;

import org.vegetablesales.Model.Customer;
import org.vegetablesales.Model.Orders;

public interface IOrdersService {
	public Orders addOrder(Orders order);
	public Orders updateOrder(Orders order);
	public Orders cancelOrder(Integer orderId);
	public Orders viewOrder(Integer orderId);
	public List<Orders> viewOrderList();
	public List<Orders> viewAllOrders(Integer custId);
	public List<Orders> viewOrderBydate(LocalDate date);
}
