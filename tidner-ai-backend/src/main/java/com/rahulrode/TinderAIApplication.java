package com.rahulrode;

import java.time.LocalDateTime;
import java.util.List;

import com.rahulrode.conversations.ChatMessage;
import com.rahulrode.conversations.Conversation;
import com.rahulrode.conversations.ConversationRepository;
import com.rahulrode.profiles.Gender;
import com.rahulrode.profiles.Profile;
import com.rahulrode.profiles.ProfileRepository;

import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
@Startup
public class TinderAIApplication {

  @Inject
  ProfileRepository profileRepo;

  @Inject
  ConversationRepository conversationRepo;

  void onStart(@Observes StartupEvent ev) {
    Log.info("%!%!%!%!%!%! ON START %!%!%!%!%!%!");
    var profile = Profile.builder()
        .id("1")
        .firstName("Rahul")
        .lastName("Rode")
        .age(40)
        .ethnicity("Indian")
        .gender(Gender.MALE)
        .bio("Software Programmer")
        .imageUrl("foo.jpg")
        .myersBriggsPersonalityType("INTP")
        .build();

    var conversation = Conversation.builder()
        .id("1")
        .profileId("1")
        .messages(List.of(new ChatMessage("Hello", profile.id(), LocalDateTime.now())))
        .build();
    profileRepo.persist(profile);
    conversationRepo.persist(conversation);
    
    profileRepo.listAll().forEach(System.out::println);
    conversationRepo.listAll().forEach(System.out::println);
  }

}
