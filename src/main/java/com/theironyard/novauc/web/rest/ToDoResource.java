package com.theironyard.novauc.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.theironyard.novauc.domain.ToDo;

import com.theironyard.novauc.repository.ToDoRepository;
import com.theironyard.novauc.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ToDo.
 */
@RestController
@RequestMapping("/api")
public class ToDoResource {

    private final Logger log = LoggerFactory.getLogger(ToDoResource.class);

    private static final String ENTITY_NAME = "toDo";
        
    private final ToDoRepository toDoRepository;

    public ToDoResource(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    /**
     * POST  /to-dos : Create a new toDo.
     *
     * @param toDo the toDo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new toDo, or with status 400 (Bad Request) if the toDo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/to-dos")
    @Timed
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDo toDo) throws URISyntaxException {
        log.debug("REST request to save ToDo : {}", toDo);
        if (toDo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new toDo cannot already have an ID")).body(null);
        }
        ToDo result = toDoRepository.save(toDo);
        return ResponseEntity.created(new URI("/api/to-dos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /to-dos : Updates an existing toDo.
     *
     * @param toDo the toDo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated toDo,
     * or with status 400 (Bad Request) if the toDo is not valid,
     * or with status 500 (Internal Server Error) if the toDo couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/to-dos")
    @Timed
    public ResponseEntity<ToDo> updateToDo(@Valid @RequestBody ToDo toDo) throws URISyntaxException {
        log.debug("REST request to update ToDo : {}", toDo);
        if (toDo.getId() == null) {
            return createToDo(toDo);
        }
        ToDo result = toDoRepository.save(toDo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, toDo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /to-dos : get all the toDos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of toDos in body
     */
    @GetMapping("/to-dos")
    @Timed
    public List<ToDo> getAllToDos() {
        log.debug("REST request to get all ToDos");
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos;
    }

    /**
     * GET  /to-dos/:id : get the "id" toDo.
     *
     * @param id the id of the toDo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the toDo, or with status 404 (Not Found)
     */
    @GetMapping("/to-dos/{id}")
    @Timed
    public ResponseEntity<ToDo> getToDo(@PathVariable Long id) {
        log.debug("REST request to get ToDo : {}", id);
        ToDo toDo = toDoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(toDo));
    }

    /**
     * DELETE  /to-dos/:id : delete the "id" toDo.
     *
     * @param id the id of the toDo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/to-dos/{id}")
    @Timed
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        log.debug("REST request to delete ToDo : {}", id);
        toDoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
