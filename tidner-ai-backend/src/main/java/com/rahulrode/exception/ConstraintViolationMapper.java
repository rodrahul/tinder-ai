package com.rahulrode.exception;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.validator.runtime.jaxrs.ViolationReport;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException cve) {
    List<ViolationReport.Violation> violations = new ArrayList<>(cve.getConstraintViolations().size());

    for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
      violations.add(
          new ViolationReport.Violation(cv.getPropertyPath().toString(), cv.getMessage()));
    }

    var vr = new ViolationReport("Constraint Violation", Response.Status.BAD_REQUEST, violations);

    return Response.status(400).entity(vr).build();
  }

}