package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.OrderDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class OrderDetailService implements BaseService<OrderDetail> {
	@Autowired
	private BaseDao<OrderDetail> dao;

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<OrderDetail> findAll() {
		return dao.findAll();
	}

	@Override
	public List<OrderDetail> findByOrderId(Integer id) {
		TypedQuery<OrderDetail> query = entityManager.createQuery(
			"SELECT od FROM OrderDetail od WHERE od.order.id = :id", OrderDetail.class);
		query.setParameter("orderId", id);
		return query.getResultList();
	}

	@Override
	public OrderDetail findById(Integer id) throws DataNotFoundException {
		return dao.findById(id);
	}

	@Override
	public void save(OrderDetail orderDetail) {
		dao.save(orderDetail);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
