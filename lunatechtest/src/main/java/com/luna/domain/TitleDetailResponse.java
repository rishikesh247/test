package com.luna.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TitleDetailResponse {
	private String titleType;
	private String primarytitle;
	private String originaltitle;
	private boolean isadult;
	private Integer startyear;
	private Integer endyear;
	private Integer runtimeminutes;
	private String genres;
	private List<PrimaryCastCrewDetail>PrimaryCastCrewDetails;
}
