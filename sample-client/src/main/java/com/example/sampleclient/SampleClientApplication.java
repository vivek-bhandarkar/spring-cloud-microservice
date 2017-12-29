package com.example.sampleclient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableFeignClients
@EnableCircuitBreaker
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class SampleClientApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(SampleClientApplication.class, args);
	}
}

@FeignClient("sample-service")
interface SampleServiveReader{
	@RequestMapping(value="/api/tests/", method=RequestMethod.GET)
	Resources<Test> read();
}


class Test implements Serializable {


		private static final long serialVersionUID = 1L;

		@Override
		public String toString() {
			return "Test [Name=" + fullName + "]";
		}

	
		private String fullName;

		public String getFullName() {
			return fullName;
		}


		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		

}


@Controller
class ApiGAteway{
	
	@Autowired 
	private SampleServiveReader sampleServiceReader;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//@HystrixCommand(fallbackMethod="fallback")
	@RequestMapping(value="/gateway/accounts", method=RequestMethod.GET)
	public List<String> getAccounts(){
	 
		
		//ParameterizedTypeReference<Resources<Test>> ptr= new ParameterizedTypeReference<Resources<Test>>(){};
		
		//ResponseEntity<Resources<Test>> responseEntity=this.restTemplate.exchange("http://sample-service/api/accounts", HttpMethod.GET, null,ptr);
	
		/*return responseEntity
				.getBody()
				.getContent()
				.stream()
				.map(Test::getName)
				.collect(Collectors.toList());
	    */
		System.out.println(sampleServiceReader.read());
		return this.sampleServiceReader.read()
				.getContent()
				.stream()
				.map(Test::getFullName)
				.collect(Collectors.toList());
	}

	public List<String> fallback(){
		return null;
	}
}
