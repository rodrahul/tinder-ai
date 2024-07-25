package com.rahulrode.exception;

import jakarta.ws.rs.core.Response.Status;
import lombok.Getter;

@Getter
public class ResponseStatusException extends RuntimeException {

  private final Status status;
  private final String message;

  public ResponseStatusException(int status) {
    super();
    this.status = Status.fromStatusCode(status);
    this.message = null;
  }

  public ResponseStatusException(Status status) {
    super();
    this.status = status;
    this.message = null;
  }

  public ResponseStatusException(int status, String message) {
    super(message);
    this.status = Status.fromStatusCode(status);
    ;
    this.message = message;
  }

  public ResponseStatusException(Status status, String message) {
    super(message);
    this.status = status;
    this.message = message;
  }

}
