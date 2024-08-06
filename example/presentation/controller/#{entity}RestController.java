package #{group}.#{project}.feature.#{entityLowercase}.presentation.controller;

import #{group}.#{project}.feature.#{entityLowercase}.application.facade.#{entity}Facade;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Update#{entity}Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/#{entityLowercase}s")
@RequiredArgsConstructor
public class #{entity}RestController {
    private final #{entity}Facade #{entityLowercase}Facade;

    @PostMapping
    public ResponseEntity<Create#{entity}Response> create(@Valid @RequestBody Create#{entity}Request request) {
        var response = #{entityLowercase}Facade.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Update#{entity}Response> update(@PathVariable Long id, @Valid @RequestBody Update#{entity}Request request) {
        var response = #{entityLowercase}Facade.update(id, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Collection<Get#{entity}Response>> getAll() {
        var response = #{entityLowercase}Facade.getAll();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Get#{entity}Response> get(@PathVariable Long id) {
        var response = #{entityLowercase}Facade.get(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        #{entityLowercase}Facade.delete(id);

        return ResponseEntity.noContent()
                .build();
    }
}
