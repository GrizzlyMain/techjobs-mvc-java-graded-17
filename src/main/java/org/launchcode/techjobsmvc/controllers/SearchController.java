package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
//renders the form defined in the search.html
    //will be adding a second handler
    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        ArrayList<Job> jobResults;

        if (searchTerm.isEmpty() && searchType.equals("all")) {
        jobResults = JobData.findAll();


        }else {
        jobResults = JobData.findByColumnAndValue(searchType.toLowerCase(), searchTerm.toLowerCase());
        }
        model.addAttribute("jobResults", jobResults);

        return "search";
    }

 }



