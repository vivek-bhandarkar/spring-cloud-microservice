package com.group.sample.account;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.group.sample.entity.Test;

public interface AccountService {

	@Transactional(readOnly=true)
	public List<Test> getAccounts();
	
	@Transactional(readOnly = true)
	public Test getAccount(String name) throws AccountNotFountException;

	@Transactional
	public Test save(Test test);

}
