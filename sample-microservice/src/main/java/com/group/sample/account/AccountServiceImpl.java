package com.group.sample.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.sample.entity.Test;
import com.group.sample.repository.TestRepository;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private TestRepository testRepository;
	@Override
	public List<Test> getAccounts() {
		
		return this.testRepository.findAll();
	}

	@Override
	public Test getAccount(String name) throws AccountNotFountException  {
			Test test= this.testRepository.findOne(name);
			
			if(test==null)
				throw new AccountNotFountException("Account Not Found");
			return test;
	}

	@Override
	public Test save(Test test) {
		return this.testRepository.save(test);
		
	}

}
