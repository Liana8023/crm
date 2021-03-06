package com.briup.crm.service;

import com.briup.crm.bean.SysUser;
import com.github.pagehelper.PageInfo;

public interface UserService {

	public SysUser login(String username,String password)throws Exception;
	
	public PageInfo<SysUser> findAllUsers(int curPage,int size);
	
	public void saveOrUpdate(SysUser user);
	
	public void deleteUserById(long usrId);
	
	public SysUser findUserById(long usrId);
	
	public PageInfo<SysUser> findUserLike(int curPage,int size, String usrRoleName);
}
