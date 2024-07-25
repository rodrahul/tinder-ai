package com.rahulrode;

import java.io.IOException;

import com.rahulrode.profiles.ProfileCreationService;

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
  ProfileCreationService profileCreationService;

  void onStart(@Observes StartupEvent ev) throws IOException {
    Log.info("%!%!%!%!%!%! ON START %!%!%!%!%!%!");
    profileCreationService.saveProfilesToDB();

  }

}
