package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalChanceExample;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.briup.crm.dao.SalChanceMapper;
import com.briup.crm.dao.extend.SalChanceExtendMapper;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SaleChanceServiceImpl implements SaleChanceService{
	
	@Autowired
	private SalChanceMapper salChanceMapper;
	@Autowired
	private SalChanceExtendMapper salChanceExtendMapper;
	
	@Override
	public PageInfo<SalChance> findSalChance(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		
		SalChanceExample example = new SalChanceExample();
		List<SalChance> chanceList = salChanceMapper.selectByExample(example);
		
		PageInfo<SalChance> chanceInfo = new PageInfo<SalChance>(chanceList);
		return chanceInfo;
	}

	@Override
	public PageInfo<SalChance> findSaleChanceLike(int curPage, int size, String custName, String region) {
		PageHelper.startPage(curPage,size);
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcCustNameLike("%"+custName+"%").andChcAddrLike("%"+region+"%");
		List<SalChance> chancelist = salChanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceInfo = new PageInfo<SalChance>(chancelist);
		return chanceInfo;
	}

	@Override
	public void saveOrUpdateChance(SalChance salChance) {
		if(salChance.getChcId()==null) {
			salChanceMapper.insertSelective(salChance);
		}else {
			salChanceMapper.updateByPrimaryKey(salChance);
		}
	}

	@Override
	public SalChance findChanceById(long chanceId) {
		SalChance salChance = salChanceMapper.selectByPrimaryKey(chanceId);
		return salChance;
	}

	@Override
	public void deleteChanceById(long chanceId) {
		salChanceMapper.deleteByPrimaryKey(chanceId);
	}

	@Override
	public PageInfo<SalChance> findChanceByDueTo(String dueTo,int curPage,int size) {
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcDueToEqualTo(dueTo);
		List<SalChance> chancelist = salChanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceInfo = new PageInfo<SalChance>(chancelist);
		return chanceInfo;
	}

	@Override
	public PageInfo<SalChance> findChanceByUserNameAndRegin(int curPage, int size, String dueTo, String region) {
		
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcDueToEqualTo(dueTo).andChcAddrEqualTo(region);
		List<SalChance> chancelist = salChanceMapper.selectByExample(example);
		PageInfo<SalChance> chanceInfo = new PageInfo<SalChance>(chancelist);
		return chanceInfo;
	}

	@Override
	public SalChanceExtend findChanceWithPlanById(long id) {
		// TODO Auto-generated method stub
		SalChanceExtend salChanceExtend = salChanceExtendMapper.selectChanceWithPlanById(id);
		return salChanceExtend;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
