package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstLinkman;
import com.briup.crm.service.LinkmanService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/linkman")
public class LinkmanController {

	@Autowired
	private LinkmanService linkmanService;
	
	@RequestMapping("/findLinkmanByCustId/{custId}/{curPage}")
	public String findLinkmanByCustId(@PathVariable long custId,@PathVariable int curPage, HttpSession session) {
		PageInfo<CstLinkman> linkmaninfo = linkmanService.findLinkmanByCustId(custId, curPage, 5);
		session.setAttribute("linkmaninfo", linkmaninfo);
		session.setAttribute("custId", custId);//分页显示时，如果不拿custId就会分页显示所有的联系人，如果拿custId就会分页显示根据custId查找到的联系人
		
		return "customer/linkman";
	}
	
	@RequestMapping("/deleteLinkmanById/{lkmId}")
	public String deleteLinkmanById(@PathVariable long lkmId,HttpSession session) {
		linkmanService.deleteLinkmanById(lkmId);
		Long custId = (Long)session.getAttribute("custId");
		return "forward:/linkman/findLinkmanByCustId/"+custId+"/1";
	}
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(CstLinkman lkm, HttpSession session) {
		Long custId = (Long)session.getAttribute("custId");//从session从获取添加的linkman对应的顾客的id
		//因为点击新增的时候，不会让你填写此联系人对应的custId，必须要自己从session中获取到，然后添加到linkman对象中
		lkm.setLkmCustId(custId);
		linkmanService.saveOrUpdate(lkm);//保存
		//保存完后，要根据获取到的custId去查询联系人，即先跳转到findLinkmanByCustId，查找出来后，显示对应联系人
		return "forward:/linkman/findLinkmanByCustId/"+custId+"/1";
	}
	
	@RequestMapping("/findLinkmanById/{lkmId}")
	@ResponseBody
	public CstLinkman findLinkmanById(@PathVariable long lkmId) {
		CstLinkman lkm = linkmanService.findLinkmanById(lkmId);
		return lkm;
	}
	
	
/*	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(CstLinkman lkm,HttpSession session) {
		Long custId = (Long)session.getAttribute("custId");
		lkm.setLkmCustId(custId);
		linkmanService.saveOrUpdate(lkm);
		return"forward:/linkman/findLinkmanByCustId/"+custId+"/1";
	}
	
	@RequestMapping("/findLinkmanById/{lkmId}")
	@ResponseBody
	public CstLinkman findLinkmanById(@PathVariable long lkmId) {
		CstLinkman lkm = linkmanService.findLinkmanById(lkmId);
		return lkm;
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
