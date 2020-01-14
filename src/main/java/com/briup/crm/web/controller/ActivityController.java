package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstActivity;
import com.briup.crm.service.ActivityService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/findActivitiesByCustId/{custId}/{curPage}")
	public String findActivitiesByCustId(@PathVariable long custId, @PathVariable int curPage,HttpSession session) {
		PageInfo<CstActivity> activityInfo = activityService.findActivitiesByCustId(curPage, 5, custId);
		session.setAttribute("activityInfo", activityInfo);
		session.setAttribute("custId", custId);
		return "customer/activities";
	}
	
	@RequestMapping("/findActivityById/{atvId}")
	@ResponseBody
	public CstActivity findActivityById(@PathVariable long atvId) {
		CstActivity activity = activityService.findActivityById(atvId);
		return activity;
	}
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(CstActivity activity,HttpSession session) {
		
		long custId=(long)session.getAttribute("custId");
		activity.setAtvCustId(custId);
		activityService.saveOrUpdate(activity);
		return "forward:/activity/findActivitiesByCustId/"+custId+"/1";
	}
	
	@RequestMapping("/deleteActicityById/{atvId}")
	
	public String deleteActicityById(@PathVariable long atvId,HttpSession session) {
		long custId=(long)session.getAttribute("custId");
		
		activityService.deleteActicityById(atvId);
		
		return  "forward:/activity/findActivitiesByCustId/"+custId+"/1";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
