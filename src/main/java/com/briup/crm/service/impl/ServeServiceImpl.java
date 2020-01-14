package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstService;
import com.briup.crm.bean.CstServiceExample;
import com.briup.crm.dao.CstServiceMapper;
import com.briup.crm.service.ServeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ServeServiceImpl implements ServeService{
	
	@Autowired
	private CstServiceMapper cstServiceMapper;

	@Override
	public PageInfo<CstService> findServiceByUserName(int curPage, int size, String username) {
				PageHelper.startPage(curPage,size);
				
				CstServiceExample example = new CstServiceExample();
				example.createCriteria().andSvrDisposeEqualTo(username);
				List<CstService> servicelist= cstServiceMapper.selectByExample(example);
				PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
				return serviceinfo;
		
	}

	@Override
	public PageInfo<CstService> findServiceLike(int curPage, int size, String svrCustName, String svrType,String username) {
		PageHelper.startPage(curPage, size);
		CstServiceExample example = new CstServiceExample();
		example.createCriteria().andSvrCustNameLike("%"+svrCustName+"%").andSvrTypeLike("%"+svrType+"%").andSvrDisposeEqualTo(username);
		List<CstService> servicelist = cstServiceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}

	@Override
	public void saveOrUpdate(CstService service) {
		if (service.getSvrId() == null) {
			cstServiceMapper.insertSelective(service);
		} else {
			cstServiceMapper.updateByPrimaryKeySelective(service);
		}
		
	}

	@Override
	public CstService findServiceById(long serviceId) {
		CstService service = cstServiceMapper.selectByPrimaryKey(serviceId);
		return service;
		
	}

	@Override
	public PageInfo<CstService> findAllService(int curPage,int size) {
		PageHelper.startPage(curPage, size);
		CstServiceExample example = new CstServiceExample();
		List<CstService> servicelist = cstServiceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}

	@Override
	public PageInfo<CstService> findServiceEqualTo(int curPage, int size, String type, String status) {
		CstServiceExample example = new CstServiceExample();
		example.createCriteria().andSvrTypeEqualTo(type).andSvrStatusEqualTo(status);
		List<CstService> servicelist = cstServiceMapper.selectByExample(example);
		PageInfo<CstService> serviceinfo = new PageInfo<CstService>(servicelist);
		return serviceinfo;
	}
}
