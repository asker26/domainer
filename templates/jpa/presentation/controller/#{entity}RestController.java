package #{group}.#{project}.feature.#{entityLowercase}.presentation.controller;

import #{group}.#{project}.feature.#{entityLowercase}.application.facade.#{entity}Facade;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.#{entityLowercase}.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.#{entityLowercase}.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Update#{entity}Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/quests")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class #{entity}RestController {
    private final #{entity}Facade #{entityLowercase}Facade;

    @GetMapping
    public ResponseEntity<List<Get#{entity}Response>> get#{entity}() {
        return ResponseEntity.ok(#{entityLowercase}Facade.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Get#{entity}Response> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(#{entityLowercase}Facade.get(id));
    }

    @PostMapping
    public ResponseEntity<Create#{entity}Response> create(@RequestBody Create#{entity}Request request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(#{entityLowercase}Facade.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Update#{entity}Response> update(@PathVariable("id") Long id, @RequestBody Update#{entity}Request request) {
        return ResponseEntity.ok(#{entityLowercase}Facade.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
       #{entityLowercase}Facade.delete(id);
    }
}
