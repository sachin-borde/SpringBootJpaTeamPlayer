package com.otm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otm.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer>{

	@Query("select t from Team t where t.teamName like :key")
	List<Team> findByTeamName(@Param("key") String teamName);

}
