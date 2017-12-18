package service.impl;

import dao.PersonDao;
import entity.Person;
import service.ApplicationService;

import javax.inject.Inject;

public class ApplicationServiceImpl implements ApplicationService {

    private final PersonDao personDao;//dependency should be final and inject by constractor

    @Inject
    public ApplicationServiceImpl(PersonDao personDao){
        this.personDao = personDao;
    }
    @Override
    public String sayHello() {
        return "say hello play";
    }

    @Override
    public Person getPerson() {
        return personDao.get("123").toCompletableFuture().join();// in a product envirement don't use join
    }
}
