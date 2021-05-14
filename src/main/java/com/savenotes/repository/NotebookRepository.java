package com.savenotes.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savenotes.model.Notebook;


@Repository
public interface NotebookRepository extends JpaRepository<Notebook, UUID> 
{
		Notebook findById(String id); 
}




