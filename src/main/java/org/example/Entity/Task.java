package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @Size(min = 3, max = 200)
    private String name;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    @JsonBackReference
    private ToDo toDo;
}
