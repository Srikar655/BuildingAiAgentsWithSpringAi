package com.iss.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Aiconfigdemo {
	@Bean 
	ChatMemory chatMemory()
	{
		return new InMemoryChatMemory();
	}
}
