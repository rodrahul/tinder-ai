package com.rahulrode;

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
    profileRepo.persist(profile);
    System.out.println(profileRepo.listAll());
  }

}
