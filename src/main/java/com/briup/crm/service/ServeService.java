package com.briup.crm.service;


import com.briup.crm.bean.CstService;
import com.github.pagehelper.PageInfo;

public interface ServeService {

	public PageInfo<CstService> findServiceByUserName(int curPage, int size, String username);
	
	public PageInfo<CstService> findServiceLike(int curPage,int size,String svrCustName,String username,String svrType);
	
	public void saveOrUpdate(CstService service);
	
	public CstService findServiceById(long serviceId);
	
	public PageInfo<CstService> findAllService(int curPage,int size);
	
	public PageInfo<CstService> findServiceEqualTo(int curPage,int size,String type,String status);
}
