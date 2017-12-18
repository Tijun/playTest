package dao.impl;

import dao.PersonDao;
import db.DatabaseExecutionContext;
import entity.Person;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class PersonDaoImpl implements PersonDao {

    private final JPAApi jpaApi;

    private final DatabaseExecutionContext executionContext;

    @Inject
    PersonDaoImpl(JPAApi jpaApi,DatabaseExecutionContext executionContext){
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }
    @Override
    public CompletionStage<Person> get(String uuid) {
        return CompletableFuture.supplyAsync(()->jpaApi.withTransaction(em->em.find(Person.class,"123")));
    }

    public Person getAPerson(){
        return jpaApi.em().find(Person.class,"123");
    }
}
