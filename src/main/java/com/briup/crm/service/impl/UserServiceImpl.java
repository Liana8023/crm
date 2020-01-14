package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SysRole;
import com.briup.crm.bean.SysUser;
import com.briup.crm.bean.SysUserExample;
import com.briup.crm.dao.SysRoleMapper;
import com.briup.crm.dao.SysUserMapper;
import com.briup.crm.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public SysUser login(String name, String password) throws Exception {
		//根据用户名查询数据库中是否有对应的用户的信息
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsrNameEqualTo(name);
		List<SysUser> userlist=userMapper.selectByExample(example);
		
		if(userlist.size()>0) {
			SysUser user = userlist.get(0);
			if(user.getUsrPassword().equals(password)) {
				return user;
			}else {
				throw new Exception("密码输入错误，请重新输入");
			}
		}else {
			throw new Exception("用户名输入错误，请重新输入");
		}
		
	}

	@Override
	public PageInfo<SysUser> findAllUsers(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		SysUserExample example = new SysUserExample();
		List<SysUser> userlist = userMapper.selectByExample(example);
		PageInfo<SysUser> userInfo = new PageInfo<SysUser>(userlist);
		return userInfo;
	}

	@Override
	public void saveOrUpdate(SysUser user) {
		SysRole role = sysRoleMapper.selectByPrimaryKey(user.getUsrRoleId());
		user.setUsrRoleName(role.getRoleName());
		if(user.getUsrId()==null) {
			userMapper.insertSelective(user);
		}else {
			userMapper.updateByPrimaryKeySelective(user);
		}
	}

	@Override
	public void deleteUserById(long usrId) {
		userMapper.deleteByPrimaryKey(usrId);
	}

	@Override
	public SysUser findUserById(long usrId) {
		SysUser user = userMapper.selectByPrimaryKey(usrId);
		return user;
	}

	@Override
	public PageInfo<SysUser> findUserLike(int curPage, int size, String usrRoleName) {
		PageHelper.startPage(curPage, size);
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsrRoleNameEqualTo(usrRoleName);
		List<SysUser> userlist = userMapper.selectByExample(example);
		PageInfo<SysUser> usrinfo = new PageInfo<SysUser>(userlist);
		return usrinfo;
	}
}
