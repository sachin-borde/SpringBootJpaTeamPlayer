package com.otm.service;

import java.util.List;

import com.otm.model.dto.TeamDto;
import com.otm.payloads.TeamResponse;

public interface TeamService {

	TeamDto saveTeamData(TeamDto teamDto);

	TeamDto getTeamDataById(Integer teamId);

	TeamResponse getAllTeamData(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	List<TeamDto> getTeamDataByName(String keyword);

	TeamDto updateTeamDataById(TeamDto teamDto, Integer teamId);

	void deleteTeamDataById(Integer teamId);

}
