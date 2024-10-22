package mongodb.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collation = "todos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {


    @Id
    private String id;

    private String todo;

    private String description;

    private Boolean compled;

    private Date createdAt;

    private Date updateAt;
}
