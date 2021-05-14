package com.savenotes.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.savenotes.Mapper;
import com.savenotes.model.Note;
import com.savenotes.model.Notebook;
import com.savenotes.repository.NoteRepository;
import com.savenotes.repository.NotebookRepository;
import com.savenotes.viewmodel.NoteViewModel;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController 
{
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private NotebookRepository notebookRepository;
	
	@Autowired
	private Mapper mapper;
	
	public NoteController(NotebookRepository notebookRepository, NoteRepository noteRepository,
								Mapper mapper)
	{
		this.notebookRepository = notebookRepository;
		this.noteRepository = noteRepository;
		this.mapper = mapper;
	}
	
	@GetMapping("/all")
	public List<NoteViewModel> fetchAllNotes()
	{
		List<Note> notes = noteRepository.findAll(); 
		
		List<NoteViewModel> listOfNotes = notes.stream().map(note -> this.
				mapper.convertToNoteViewModel(note)).collect(Collectors.toList());
		
		return listOfNotes;		
	}
	
	@GetMapping("/byId/{id}")
	public NoteViewModel findNoteById(@PathVariable String id)
	{
		Note note = noteRepository.findById(UUID.fromString(id)).orElse(null);
		
		if(note == null)
		{
			throw new EntityNotFoundException();
		}
		
		NoteViewModel noteViewModel = mapper.convertToNoteViewModel(note);
		return noteViewModel;
	}
	
	@GetMapping("byNotebook/{notebookId}")
	public List<NoteViewModel> findByNotebook(@PathVariable String notebookId)
	{
		List<Note> notes = new ArrayList<>();
		Optional<Notebook> notebook = notebookRepository.findById(UUID.fromString(notebookId));
		
		if(notebook.isPresent())
		{
			notes = noteRepository.findAllByNotebook(notebook.get());
		}
		
		List<NoteViewModel> listOfNotesInNoteBook = notes.stream().map(
			note-> mapper.convertToNoteViewModel(note)).collect(Collectors.toList());
		
		return listOfNotesInNoteBook;	
	}
	
	
	@PostMapping
	public Note save(@Valid @RequestBody NoteViewModel noteViewModel, 
						BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			throw new ValidationException();
		}
		
		Note note = mapper.convertToNoteEntity(noteViewModel);
		noteRepository.save(note);
		
		return note;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id)
	{
		noteRepository.deleteById(UUID.fromString(id));	
	}
}
