package com.magic30minutes.microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.magic30minutes.microservices.pojo.ExchangeValue;
import com.magic30minutes.microservices.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeVale(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from,to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("{}",exchangeValue);
		
		return exchangeValue;
		
	}
}
