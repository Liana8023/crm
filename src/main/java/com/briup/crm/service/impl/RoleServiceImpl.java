package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.bean.CstCustomerExample;
import com.briup.crm.bean.SysRole;
import com.briup.crm.bean.SysRoleExample;
import com.briup.crm.dao.SysRoleMapper;
import com.briup.crm.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Override
	public PageInfo<SysRole> findAllRoles(int curPage, int size) {
		PageHelper.startPage(curPage,size);
		SysRoleExample example = new SysRoleExample();
		List<SysRole> rolelist = sysRoleMapper.selectByExample(example);
		PageInfo<SysRole> roleinfo = new PageInfo<SysRole>(rolelist);
		return roleinfo;
	}
	@Override
	public void saveOrUpdate(SysRole role) {
		if(role.getRoleId()==null) {
			sysRoleMapper.insertSelective(role);
		}else {
			sysRoleMapper.updateByPrimaryKey(role);
		}
		
		
	}
	@Override
	public SysRole findRoleById(long roleId) {
		SysRole role = sysRoleMapper.selectByPrimaryKey(roleId);
		return role;
		
	}
	@Override
	public void deleteRoleById(long roleId) {
		sysRoleMapper.deleteByPrimaryKey(roleId);
	}

}
