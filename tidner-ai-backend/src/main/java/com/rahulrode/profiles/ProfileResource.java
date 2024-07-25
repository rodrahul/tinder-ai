package com.rahulrode.profiles;

import com.rahulrode.profiles.models.Profile;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/profiles")
public class ProfileResource {

  @Inject
  ProfileService profileService;

  @GET
  @Path("/random")
  public Profile getRandomProfile() {

    return profileService.getRandomProfile();
  }

}
