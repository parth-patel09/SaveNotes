package com.savenotes;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.savenotes.model.Note;
import com.savenotes.model.Notebook;
import com.savenotes.repository.NotebookRepository;
import com.savenotes.viewmodel.NoteViewModel;
import com.savenotes.viewmodel.NotebookViewModel;

@Component
public class Mapper 
{
	@Autowired
	private NotebookRepository notebookRepository;
	
	/*public Mapper(NotebookRepository notebookRepository)
	{
		this.notebookRepository = notebookRepository;
	}*/
	
	public NoteViewModel convertToNoteViewModel(Note entity)
	{
		NoteViewModel viewModel = new NoteViewModel();
		viewModel.setId(entity.getId().toString());
		viewModel.setText(entity.getText());
		viewModel.setTitle(entity.getTitle());
		viewModel.setNotebookId(entity.getNotebook().getId().toString());
		viewModel.setLastModified(entity.getLastModified());
		
		return viewModel;
	}

	public Note convertToNoteEntity(NoteViewModel noteViewModel)
	{
		Notebook notebook = this.notebookRepository.
				findById(UUID.fromString(noteViewModel.getNotebookId())).get();
		
		if(notebook !=null)
		{
		Note entity = new Note(noteViewModel.getId(), noteViewModel.getTitle(), 
								noteViewModel.getText(),notebook);
		
		return entity;
		}
		else {
			return null;
		}
	}
	
	
	public NotebookViewModel convertToNoteBookViewModel(Notebook entity)
	{
		NotebookViewModel model = new NotebookViewModel();
		model.setId(entity.getId().toString());
		model.setName(entity.getName());
		model.setNbNotes(entity.getNotes().size());
		
		return model;
	}
	
	public Notebook convertToNotebookEntity(NotebookViewModel notebookViewModel)
	{
		Notebook entity = new Notebook(notebookViewModel.getId(), notebookViewModel.getName());
		return entity;
	}
}
