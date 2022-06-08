package com.otm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otm.model.dto.TeamDto;
import com.otm.payloads.ApiResponse;
import com.otm.payloads.AppConstants;
import com.otm.payloads.TeamResponse;
import com.otm.service.TeamService;

@RestController
@RequestMapping("/api")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@PostMapping("/saveTeamData")
	public ResponseEntity<TeamDto> saveTeamData(@Valid @RequestBody TeamDto teamDto) {
		TeamDto saveCustData = teamService.saveTeamData(teamDto);
		return new ResponseEntity<TeamDto>(saveCustData, HttpStatus.CREATED);
	}

	@GetMapping("/getTeamDataById/{teamId}")
	public ResponseEntity<TeamDto> getTeamDataById(@PathVariable("teamId") Integer teamId) {
		TeamDto getTeamDataById = teamService.getTeamDataById(teamId);
		return new ResponseEntity<TeamDto>(getTeamDataById, HttpStatus.OK);
	}

	@GetMapping("/getAllTeamData/")
	public ResponseEntity<TeamResponse> getAllTeamData(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		TeamResponse getAllTeamData = teamService.getAllTeamData(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<TeamResponse>(getAllTeamData, HttpStatus.OK);
	}

	@GetMapping("/getTeamDataByName/{keyword}")
	public ResponseEntity<List<TeamDto>> getTeamDataByName(@PathVariable("keyword") String keyword) {

		List<TeamDto> getTeamDataByName = teamService.getTeamDataByName(keyword);

		return new ResponseEntity<List<TeamDto>>(getTeamDataByName, HttpStatus.OK);
	}

	@PutMapping("/updateTeamDataById/{teamId}")
	public ResponseEntity<TeamDto> updateTeamDataById(@Valid @RequestBody TeamDto teamDto,
			@PathVariable("teamId") Integer teamId) {
		TeamDto updateTeamDataById = teamService.updateTeamDataById(teamDto, teamId);
		return new ResponseEntity<TeamDto>(updateTeamDataById, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteTeamDataById/{teamId}")
	public ResponseEntity<ApiResponse> deleteTeamDataById(@PathVariable("teamId") Integer teamId) {
		teamService.deleteTeamDataById(teamId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully", true),
				HttpStatus.OK);
	}
}
