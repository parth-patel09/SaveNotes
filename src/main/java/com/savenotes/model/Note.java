package com.savenotes.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Note {
	
	@Id
	private UUID id;
	private String title;
	private String text;
	
	private Date lastModified;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Notebook notebook;
	
	protected Note()
	{
		this.id = UUID.randomUUID();
		lastModified = new Date();
	}
	
	
	public Note(String title, String text, Notebook notebook)
	{
		this();
		this.title = title;
		this.text = text;
		this.notebook = notebook;
	}
	
	public Note(String id, String title, String text, Notebook notebook)
	{
		this(title, text, notebook);
		if(id!= null)
		{
			this.id = UUID.fromString(id);
		}
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Date getLastModified() {
		return lastModified;
	}


	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}


	public Notebook getNotebook() {
		return notebook;
	}


	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
	
	public String getNotebookId() {
        return this.notebook.getId().toString();
    }

	
}
