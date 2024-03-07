package com.example.demo.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.FlashData;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.BaseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/orderdetails")
public class OrderDetailsController {
	@Autowired
	BaseService<OrderDetail> orderdetailService;

	/*
	 * 新規作成画面表示
	 */
	@GetMapping(value = "/create")
	public String form(OrderDetail orderdetail, Model model) {
		model.addAttribute("orderdetail", orderdetail);
		return "admin/orderdetails/create";
	}

	/*
	 * 新規登録
	 */
	@PostMapping(value = "/create")
	public String register(@Valid OrderDetail orderdetail, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if (result.hasErrors()) {
				return "admin/orderdetails/create";
			}
			// 新規登録
			orderdetailService.save(orderdetail);
			flash = new FlashData().success("新規作成しました");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/orders/view/{id}";
	}

	/*
	 * 受注表示
	 */
	@GetMapping(value = "/edit/{id}")
	public String list(@PathVariable Integer id, @Valid OrderDetail orderdetail, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			// 新規登録
			orderdetailService.save(orderdetail);
			flash = new FlashData().success("新規作成しました");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/orders/view/{id}";
	}

	/*
	 * 更新
	 */
	@PostMapping(value = "/edit/{id}")
	public String update(@PathVariable Integer id, @Valid OrderDetail orderdetail, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if (result.hasErrors()) {
				return "admin/orderdetails/edit";
			}
			orderdetailService.findById(id);
			// 更新
			orderdetailService.save(orderdetail);
			flash = new FlashData().success("更新しました");
		} catch (Exception e) {
			flash = new FlashData().danger("該当データがありません");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/orders/view/{id}";
	}
}
