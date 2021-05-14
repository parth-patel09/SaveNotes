package com.savenotes.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savenotes.Mapper;
import com.savenotes.model.Notebook;
import com.savenotes.repository.NotebookRepository;
import com.savenotes.viewmodel.NotebookViewModel;


@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NoteBookController 
{
	
	@Autowired
	private NotebookRepository notebookRepository;
	
	@Autowired
	private Mapper mapper;
	
	public NoteBookController(NotebookRepository notebookRepository, Mapper mapper)
	{
		this.notebookRepository = notebookRepository;
		this.mapper = mapper;
	}
	
	@GetMapping("/all")
	public List<Notebook> fetchAllNotebook() 
	{
		List<Notebook> listOfNotebooks = notebookRepository.findAll();
		System.out.println("Hiiiiiiii");
		return listOfNotebooks;
	}
	
	@PostMapping
	public Notebook save(@Valid @RequestBody NotebookViewModel notebookViewModel, 
								BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			throw new ValidationException();
		}
		Notebook notebook = mapper.convertToNotebookEntity(notebookViewModel);
		
		notebookRepository.save(notebook);
		return notebook;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id)
	{
		notebookRepository.deleteById(UUID.fromString(id));
	}
}



