package com.briup.crm.service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.github.pagehelper.PageInfo;

public interface SaleChanceService {

	public PageInfo<SalChance> findSalChance(int curPage,int size);
	
	public PageInfo<SalChance> findSaleChanceLike(int curPage, int size, String custName, String region);

	public void saveOrUpdateChance(SalChance salChance);
	
	public SalChance findChanceById(long chanceId);
	
	public void deleteChanceById(long chanceId);
	
	public PageInfo<SalChance> findChanceByDueTo(String dueTo,int curPage,int size);
	
	public PageInfo<SalChance> findChanceByUserNameAndRegin(int curPage,int size,String dueTo,String region);

	public SalChanceExtend findChanceWithPlanById(long id);
}
