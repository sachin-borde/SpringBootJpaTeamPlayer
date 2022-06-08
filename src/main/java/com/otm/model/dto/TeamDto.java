package com.otm.model.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TeamDto {

	private int teamId;

	@NotEmpty
	@Size(min = 2, message = "Team name Must Greater than 2 character")
	private String teamName;

	@NotNull
	private Long teamBudget;

	@NotNull
	@DateTimeFormat
	private Date teamDof;

	private Set<PlayerDto> players = new HashSet<>();
}
