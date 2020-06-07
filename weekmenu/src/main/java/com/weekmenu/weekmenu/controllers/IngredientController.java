package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.helpers.ControllerHelpers;
import com.weekmenu.weekmenu.models.Ingredient;
import com.weekmenu.weekmenu.models.Tag;
import com.weekmenu.weekmenu.services.IngredientService;
import com.weekmenu.weekmenu.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;
    private final UserService userService;

    public IngredientController(IngredientService ingredientService, UserService userService) {
        this.ingredientService = ingredientService;
        this.userService = userService;
    }

    @GetMapping
    public List<Ingredient> getIngredients(){
        return ingredientService.getIngredientsByGroupId(ControllerHelpers.GetCurrentGroupId(userService));
    }

    @PostMapping("/create")
    public ResponseEntity createIngredient(@RequestBody Ingredient ingredient) {
        if(!ingredientService.doesIngredientExist(ingredient.getIngredientName())){
            ingredient.setGroupId(ControllerHelpers.GetCurrentGroupId(userService));
            ingredientService.saveIngredient(ingredient);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
