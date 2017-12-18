package service;

import base.PlayApplicationContext;
import org.junit.Test;

import javax.inject.Inject;

public class ApplicationServiceTest extends PlayApplicationContext {


    @Inject
    ApplicationService applicationService;

    @Test
    public void testService(){
        System.out.println(applicationService.sayHello());
    }

    @Test
    public void getAperson(){
        System.out.println(applicationService.getPerson());
    }

}
