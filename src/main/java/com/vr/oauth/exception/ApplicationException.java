package com.vr.oauth.exception;

/**
 * @author sachin
 * @purpose Custom runtime exception.
 */
public class ApplicationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private ErrorResponseEnum errorResponse;

  public ApplicationException(ErrorResponseEnum errorResponse) {
    super(errorResponse.getErrorText());
    this.errorResponse = errorResponse;
  }

  public ApplicationException(String errorMessage) {
    super(errorMessage);
  }

  public ApplicationException(ErrorResponseEnum errorResponse, Throwable throwable) {
    super(throwable);
    this.errorResponse = errorResponse;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public ErrorResponseEnum getErrorResponse() {
    return errorResponse;
  }

  public void setErrorResponse(ErrorResponseEnum errorResponse) {
    this.errorResponse = errorResponse;
  }

}
