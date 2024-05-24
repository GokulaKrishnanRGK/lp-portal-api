package com.sw.lp.entity;

import com.sw.lp.constants.ActionStatus;
import org.springframework.lang.Nullable;

public class AppResponse<B> {

  private final ActionStatus actionStatus;

  private final B body;

  private final String appStatusCode;

  /**
   * Create a new {@code AppResponse} with the given body and status code, and no headers.
   *
   * @param actionStatus the status code
   */
  public AppResponse(ActionStatus actionStatus, String message) {
    this.appStatusCode = null;
    this.actionStatus = actionStatus;
    this.body = (B) message;
  }

  public static <T> AppResponse<T> ok(T body) {
    return new AppResponse<>(ActionStatus.SUCCESS, body);
  }

  public static <T> AppResponse<T> fail(T body) {
    return new AppResponse<>(ActionStatus.FAILED, body);
  }

  /**
   * Create a new {@code AppResponse} with the given body and status code, and no headers.
   *
   * @param body         the entity body
   * @param actionStatus the status code
   */
  public AppResponse(ActionStatus actionStatus, @Nullable B body) {
    this.appStatusCode = null;
    this.body = body;
    this.actionStatus = actionStatus;
  }

  public AppResponse(Object responseData) {
    this.actionStatus = null;
    this.body = (B) responseData;
    this.appStatusCode = null;
  }

  public AppResponse(ActionStatus actionStatus) {
    this.actionStatus = actionStatus;
    this.body = null;
    this.appStatusCode = null;
  }

  /**
   * Create a new {@code AppResponse} with the given body and status code, and no headers.
   *
   * @param body         the entity body
   * @param actionStatus the status code
   */
  public <S> AppResponse(ActionStatus actionStatus, S appStatusCode, @Nullable B body) {
    this.appStatusCode = appStatusCode.toString();
    this.body = body;
    this.actionStatus = actionStatus;
  }

  public ActionStatus getActionStatus() {
    return actionStatus;
  }

  public B getBody() {
    return body;
  }

  public String getAppStatusCode() {
    return appStatusCode;
  }

}
