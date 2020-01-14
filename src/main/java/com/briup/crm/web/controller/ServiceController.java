package com.briup.crm.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstService;
import com.briup.crm.bean.SysUser;
import com.briup.crm.service.ServeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private ServeService serveService;
	
	@RequestMapping("/findServiceByUserName/{curPage}")
	public String findServiceByUserName(@PathVariable int curPage,HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		PageInfo<CstService> serviceInfo = serveService.findServiceByUserName(curPage, 5, user.getUsrName());
		session.setAttribute("serviceInfo", serviceInfo);
		return "service/service";
	}
	
	@RequestMapping("/findServiceLike/{curPage}")
	public String findServiceLike(@PathVariable int curPage,String svrCustName,String svrType,HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		PageInfo<CstService> serviceInfo=serveService.findServiceLike(curPage, 5, svrCustName, svrType,user.getUsrName());
		session.setAttribute("serviceInfo", serviceInfo);
		return "service/serviceLike";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(CstService service) {
		serveService.saveOrUpdate(service);
		return "保存成功";
	}
	
	@RequestMapping("/findServiceById/{serviceId}")
	@ResponseBody
	public CstService findServiceById(@PathVariable long serviceId) {
		CstService service = serveService.findServiceById(serviceId);
		return service;	
	}
	
	@RequestMapping("/toServiceDetail/{serviceId}")
	public String toServiceDetail(@PathVariable long serviceId,HttpSession session) {
		CstService service = serveService.findServiceById(serviceId);
		session.setAttribute("service", service);
		return "service/serviceDetail";
	}
	
	@RequestMapping("/findAllService/{curPage}")
	public String findAllService(HttpSession session,@PathVariable int curPage) {
		PageInfo<CstService> serviceinfo = serveService.findAllService(curPage, 5);
		session.setAttribute("serviceinfo", serviceinfo);
		return "service/feedback";
	}
	
	@RequestMapping("/findServiceEqualTo/{curPage}")
	public String findServiceEqualTo(@PathVariable int curPage,String type,String status,HttpSession session) {
		PageInfo<CstService> serviceinfo = serveService.findServiceEqualTo(curPage, 5, type, status);
		session.setAttribute("serviceinfo", serviceinfo);
		return "service/feedback";
	}
	
	@RequestMapping("/toServiceDetail2/{serviceId}")
	public String toServiceDetail2(@PathVariable long serviceId,HttpSession session) {
		CstService service = serveService.findServiceById(serviceId);
		session.setAttribute("service", service);
		return "service/serviceDetail2";
	}
}
