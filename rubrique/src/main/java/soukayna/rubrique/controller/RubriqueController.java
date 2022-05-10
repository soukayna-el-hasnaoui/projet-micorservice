package soukayna.rubrique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import soukayna.rubrique.model.Rubrique;
import soukayna.rubrique.service.RubriqueService;

import java.util.List;

@RestController
public class RubriqueController {

    //injecter mon service
    @Autowired
    private RubriqueService rubriqueService;

    @GetMapping("/getAll")
    public List<Rubrique> getAll(){
        return rubriqueService.findAll();
    }

    @PostMapping("/create")
    // requestbody nous dit qu'on recoit en parametre un  json de type rubrique
    public ResponseEntity<?> create(@RequestBody Rubrique rubrique){
        try{
            //step1: verifier si la données est valide
            var all = rubriqueService.findAll();
            for (Rubrique r : all) {
                if (r.getName().equals(rubrique.getName())){
                    return ResponseEntity.status(400).body("Ce nom de rubrique existe deja");
                }
            }
            //step2: enregistrer
            System.out.println(rubrique);
            rubriqueService.save(rubrique);
            return  ResponseEntity.status(201).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try{
            rubriqueService.deleteById(id);
            return ResponseEntity.status(200).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }


}
