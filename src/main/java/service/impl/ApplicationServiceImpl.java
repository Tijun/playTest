package service.impl;

import service.ApplicationService;

public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public String sayHello() {
        return "say hello play";
    }
}
