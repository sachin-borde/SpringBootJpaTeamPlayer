package com.otm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teamId;
	
	@Column(length = 15, nullable = false)
	private String teamName;
	
	private Long teamBudget;
	
	private Date teamDof;
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Set<Player> players = new HashSet<>();		
}
