package com.savenotes.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savenotes.model.Note;
import com.savenotes.model.Notebook;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {

	List<Note> findAllByNotebook(Notebook notebook);
}
