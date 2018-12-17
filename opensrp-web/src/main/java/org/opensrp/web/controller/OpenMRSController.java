package org.opensrp.web.controller;

import ch.lambdaj.function.convert.Converter;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.opensrp.common.AllConstants.BaseEntity;
import org.opensrp.domain.Client;
import org.opensrp.dto.Action;
import org.opensrp.repository.AllClients;
import org.opensrp.scheduler.Alert;
import org.opensrp.scheduler.repository.AllAlerts;
import org.opensrp.scheduler.service.ActionService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.lambdaj.collection.LambdaCollections.with;
import static org.opensrp.common.AllConstants.Event.PROVIDER_ID;
import static org.opensrp.web.rest.RestUtils.getIntegerFilter;
import static org.opensrp.web.rest.RestUtils.getStringFilter;

@Controller
public class OpenMRSController {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(OpenMRSController.class.toString());

    private ActionService actionService;
    private AllClients allClients;
    private AllAlerts allAlerts;

    @Autowired
    public OpenMRSController(ActionService actionService, AllClients c, AllAlerts allAlerts) {
        this.actionService = actionService;
        this.allClients = c;
        this.allAlerts = allAlerts;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ANMactions")
    @ResponseBody
    public List<Action> getNewActionForANM(@RequestParam("anmIdentifier") String anmIdentifier, @RequestParam("timeStamp") Long timeStamp){
        List<org.opensrp.scheduler.Action> actions = actionService.getNewAlertsForANM(anmIdentifier, timeStamp);
        return with(actions).convert(new Converter<org.opensrp.scheduler.Action, Action>() {
            @Override
            public Action convert(org.opensrp.scheduler.Action action) {
                return ActionConvertor.from(action);
            }
        });
    }

}

