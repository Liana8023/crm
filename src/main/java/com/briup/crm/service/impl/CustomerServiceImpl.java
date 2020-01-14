package com.briup.crm.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.bean.CstCustomerExample;
import com.briup.crm.dao.CstCustomerMapper;
import com.briup.crm.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CstCustomerMapper customerMapper;
	
	@Override
	public List<CstCustomer> findAllCustomer() {
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		return customerlist;
	}

	@Override
	public PageInfo<CstCustomer> findAllCustomerByPage(int curPage, int size) {
		//设置当前是哪一页，以及每页显示几条数据
		PageHelper.startPage(curPage,size);
		//查询所有顾客信息
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		//将顾客信息传递给分页对象
		PageInfo<CstCustomer> customerinfo = new PageInfo<CstCustomer>(customerlist);
		return customerinfo;
	}



	@Override
	public void deleteCustomerById(long custId) {
		// TODO Auto-generated method stub
		customerMapper.deleteByPrimaryKey(custId);
	}

	@Override
	public CstCustomer findCustomerById(long custId) {
		// TODO Auto-generated method stub
		CstCustomer customer = customerMapper.selectByPrimaryKey(custId);
		return customer;
	}

	@Override
	public void saveOrUpdateCustomer(CstCustomer customer) {
		// TODO Auto-generated method stub
		//判断id是否为空，如果为空，则是保存操作，否则更新
		if(customer.getCustId()==null) {
			customerMapper.insertSelective(customer);
		}else {
			customerMapper.updateByPrimaryKey(customer);
		}
	}

	@Override
	public PageInfo<CstCustomer> findCustomerLike(int curPage, int size, CstCustomer customer) {
		// TODO Auto-generated method stub
		PageHelper.startPage(curPage,size);//设置当前页，每页显示多少条数据
		//根据客户姓名、所在区域、客户等级进行模糊查询
		CstCustomerExample example=new CstCustomerExample();
		example.createCriteria().andCustNameLike("%"+customer.getCustName()+"%").andCustRegionLike("%"+customer.getCustRegion()+"%")
		.andCustLevelLabelLike("%"+customer.getCustLevelLabel()+"%");
		List<CstCustomer> customerlist = customerMapper.selectByExample(example);
		
		//把查询出来的结果放到pageInfo里面
		PageInfo<CstCustomer> customerinfo= new PageInfo<CstCustomer>(customerlist);
		return customerinfo;
	}

	//获取总的消费金额
	@Override
	public float getTotal() {
		float total=0;
		//查询所有顾客，然后获取每个顾客的消费金额
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> custlist = customerMapper.selectByExample(example);//查询所有顾客
		
		for(CstCustomer cust:custlist) {
			total +=cust.getCustTurnover();
		}
		
		return total;
	}
	
	//获取每个区域的消费金额
	@Override
	public float getRegionTotal(String region) {
		
		float regionTotal=0;
		//1.查询每个区域所有的顾客
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustRegionEqualTo(region);
		List<CstCustomer> regionCustList = customerMapper.selectByExample(example);
		//2.求总的消费金额
		for(CstCustomer cust:regionCustList) {
			regionTotal+=cust.getCustTurnover();
		}
	
		return regionTotal;
	}
	
	//获取每个区域的百分比
	@Override
	public float getRegionPercent(String region) {
		float percent = getRegionTotal(region)/getTotal();
		return percent;
	}

	//查询不同等级的顾客的人数
	@Override
	public int getCustByLevel(String level) {
	
		
		//1.查询出level对应的所有顾客
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustLevelLabelEqualTo(level);
		List<CstCustomer> custlist = customerMapper.selectByExample(example);

		return custlist.size();
	}

	//查询不同信誉度的顾客的人数
	@Override
	public int getCustByCredit(int credit) {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustCreditEqualTo(credit);
		List<CstCustomer> custlist = customerMapper.selectByExample(example);

		return custlist.size();
	}

	//查询不同满意度的顾客的人数
	@Override
	public int getCustBySatisfy(int satisfy) {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustSatisfyEqualTo(satisfy);
		List<CstCustomer> custlist = customerMapper.selectByExample(example);
		return custlist.size();
	}

	
	@Override
	public Set<String> findAllLevel() {
		Set<String> levelSet = new HashSet<String>();
		//1.查出所有顾客
		List<CstCustomer> custlist = findAllCustomer();
		//2.获取到顾客的等级
		for(CstCustomer cust:custlist) {
			String level = cust.getCustLevelLabel();
			levelSet.add(level);
		}
		return levelSet;
	}

	@Override
	public Set<Integer> findAllCredit() {
		Set<Integer> creditSet = new HashSet<Integer>();
		//1.查出所有顾客
		List<CstCustomer> custlist = findAllCustomer();
		//2.获取到顾客的信誉度
		for(CstCustomer cust:custlist) {
			int credit = cust.getCustCredit();
			creditSet.add(credit);
		}
		return creditSet;
	}

	@Override
	public Set<Integer> findAllSatisfy() {
		Set<Integer> satisfySet = new HashSet<Integer>();
		//1.查出所有顾客
		List<CstCustomer> custlist = findAllCustomer();
		//2.获取到顾客的满意度
		for(CstCustomer cust:custlist) {
			int satisfy = cust.getCustSatisfy();
			satisfySet.add(satisfy);
		}
		return satisfySet;
	}




	
}
