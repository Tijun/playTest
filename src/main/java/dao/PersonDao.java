package dao;

import com.google.inject.ImplementedBy;
import dao.impl.PersonDaoImpl;
import entity.Person;

import java.util.concurrent.CompletionStage;
@ImplementedBy(PersonDaoImpl.class)
public interface PersonDao {

    CompletionStage<Person> get(String uuid);
}
