package com.ss.spring.member.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/*
 * 마이바티스에서 컬럼의 옵션 형태로 값이 들어가는 경우 Type을 지원하기 위한 Helper
 * 
 * 취미: 운동, 영화보기, 음악감상
 * - DB에 insert할 경우 String[] → String wjsghks
 * - DB에서 추출할 때 String → String[] 전환
 * 
 */
public class MemberTypeHandler implements TypeHandler<String[]>{

	// 자바 → DB로 전송
	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		// PreparedStatement java객체를 sql 쿼리의 매개변수로 설정
		// join() 배열의 쉼표를 기준으로 하나의 문자열로 변환하여 sql 쿼리의 i 번째 매개변수로 설정을 한다.
		if(parameter != null) {
			ps.setString(i, String.join(",", parameter));
		} else {
			ps.setString(i, "");
		}
	}

	// DB → 자바로 전송
	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getString(columnName).split(",");
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getString(columnIndex).split(",");
	}

	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return null;
	}

}
