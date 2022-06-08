package com.otm.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.otm.exceptions.ResourceNotFoundException;
import com.otm.model.Team;
import com.otm.model.dto.TeamDto;
import com.otm.payloads.TeamResponse;
import com.otm.repository.TeamRepository;
import com.otm.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TeamDto saveTeamData(TeamDto teamDto) {

		Team team = modelMapper.map(teamDto, Team.class);

		Team saveTeamData = teamRepository.save(team);

		return modelMapper.map(saveTeamData, TeamDto.class);
	}

	@Override
	public TeamDto getTeamDataById(Integer teamId) {

		Team team = teamRepository.findById(teamId)
				.orElseThrow(() -> new ResourceNotFoundException("Team", "Team Id", teamId));

		return modelMapper.map(team, TeamDto.class);
	}

	@Override
	public TeamResponse getAllTeamData(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Team> pageTeam = teamRepository.findAll(p);

		List<Team> teams = pageTeam.getContent();

		List<TeamDto> teamDtos = teams.stream().map((team) -> this.modelMapper.map(team, TeamDto.class))
				.collect(Collectors.toList());

		TeamResponse teamResponse = new TeamResponse();

		teamResponse.setContent(teamDtos);
		teamResponse.setPageNumber(pageTeam.getNumber());
		teamResponse.setPageSize(pageTeam.getSize());
		teamResponse.setTotalElements(pageTeam.getTotalElements());

		teamResponse.setTotalPages(pageTeam.getTotalPages());
		teamResponse.setLastPage(pageTeam.isLast());

		return teamResponse;
	}

	@Override
	public List<TeamDto> getTeamDataByName(String keyword) {

		List<Team> teams = teamRepository.findByTeamName("%" + keyword + "%");

		List<TeamDto> teamDtos = teams.stream().map((team) -> this.modelMapper.map(team, TeamDto.class))
				.collect(Collectors.toList());

		return teamDtos;
	}

	@Override
	public TeamDto updateTeamDataById(TeamDto teamDto, Integer teamId) {

		Team team = teamRepository.findById(teamId)
				.orElseThrow(() -> new ResourceNotFoundException("Team", "Team Id", teamId));

		team.setTeamName(teamDto.getTeamName());
		team.setTeamBudget(teamDto.getTeamBudget());
		team.setTeamDof(teamDto.getTeamDof());

		Team updateTeamDataById = teamRepository.save(team);

		return modelMapper.map(updateTeamDataById, TeamDto.class);
	}

	@Override
	public void deleteTeamDataById(Integer teamId) {

		Team team = teamRepository.findById(teamId)
				.orElseThrow(() -> new ResourceNotFoundException("Team", "Team Id", teamId));

		teamRepository.delete(team);
	}
}
