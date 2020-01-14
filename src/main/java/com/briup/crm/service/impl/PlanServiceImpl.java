package com.briup.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;
import com.briup.crm.dao.SalChanceMapper;
import com.briup.crm.dao.SalPlanMapper;
import com.briup.crm.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService{
	@Autowired
	private SalPlanMapper salPlanMapper;
	@Autowired
	private SalChanceMapper salChanceMapper;

	@Override
	public void savePlan(SalPlan plan,long chanceId) {
		salPlanMapper.insertSelective(plan);
		//根据chanceId查询对应的销售商机
		SalChance chance = salChanceMapper.selectByPrimaryKey(chanceId);
		chance.setChcStatus(2);
		salChanceMapper.updateByPrimaryKey(chance);
		
	}

	@Override
	public void saveOrUpdate(SalPlan plan) {
		if(plan.getPlaId()==null) {
			salPlanMapper.insertSelective(plan);
		}else {
			salPlanMapper.updateByPrimaryKey(plan);
		}
		
	}

	@Override
	public SalPlan findPlanById(long plaId) {
		SalPlan plan = salPlanMapper.selectByPrimaryKey(plaId);
		return plan;
	}

	@Override
	public void success(long chanceId) {
		SalChance chance = salChanceMapper.selectByPrimaryKey(chanceId);
		chance.setChcStatus(3);
		salChanceMapper.updateByPrimaryKey(chance);
	}

	@Override
	public void delete(long plaId) {
		salPlanMapper.deleteByPrimaryKey(plaId);
	}

}
