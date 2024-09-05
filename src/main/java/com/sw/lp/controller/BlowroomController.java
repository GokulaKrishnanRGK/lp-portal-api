package com.sw.lp.controller;

import com.sw.lp.authentication.AuthenticationFacade;
import com.sw.lp.entity.AppResponse;
import com.sw.lp.record.BlowroomLine;
import com.sw.lp.service.BlowroomService;
import com.sw.lp.utils.JsonUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blowroom")
public class BlowroomController {

  private static final Logger logger = LoggerFactory.getLogger(BlowroomController.class);

  private BlowroomService blowroomService;
  private AuthenticationFacade authenticationFacade;

  public BlowroomController(BlowroomService blowroomService, AuthenticationFacade authenticationFacade) {
    this.blowroomService = blowroomService;
    this.authenticationFacade = authenticationFacade;
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public AppResponse getAllBlowroomLines() {
    List<BlowroomLine> blowroomLines = blowroomService.getAllBlowroomLines(authenticationFacade.getMillId());
    return AppResponse.ok(JsonUtils.transformTree(blowroomLines));
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public AppResponse newBlowroomLine(@RequestBody BlowroomLine blowroomLine) {
    blowroomService.newBlowroomLine(blowroomLine, authenticationFacade.getMillId());
    return AppResponse.ok("Success");
  }

}
