package com.rahulrode.matches;

import java.util.List;

import com.rahulrode.matches.models.CreateMatchRequest;
import com.rahulrode.matches.models.Match;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/matches")
public class MatchResource {

  @Inject
  MatchService matchService;

  @POST
  public Match createMatch(CreateMatchRequest request) {

    return matchService.createMatch(request);
  }


  @GET
  public List<Match> getMatches() {
    return matchService.getAllMatches();
  }




}
