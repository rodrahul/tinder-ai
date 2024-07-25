package com.rahulrode.profiles;

import java.util.List;

import com.mongodb.client.model.Aggregates;
import com.rahulrode.profiles.models.Profile;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfileRepository implements PanacheMongoRepositoryBase<Profile, String> {

  public Profile findRandomProfile() {
    return mongoCollection()
        .aggregate(List.of(Aggregates.sample(1)))
        .first();
  }
}
