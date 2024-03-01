package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class CurrencyExchangeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	private Environment environment;
	
	private CurrencyExchangeRepository currencyExchangeRepository;

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		LOGGER.info("[IN]CurrencyExchangeController - retrieveExchangeValue - from: {} - to: {}", from, to);
//		CurrencyExchange currencyExchange = new CurrencyExchange(100L, from, to, BigDecimal.valueOf(50));
		String port = environment.getProperty("local.server.port");
//		List<CurrencyExchange> currencyExchanges = currencyExchangeRepository.findAll();
//		currencyExchange = currencyExchanges.stream()
//				.filter(ce -> ce.getFrom().equals(from) && ce.getTo().equals(to))
//				.findFirst().get();
		CurrencyExchange currencyExchange = currencyExchangeRepository
				.findByFromAndTo(from, to).orElseThrow(() -> new RuntimeException("Unable to find data for " + from + " " + to));
		currencyExchange.setEnvirnoment(port);
		return currencyExchange;
	}
	

}
