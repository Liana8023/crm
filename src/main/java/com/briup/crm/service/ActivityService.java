package com.briup.crm.service;

import com.briup.crm.bean.CstActivity;
import com.github.pagehelper.PageInfo;

public interface ActivityService {

	public PageInfo<CstActivity> findActivitiesByCustId(int curPage,int size, long custId);
	
	public CstActivity findActivityById(long atvId);
	
	public void saveOrUpdate(CstActivity activity);
	
	public void deleteActicityById(long atvId);
}
