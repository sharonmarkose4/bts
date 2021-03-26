package com.demo.bts;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bts.entity.Bug;
import com.demo.bts.service.BugService;

@RestController
public class BugController {
	Logger logger = Logger.getLogger(BugController.class.getName());
	@Autowired
	BugService bugService;

	@PostMapping("/bug")
	@ResponseStatus(code = HttpStatus.CREATED)
	/**
	 * method to create bug
	 * 
	 * @param bug
	 * @param bindingResult
	 *
	 */
	String createBug(@RequestBody @Valid Bug bug, BindingResult bindingResult) { // create bug
		validateModel(bindingResult);
		return bugService.createBug(bug);
	}

	/**
	 * method to get all bugs in db
	 * 
	 * @return all bugs
	 */

	@GetMapping("/bug")
	List<Bug> getBugs() {
		return bugService.getBugs();// get all bugs
	}

	/**
	 * method to search for bug
	 * 
	 * @param bugId
	 * @return zero or matching bug
	 */

	@GetMapping("/bug/{id}")
	Optional<Bug> getBug(@PathVariable("id") String bugId) {
		return bugService.getBug(bugId);// get bug by id
	}

	/**
	 * method to update bug
	 * 
	 * @param bug
	 * @param bindingResult
	 * @param bugId
	 */

	@PutMapping("/bug/{id}")
	void updateBug(@RequestBody @Valid Bug bug, BindingResult bindingResult, @PathVariable("id") String bugId) {
		validateModel(bindingResult);
		logger.setLevel(Level.WARNING);
		logger.log(Level.WARNING, bugId);
		bug.setId(bugId);
		bugService.updateBug(bug);
	}

	/**
	 * method to delete bug
	 * 
	 * @param bugId
	 */

	@DeleteMapping("/bug/{id}")
	void deleteBug(@PathVariable("id") String bugId) { // delete bug
		// TODO:print bugid
		bugService.deleteBug(bugId);
	}

	/**
	 * method to validate, throw exception if errors are present
	 * 
	 * @param bindingResult
	 */

	private void validateModel(Errors bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Something wrong");
		}
	}
}
