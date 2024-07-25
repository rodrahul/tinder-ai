package com.rahulrode.profiles;

import com.rahulrode.profiles.models.Profile;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ProfileService {

  // DB Manager
  @Inject
  ProfileRepository profileRepo;

  public Profile getRandomProfile() {
    return profileRepo.findRandomProfile();
  }

}
