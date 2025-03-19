package com.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iss.service.OllamaService;

@RestController
public class OllamaAiController {
	@Autowired
	private OllamaService service;
	
	@GetMapping("/")
	public String callAi(@RequestParam String userId,@RequestParam String query)
	{
		return service.callAi(userId,query);
	}
}
