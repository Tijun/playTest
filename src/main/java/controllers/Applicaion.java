package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.ApplicationService;

import javax.inject.Inject;

public class Applicaion extends Controller {

    @Inject
    private ApplicationService applicationService;

    public Result index(){
        return ok("hello java play");
    }

    public Result getPerson(){
       return ok(Json.toJson(applicationService.getPerson()));
    }
    public Result hello(){
        return ok(applicationService.sayHello());
    }
}
