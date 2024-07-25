package com.rahulrode.matches;

import com.rahulrode.matches.models.Match;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MatchRepository implements PanacheMongoRepositoryBase<Match, String>{

}
