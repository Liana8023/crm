package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.SalChance;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/chance")
public class SaleChanceController {
	@Autowired
	private SaleChanceService chanceService;

	@RequestMapping("/findSaleChance/{curPage}")
	public String findSaleChance(@PathVariable int curPage,HttpSession session) {
		PageInfo<SalChance> chanceInfo = chanceService.findSalChance(curPage, 5);
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/sales";
	}
	
	@RequestMapping("/findSaleChanceLike/{curPage}")
	public String findSaleChanceLike(@PathVariable int curPage, String custName, String region,HttpSession session) {
		PageInfo<SalChance> chanceInfo = chanceService.findSaleChanceLike(curPage, 5, custName, region);
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/sales";
	}
	
	@RequestMapping("/saveOrUpdateChance")
	@ResponseBody
	public String saveOrUpdateChance(SalChance salChance) {
		chanceService.saveOrUpdateChance(salChance);
		return "保存成功";
	}
	
	@RequestMapping("/findChanceById/{chanceId}")
	@ResponseBody
	public SalChance findChanceById(@PathVariable long chanceId) {
		SalChance salChance = chanceService.findChanceById(chanceId);
		return salChance;
	}
	
	@RequestMapping("/deleteChanceById/{chanceId}")
	@ResponseBody
	public String deleteChanceById(@PathVariable long chanceId) {
		chanceService.deleteChanceById(chanceId);
		return "删除成功";
	}
	
	
	
}
