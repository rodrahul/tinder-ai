package com.rahulrode.profiles;

import com.rahulrode.profiles.models.Profile;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfileRepository implements PanacheMongoRepositoryBase<Profile, String> {

}
