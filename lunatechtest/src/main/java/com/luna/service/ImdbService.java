package com.luna.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luna.dao.Constant;
import com.luna.dao.ImdbDaoImpl;
import com.luna.domain.PrimaryCastCrewDetail;
import com.luna.domain.TitleDetailResponse;
import com.luna.domain.TopRatedMovieDetail;

@Service
public class ImdbService {

	@Autowired
	private ImdbDaoImpl imdbDaoImpl;
	
	public TitleDetailResponse getTitleDetail(String titleName) {
		List<Map<String, Object>> titleDetailList = imdbDaoImpl.getTitleDetail(titleName);
		if(!titleDetailList.isEmpty()) {
			boolean titleDeatil = false;
			TitleDetailResponse titleDetailResponse = new TitleDetailResponse();
			List<PrimaryCastCrewDetail>primaryCastCrewDetails = new ArrayList<PrimaryCastCrewDetail>();
			for (final Map<String, Object> map : titleDetailList) {
				if(!titleDeatil) {
					titleDetailResponse.setTitleType(map.get(Constant.TITLE_TYPE) != null ? String.valueOf(map.get(Constant.TITLE_TYPE)) : null);
					titleDetailResponse.setStartyear(map.get(Constant.START_YEAR) != null ? (Integer)map.get(Constant.START_YEAR) : null);
					titleDetailResponse.setEndyear(map.get(Constant.END_YEAR) != null ? (Integer)map.get(Constant.END_YEAR) : null);
					titleDetailResponse.setRuntimeminutes(map.get(Constant.RUNTIME_MINUTES) != null ? (Integer)map.get(Constant.RUNTIME_MINUTES) : null);
					titleDetailResponse.setPrimarytitle(map.get(Constant.PRIMARY_TITLE) != null ? String.valueOf(map.get(Constant.PRIMARY_TITLE)) : null);
					titleDetailResponse.setOriginaltitle(map.get(Constant.ORIGINAL_TITLE) != null ? String.valueOf(map.get(Constant.ORIGINAL_TITLE)) : null);
					titleDetailResponse.setIsadult(map.get(Constant.IS_ADULT) != null ? Boolean.valueOf( String.valueOf(map.get(Constant.IS_ADULT))) : null);
					titleDetailResponse.setGenres(map.get(Constant.GENRES) != null ? String.valueOf(map.get(Constant.GENRES)) : null);
					titleDeatil = true;				
				}
				
				PrimaryCastCrewDetail primaryCastCrewDetail = new PrimaryCastCrewDetail();
				primaryCastCrewDetail.setPrimaryname(map.get(Constant.PRIMARY_NAME) != null ? String.valueOf(map.get(Constant.PRIMARY_NAME)) : null);
				primaryCastCrewDetail.setCategory(map.get(Constant.CATEGORY) != null ? String.valueOf(map.get(Constant.CATEGORY)) : null);
				primaryCastCrewDetail.setJob(map.get(Constant.JOB) != null ? String.valueOf(map.get(Constant.JOB)) : null);
				primaryCastCrewDetail.setCharacters(map.get(Constant.CHARACTERS) != null ? String.valueOf(map.get(Constant.CHARACTERS)) : null);
				primaryCastCrewDetails.add(primaryCastCrewDetail);
			}
			titleDetailResponse.setPrimaryCastCrewDetails(primaryCastCrewDetails);
			return titleDetailResponse;
		}
		return null;
	}
	
	public List<TopRatedMovieDetail>  getTopRatedMovies(String generes) {
		List<Map<String, Object>> topRatedMoviesList = imdbDaoImpl.getTopRatedMovies(generes);
		if (!topRatedMoviesList.isEmpty()) {
			List<TopRatedMovieDetail> primaryCastCrewDetails = new ArrayList<TopRatedMovieDetail>();
			for (final Map<String, Object> map : topRatedMoviesList) {

				TopRatedMovieDetail topRatedMovieDetail = new TopRatedMovieDetail();
				topRatedMovieDetail.setOriginalTitle(
						map.get(Constant.ORIGINAL_TITLE) != null ? String.valueOf(map.get(Constant.ORIGINAL_TITLE)) : null);
				topRatedMovieDetail.setPrimaryTitle(
						map.get(Constant.PRIMARY_TITLE) != null ? String.valueOf(map.get(Constant.PRIMARY_TITLE)) : null);
				topRatedMovieDetail.setAveragerating(
						map.get(Constant.AVERAGE_RATING) != null ? String.valueOf(map.get(Constant.AVERAGE_RATING)) : null);

				primaryCastCrewDetails.add(topRatedMovieDetail);
			}
			return primaryCastCrewDetails;
		}
		return null;
	}
	
	public Set<String>  getNconstFromName(String name) {
		List<Map<String, Object>> userIdList = imdbDaoImpl.getNconstFromName(name);
		Set<String> nameSet = new HashSet<String>();
		if (!userIdList.isEmpty()) {
			for (final Map<String, Object> map : userIdList) {
				if(map.get(Constant.NCONST) != null) {
					nameSet.add( String.valueOf(map.get(Constant.NCONST)) );
				}
			}
		}
		return nameSet;
	}
	public Integer  getDegreeOfSeperation(Set<String> name1Set, Set<String> name2Set) {
		// Degree Of Seperation 1
		Set<String> dos1 = new HashSet<String>();
		Integer dos = getDos(name1Set, name2Set, dos1, 1);
		if(dos > 0) {
			return dos;
		}
		if(dos1.isEmpty()) {
			return -1;
		}
		// Degree Of Seperation 2
		Set<String> dos2 = new HashSet<String>();
		dos = getDos(dos1, name2Set, dos2, 2);
		if(dos > 0) {
			return dos;
		}
		if(dos2.isEmpty()) {
			return -1;
		}
		// Degree Of Seperation 3
		Set<String> dos3 = new HashSet<String>();
		dos = getDos(dos2, name2Set, dos3, 3);
		if(dos > 0) {
			return dos;
		}
		if(dos3.isEmpty()) {
			return -1;
		}
		// Degree Of Seperation 4
		Set<String> dos4 = new HashSet<String>();
		dos = getDos(dos3, name2Set, dos4, 4);
		if(dos > 0) {
			return dos;
		}
		if(dos4.isEmpty()) {
			return -1;
		}
		// Degree Of Seperation 5
		Set<String> dos5 = new HashSet<String>();
		dos = getDos(dos4, name2Set, dos5, 5);
		if(dos > 0) {
			return dos;
		}
		if(dos5.isEmpty()) {
			return -1;
		}
		// Degree Of Seperation 6
		Set<String> dos6 = new HashSet<String>();
		dos = getDos(dos5, name2Set, dos6, 6);
		if(dos > 0) {
			return dos;
		}
		if(dos6.isEmpty()) {
			return -1;
		}
		return -1;
	}
	
	private Integer getDos(Set<String> currentNameSet, Set<String> resultCheckNameSet, Set<String> nextNameSet, Integer dos ) {
		
		for(String nameId : currentNameSet) {
			List<Map<String, Object>> connectedUserDetailList = imdbDaoImpl.getConnectedUserDetail(nameId);
			if (!connectedUserDetailList.isEmpty()) {
				for (final Map<String, Object> map : connectedUserDetailList) {
					if(map.get(Constant.NCONST) != null) {
						String nm = String.valueOf(map.get(Constant.NCONST));
						if(resultCheckNameSet.contains(nm)) {
							return dos;
						}
						nextNameSet.add(nm);
					}
				}
			}
		}
		return -1;
	}
	
}
