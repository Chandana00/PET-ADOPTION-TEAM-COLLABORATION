package com.psp.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psp.app.model.Message;
import com.psp.app.model.Pet;


@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {


}
