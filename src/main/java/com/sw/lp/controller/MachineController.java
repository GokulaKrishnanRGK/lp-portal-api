package com.sw.lp.controller;

import com.sw.lp.authentication.AuthenticationFacade;
import com.sw.lp.constants.MachineType;
import com.sw.lp.entity.AppResponse;
import com.sw.lp.record.Machine;
import com.sw.lp.service.MachineService;
import com.sw.lp.utils.JsonUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/machine")
public class MachineController {

  private static final Logger logger = LoggerFactory.getLogger(MachineController.class);

  private MachineService machineService;
  private AuthenticationFacade authenticationFacade;

  public MachineController(MachineService machineService, AuthenticationFacade authenticationFacade) {
    this.machineService = machineService;
    this.authenticationFacade = authenticationFacade;
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public AppResponse getMachinesByType(@RequestParam(required = false) MachineType type) {
    List<Machine> machines = machineService.getAllMachines(authenticationFacade.getMillId());
    return AppResponse.ok(JsonUtils.transformTree(machines));
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public AppResponse newMachine(@RequestBody Machine machine) {
    machineService.saveMachine(machine, authenticationFacade.getMillId()); //TODO: get mill id from auth facade
    return AppResponse.ok("Success");
  }

}
