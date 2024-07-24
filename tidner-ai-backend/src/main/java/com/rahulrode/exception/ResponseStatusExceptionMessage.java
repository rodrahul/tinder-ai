package com.rahulrode.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
@JsonInclude(Include.NON_NULL)
@Data
public class ResponseStatusExceptionMessage {

  /**
   * {
   * "timestamp": "2020-12-26T19:49:11.426+00:00",
   * "status": 404,
   * "error": "Not Found",
   * "message": "Actor Not Found",
   * "path": "/actor/8"
   * }
   */

  String timestamp;
  int status;
  String error;
  String message;
  String path;

}
