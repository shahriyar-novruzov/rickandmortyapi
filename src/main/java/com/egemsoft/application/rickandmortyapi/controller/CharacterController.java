package com.egemsoft.application.rickandmortyapi.controller;

import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.RestResponse;
import com.egemsoft.application.rickandmortyapi.service.CharacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Rest Controller for Character operations
 */
@Api("Rest Controller for Character operations")
@RestController
@RequestMapping(path = "${ms.root.url}/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    /**
     * @param pageNumber optional requested page number, if not exists, will be considered as page 1
     * @param sortBy optional sort parameter
     * @return Characters on the page
     */
    @ApiOperation("Find Characters on requested page, also sorted by name or episode count")
    @GetMapping
    public RestResponse<List<Character>> findPaginated(
            @RequestParam(name = "page", required = false) Integer pageNumber,
            @RequestParam(name = "sortBy", required = false) String sortBy) {
        return characterService.findPaginated(pageNumber, sortBy);
    }

    /**
     * @param id Character id
     * @return found Character
     */
    @ApiOperation("Find Characters by id")
    @GetMapping("/{id}")
    public Character findById(@PathVariable @NotNull Long id) {
        return characterService.findById(id);
    }
}
