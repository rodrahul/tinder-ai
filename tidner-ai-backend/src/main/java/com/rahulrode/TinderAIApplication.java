package com.rahulrode;

import java.time.LocalDateTime;
import java.util.List;

import com.rahulrode.conversations.ConversationRepository;
import com.rahulrode.conversations.models.ChatMessage;
import com.rahulrode.conversations.models.Conversation;
import com.rahulrode.profiles.ProfileRepository;
import com.rahulrode.profiles.models.Gender;
import com.rahulrode.profiles.models.Profile;

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
    profileRepo.deleteAll();
    conversationRepo.deleteAll();


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
    var profile2 = Profile.builder()
        .id("2")
        .firstName("Sayli")
        .lastName("Bhosale")
        .age(40)
        .ethnicity("Indian")
        .gender(Gender.FEMALE)
        .bio("Compliance Specialist")
        .imageUrl("sau.jpg")
        .myersBriggsPersonalityType("INTJ")
        .build();

    // var conversation = Conversation.builder()
    //     .id("1")
    //     .profileId("1")
    //     .messages(List.of(new ChatMessage("Hello", profile.id(), LocalDateTime.now())))
    //     .build();
    profileRepo.persist(profile);
    profileRepo.persist(profile2);
    // conversationRepo.persist(conversation);
    
    profileRepo.listAll().forEach(System.out::println);
    conversationRepo.listAll().forEach(System.out::println);
  }

}
