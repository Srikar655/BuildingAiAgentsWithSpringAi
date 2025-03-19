package com.iss.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.iss.Tools.*;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

import java.lang.reflect.Method;


@Service
public class OllamaService {
	private final ChatClient chatClient;
	
	public OllamaService(ChatClient.Builder chatClientBuilder,ChatMemory chatMemory)
	{
		this.chatClient=chatClientBuilder.defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory)).build();
	}
	public String callAi(String chatId,String query)
	{	
		Method method=ReflectionUtils.findMethod(DataAndTimeTools.class, "getCurrentDateAndTime");
		MethodToolCallback methodToolCallBack=MethodToolCallback.builder().toolDefinition(ToolDefinition.builder(method).description("Gets time and date for user at users timezone").build())
				.toolMethod(method).toolObject(new DataAndTimeTools()).build();
		
		return this.chatClient.prompt(query).tools(methodToolCallBack).advisors(a->a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
				.param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100)).call().content();
	}
}

