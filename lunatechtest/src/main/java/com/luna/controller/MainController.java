package com.luna.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luna.domain.Response;
import com.luna.domain.TitleDetailResponse;
import com.luna.domain.TopRatedMovieDetail;
import com.luna.service.ImdbService;

@RestController
@RequestMapping("/luna")
public class MainController {

	@Autowired
	private ImdbService imdbService;

	private String DEFAULT_NAME ="Kevin Bacon";
	
	@GetMapping("/title")
	public Response getTitleDetail(@RequestParam String titleName) {
		
		try {
			titleName = titleName.trim();
			if(StringUtils.isBlank(titleName)) {
				String message = "Title name can't be blank";
				return new Response(message, false, HttpStatus.OK);
			}
			TitleDetailResponse titleDetail = imdbService.getTitleDetail(titleName);
			
			if(titleDetail != null) {
				return new Response(titleDetail, true, HttpStatus.OK);
				
			}else {
				return new Response(String.format("Title detail not found for title name %s", titleName), false, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			String message="Technical error";
			return new Response(message, false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/generes")
	public Response getTopRatedMovie(@RequestParam String generesName) {
		
		try {
			generesName= generesName.trim();
			if(StringUtils.isBlank(generesName)) {
				String message = "Generes name can't be blank";
				return new Response(message, false, HttpStatus.OK);
			}
			List<TopRatedMovieDetail> topRatedMovies = imdbService.getTopRatedMovies(generesName);
			
			if(topRatedMovies != null) {
				return new Response(topRatedMovies, true, HttpStatus.OK);
				
			}else {
				return new Response(String.format("Movies not found for generes %s", generesName), false, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			String message="Technical error";
			return new Response(message, false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/dos")
	public Response getDegreeOfSeperation(@RequestParam String name) {
		try {
			String name2 = DEFAULT_NAME;
			name = name.trim();
			if(StringUtils.isBlank(name)) {
				String message = "Name can't be blank" ;
				return new Response(message, false, HttpStatus.OK);
			}
			
			if(StringUtils.equalsIgnoreCase(name, name2)) {
				return new Response(String.format("Degree of separation for given name %s and default name %s is 0", name, name2), true, HttpStatus.OK);
			}

			Set<String> name1Set = imdbService.getNconstFromName(name);
			
			if(name1Set.isEmpty()) {
				return new Response(String.format("Name detail not found for given name %s", name), false, HttpStatus.OK);
			}
			Set<String> name2Set = imdbService.getNconstFromName(name2);
			if(name2Set.isEmpty()) {
				return new Response(String.format("Name detail not found for default name %s", name2), false, HttpStatus.OK);
			}
			
			List<String> collect = name1Set.stream().filter(name2Set::contains).collect(Collectors.toList());

			if(!collect.isEmpty()) {
				return new Response(String.format("Degree of separation for given name %s and default name %s is 0", name, name2), true, HttpStatus.OK);
			}
			Integer degreeOfSeperation = imdbService.getDegreeOfSeperation(name1Set, name2Set);
			if(degreeOfSeperation > 0) {
				return new Response(String.format("Degree of separation for given name %s and default name %s is %s", name, name2, degreeOfSeperation), true, HttpStatus.OK);
			}else {
				return new Response(String.format("Degree of separation for given name %s and default name %s is either greater than 6 or not related to each other", name,name2), true, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			String message="Technical error";
			return new Response(message, false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
