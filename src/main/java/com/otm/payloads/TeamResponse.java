package com.otm.payloads;

import java.util.List;

import com.otm.model.dto.TeamDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TeamResponse {

	private List<TeamDto> content;

	private int pageNumber;

	private int PageSize;

	private long totalElements;

	private int totalPages;

	private boolean lastPage;

}
