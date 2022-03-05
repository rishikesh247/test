package com.luna.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImdbDaoImpl {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getTitleDetail(String titleName) {
		String titleNameInLowerCase = StringUtils.lowerCase(titleName);
		final String query = "SELECT tb.titletype, tb.primarytitle, tb.originaltitle, tb.isadult, tb.startyear, tb.endyear, tb.runtimeminutes, tb.genres, nb.primaryname, tp.category, tp.job, tp.characters FROM public.title_basics tb INNER JOIN public.title_principals tp on tb.tconst=tp.tconst INNER JOIN public.name_basics nb ON nb.nconst=tp.nconst where lower(tb.primarytitle)= ? or lower(tb.originaltitle)= ?"; 
		return jdbcTemplate.queryForList(query, titleNameInLowerCase, titleNameInLowerCase );
	}
	public List<Map<String, Object>> getTopRatedMovies(String generes) {
		String generesInLowerCase = StringUtils.lowerCase(generes);
		final String query ="select temp1.primarytitle, temp1.originaltitle,temp1.averagerating from ( Select tb.primarytitle, tb.originaltitle, tr.averagerating, dense_rank() over ( order by tr.averagerating desc ) rank1  	 FROM public.title_basics tb inner join public.title_ratings tr on tb.tconst=tr.tconst where lower(tb.genres) = ? ) temp1 where rank1=1";
		return jdbcTemplate.queryForList(query, generesInLowerCase);
	}
	public List<Map<String, Object>> getNconstFromName(String name) {
		String nameInLowerCase = StringUtils.lowerCase(name);
		final String query ="select nconst from public.name_basics where lower(primaryname)=?";
		return jdbcTemplate.queryForList(query, nameInLowerCase);
	}
	public List<Map<String, Object>> getConnectedUserDetail(String userId) {
		String userIdInLowerCase = StringUtils.lowerCase(userId);
		final String query ="select pb_t.nconst from public.title_principals pb join public.title_principals pb_t on pb.tconst = pb_t.tconst where pb.nconst=? and pb_t.nconst !=?";
		return jdbcTemplate.queryForList(query, userIdInLowerCase, userIdInLowerCase);
	}
}
