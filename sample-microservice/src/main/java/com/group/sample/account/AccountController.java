package com.group.sample.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.group.sample.entity.Test;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	
	
	@ExceptionHandler(value={ AccountNotFountException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void sendStatusOnObjectNotFount(){
		System.out.println("Object Not Found");
	}
	
	@ExceptionHandler(value={ DataIntegrityViolationException.class})
	@ResponseStatus(HttpStatus.CONFLICT)
	public void sendStatusOnDatabaseErroe(){
		System.out.println("Database Error");
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET, produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Test> getAccounts() {
		return this.accountService.getAccounts();
	}
	
	@RequestMapping(value="/accounts/{name}", method= RequestMethod.GET, produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Test getAccount(@PathVariable("name") String name) throws AccountNotFountException{
		return this.accountService.getAccount(name);
		
	}
	
	@RequestMapping(value="/accounts", method= RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Test test, HttpServletResponse response, HttpServletRequest request){
		Test test1= this.accountService.save(test);
		response.setHeader("location", this.getResourceLocation(request, test1.getName()));
	}

	private String getResourceLocation(HttpServletRequest request, String name) {
		return request.getRequestURI()+"/"+name;
	}

	@RequestMapping(value="/accounts/{name}", method= RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Test updateAccount(@PathVariable("name") String name, @RequestBody Test test) throws AccountNotFountException{
		//return this.accountService.update(name);
		return null;
	}
	
	
	
}
