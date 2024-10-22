package mongodb.api.controller;

import java.util.Date;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mongodb.api.model.TodoDTO;
import mongodb.api.repository.TodoRepository;

@RestController
@Controller("/todo")
public class TodoController {

    @Autowired
    private TodoRepository todorepo;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<TodoDTO> todos = todorepo.findAll();
        if (!todos.isEmpty()) {
            return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no todo avaitab;e", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/todo")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
        try {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todorepo.save(todo);
            return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSilgleTodo(@PathVariable("id") String id) {
        Optional<TodoDTO> todoOpt = todorepo.findById(id);
        if (todoOpt.isPresent()) {
            return new ResponseEntity<>(todoOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("todo not found with id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodoDTO todo) {
        Optional<TodoDTO> todoOpt = todorepo.findById(id);
        if (todoOpt.isPresent()) {
            TodoDTO todosave = todoOpt.get();
            todosave.setCompled(todo.getCompled() != null ? todo.getCompled() : todosave.getCompled());
            todosave.setTodo(todo.getTodo() != null ? todo.getTodo() : todosave.getDescription());
            todosave.setDescription(todo.getDescription() != null ? todo.getDescription() : todosave.getDescription());
            todosave.setUpdateAt(new Date(System.currentTimeMillis()));
            todorepo.save(todosave);
            return new ResponseEntity<>(todosave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("todo not found with id" + id, HttpStatus.NOT_FOUND);
        }
    }

}
