package com.sw.lp.controller;

import com.sw.lp.entity.AppResponse;
import com.sw.lp.record.Unit;
import com.sw.lp.service.UnitsService;
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
@RequestMapping("/unit")
public class UnitsController {

  private static final Logger logger = LoggerFactory.getLogger(UnitsController.class);

  private UnitsService unitsService;

  public UnitsController(UnitsService unitsService) {
    this.unitsService = unitsService;
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public AppResponse getUnits() {
    List<Unit> units = unitsService.getUnits(1);
    return AppResponse.ok(JsonUtils.transformTree(units));
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public AppResponse newUnit(@RequestBody List<Unit> units) {
    unitsService.saveUnits(units, 1);
    return AppResponse.ok("Success");
  }
}
