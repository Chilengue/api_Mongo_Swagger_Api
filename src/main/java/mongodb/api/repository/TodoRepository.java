package mongodb.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mongodb.api.model.TodoDTO;

public interface TodoRepository extends MongoRepository<TodoDTO, String> {

}
