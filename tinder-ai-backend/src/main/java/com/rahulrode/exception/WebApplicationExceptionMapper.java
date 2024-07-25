package com.rahulrode.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<ResponseStatusException> {

  @Inject
  UriInfo uriInfo;

  @Override
  public Response toResponse(ResponseStatusException ex) {


    var message = ResponseStatusExceptionMessage.builder()
        .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
        .status(ex.getStatus().getStatusCode())
        .error(ex.getStatus().getReasonPhrase())
        .message(ex.getMessage())
        .path(uriInfo.getPath())
        .build();

    return Response
        .status(ex.getStatus())
        .entity(message)
        .build();
  }

}