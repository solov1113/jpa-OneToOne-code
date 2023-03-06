package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Tutorial;
import com.example.demo.model.TutorialDetails;
import com.example.demo.repository.TutorialDetailsRepository;
import com.example.demo.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TutorialDetailsController {

    @Autowired
    TutorialRepository tutorialRepository;
    @Autowired
    TutorialDetailsRepository detailsRepository;
    @PostMapping("/tutorials/{tutorialId}/details")
    public ResponseEntity<TutorialDetails> createDetails(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody TutorialDetails detailsRequest) {
        Tutorial tutorial = tutorialRepository.findById(tutorialId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        detailsRequest.setCreatedOn(new java.util.Date());
        detailsRequest.setTutorial(tutorial);

        TutorialDetails details = detailsRepository.save(detailsRequest);
        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }
}
