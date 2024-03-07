package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.common.FlashData;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;

@Service
public class OrderService implements BaseService<Order> {
	@Autowired
	private BaseDao<Order> dao;

	@Autowired
	BaseService<OrderDetail> orderdetailService;

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public Order findById(Integer id) throws DataNotFoundException {
		return dao.findById(id);
	}

	@Override
	public void save(Order order) {
		dao.save(order);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

    // getOrderDetails メソッドの実装
    public List<OrderDetail> getOrderDetails(Integer id) {
    	FlashData flash;
        try {
            // 注文が存在するか確認
            Order order = dao.findById(id);

            // 注文に関連づけられた注文詳細を取得
            return orderdetailService.findByOrderId(id);
        } catch (DataNotFoundException e) {
        	flash = new FlashData().danger("該当データがありません");
        }
		return null;
    }

	public List<OrderDetail> findByOrderId(Integer id) {
		return null;
	}
}
