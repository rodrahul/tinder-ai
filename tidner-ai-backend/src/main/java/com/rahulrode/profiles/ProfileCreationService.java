package com.rahulrode.profiles;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahulrode.profiles.models.Profile;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ProfileCreationService {

  @Inject
  ProfileRepository profileRepository;

  String jsonFile = "profiles.json";

  public void saveProfilesToDB() throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    // FileReader reader = new FileReader(jsonFile);
    var ins = Files.newInputStream(Path.of(jsonFile));
    // var buffer = Files.newBufferedReader(Path.of(jsonFile));

    var profiles = mapper.readValue(ins, new TypeReference<List<Profile>>() {
    });

    profiles.stream()
        .filter(p -> profileRepository.findById(p.id()) != null)
        .forEach(profileRepository::persist);

  }

}
