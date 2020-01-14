package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;
import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.briup.crm.service.PlanService;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/plan")
public class PlansController {

	@Autowired
	private SaleChanceService chanceService;
	@Autowired
	private PlanService planService;
	
	@RequestMapping("/findPlansByDueTo/{curPage}")
	public String findPlansByDueTo(@PathVariable int curPage,HttpSession session) {
		SysUser user =(SysUser) session.getAttribute("user");//在userController中，登录时在session中存入了“user”，用户的信息，这里取user，然后得到user的name
		PageInfo<SalChance> chanceInfo = chanceService.findChanceByDueTo(user.getUsrName(), curPage, 5);
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/plan";
	}
	
	@RequestMapping("/findChanceByUserNameAndRegin/{curPage}")
	public String findChanceByUserNameAndRegin(@PathVariable int curPage,String region,HttpSession session) {
		SysUser user =(SysUser) session.getAttribute("user");
		PageInfo<SalChance> chanceInfo = chanceService.findChanceByUserNameAndRegin(curPage, 5, user.getUsrName(), region);
		session.setAttribute("chanceInfo", chanceInfo);
		return "sales/plan";
	}
	
	@RequestMapping("/toPlanAdd/{chanceId}")
	public String toPlanAdd(@PathVariable long chanceId,HttpSession session) {
		SalChance chance = chanceService.findChanceById(chanceId);
		session.setAttribute("chance", chance);
		session.setAttribute("chanceId", chanceId);
		return "sales/plan_add";
	}
	
	@RequestMapping("/addPlan")
	public String addPlan(SalPlan plan,HttpSession session) {
		long chanceId =(long) session.getAttribute("chanceId");
		planService.savePlan(plan, chanceId);
		return "forward:/plan/findPlansByDueTo/1";
	}
	
	@RequestMapping("/toPlanEdit/{chanceId}")
	public String toPlanEdit(@PathVariable long chanceId,HttpSession session) {
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanById(chanceId);
		session.setAttribute("chanceId", chanceId);
		session.setAttribute("chance", chanceExtend);
		return "sales/plan_edit";
	}
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(SalPlan plan,HttpSession session) {
		long chanceId=(long)session.getAttribute("chanceId");
		plan.setPlaChcId(chanceId);
		planService.saveOrUpdate(plan);
		
		return "forward:/plan/toPlanEdit/"+chanceId;
	}
	
	@RequestMapping("/findPlanById/{plaId}")
	@ResponseBody
	public SalPlan findPlanById(@PathVariable long plaId) {
		SalPlan plan = planService.findPlanById(plaId);
		return plan;
	}
	
	@RequestMapping("/success")
	public String success(HttpSession session) {
		long chanceId=(long)session.getAttribute("chanceId");
		planService.success(chanceId);
		return "forward:/plan/findPlansByDueTo/1";
	}
	
	@RequestMapping("/deletePlanById/{plaId}")
	public String deletePlanById(@PathVariable long plaId,HttpSession session) {
		long chanceId=(long)session.getAttribute("chanceId");
		planService.delete(plaId);
		return "forward:/plan/toPlanEdit/"+chanceId;
	}
	
	@RequestMapping("/toPlanDetail/{chanceId}")
	public String toPlanDetail(@PathVariable long chanceId,HttpSession session) {
		SalChanceExtend chanceExtend = chanceService.findChanceWithPlanById(chanceId);
		session.setAttribute("chance", chanceExtend);
		return "sales/plan_detail";
	}



	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
