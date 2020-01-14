package com.briup.crm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.Contribution;
import com.briup.crm.service.ConstituteService;
import com.briup.crm.service.CustomerService;

@Service
public class ConstituteServiceImpl implements ConstituteService{

	@Autowired
	private CustomerService customerService;
	@Override
	public List<Contribution> findCustMarkup(int condition) {
		int count = customerService.findAllCustomer().size();//获得所有顾客的人数
		List<Contribution> conlist = new ArrayList<Contribution>();
		
		if(condition==0) {//按等级
			Set<String> levels = customerService.findAllLevel();//获得所有等级
			for(String level:levels) {
				//获得各个等级对应的顾客数
				int size = customerService.getCustByLevel(level);
				//计算各个等级对应的百分比
				float percent = (float)size/count;
				Contribution con = new Contribution();
				con.setName(level);
				con.setY(percent);
				conlist.add(con);
			}
		}else if(condition==1) {//按信誉度
			Set<Integer> credits = customerService.findAllCredit();//获得所有信誉度
			for(Integer credit:credits) {
				//获得各个信誉度对应的顾客数
				int size = customerService.getCustByCredit(credit);
				//计算各个信誉度对应的百分比
				float percent = (float)size/count;
				Contribution con = new Contribution();
				con.setName(""+credit);
				con.setY(percent);
				conlist.add(con);
			}
		}else if(condition==2) {//按满意度
			Set<Integer> satisfies = customerService.findAllSatisfy();//获得所有满意度
			for(Integer satisfy:satisfies) {
				//获得各个满意度对应的顾客数
				int size = customerService.getCustBySatisfy(satisfy);
				//计算各个满意度对应的百分比
				float percent = (float)size/count;
				Contribution con = new Contribution();
				con.setName(""+satisfy);
				con.setY(percent);
				conlist.add(con);
			}
		}
		return conlist;
	}

	

}
