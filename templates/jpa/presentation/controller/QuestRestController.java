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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/quests")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class #{entity}RestController {
    private final #{entity}Facade#{entityLowercase}Facade;

    @GetMapping
    public ResponseEntity<List<Get#{entity}Response>> get#{entity}() {
        return ResponseEntity.ok(questFacade.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Get#{entity}Response> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(questFacade.get(id));
    }

    @PostMapping
    public ResponseEntity<Create#{entity}Response> create(@RequestBody Create#{entity}Request request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(questFacade.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Update#{entity}Response> update(@PathVariable("id") UUID id, @RequestBody Update#{entity}Request request) {
        return ResponseEntity.ok(questFacade.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
       #{entityLowercase}Facade.delete(id);
    }
}
