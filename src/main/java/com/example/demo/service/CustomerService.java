package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.Customer;
import com.example.demo.entity.OrderDetail;

@Service
public class CustomerService implements BaseService<Customer> {
	@Autowired
	private BaseDao<Customer> dao;

	@Override
	public List<Customer> findAll() {
		return dao.findAll();
	}

	@Override
	public Customer findById(Integer id) throws DataNotFoundException {
		return dao.findById(id);
	}

	@Override
	public void save(Customer customer) {
		dao.save(customer);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<OrderDetail> findByOrderId(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
