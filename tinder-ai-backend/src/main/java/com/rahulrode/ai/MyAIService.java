package com.rahulrode.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService()
public interface MyAIService {

  @SystemMessage("You are a professional poet.")
  @UserMessage("""
          Write a poem about {topic}. The poem should be {lines} lines long. Then send this poem by email.
      """)
  String writeAPoem(String topic, int lines);

}