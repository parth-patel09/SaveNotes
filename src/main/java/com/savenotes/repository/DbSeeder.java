package com.savenotes.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.savenotes.model.Note;
import com.savenotes.model.Notebook;


@Component
@ConditionalOnProperty(name="noteit.db.recreate", havingValue="false")
public class DbSeeder implements CommandLineRunner {
	
	@Autowired
	private NotebookRepository notebookRepository;
	
	@Autowired
	private NoteRepository noteRepository;

	
	public DbSeeder(NotebookRepository notebookRepository,
					NoteRepository noteRepository) {
		this.notebookRepository = notebookRepository;
		this.noteRepository = noteRepository;
	}
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		this.notebookRepository.deleteAll();
		this.noteRepository.deleteAll();
		
		
		Notebook defaultNoteBook = new Notebook("Default");
		this.notebookRepository.save(defaultNoteBook);
		
		Notebook quotesNoteBook = new Notebook("Quotes");
		this.notebookRepository.save(quotesNoteBook);
		
		Note note = new Note("Hello", "Welcome to Note It", defaultNoteBook);
		this.noteRepository.save(note);
		
		Note quotesNote = new Note("Latin Quote", "Carpe Diem", quotesNoteBook);
		this.noteRepository.save(quotesNote);
		
		
		System.out.println("Initialized database");
	}

}
