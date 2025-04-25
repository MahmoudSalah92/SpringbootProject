package com.app.service.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.app.service.dto.Request;

@Component
public class RequestDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Map<String, Object> insAtsEventLog(Request request) {
		Map<String, Object> outParams = new HashMap<String, Object>();
		Map<String, Object> resultset = new HashMap<String, Object>();
		SimpleJdbcCall stmCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("ATSNEW")
				.withCatalogName("ats_entma_pkg").withProcedureName("INS_TB_EVENT_LOG");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("in_NUSERID", request.getIn_NUSERID());
		params.addValue("in_NDATETIME", request.getIn_NDATETIME());
		params.addValue("in_work_long_pos", request.getIn_work_long_pos());
		params.addValue("in_work_lat_pos", request.getIn_work_lat_pos());
		params.addValue("in_ATT_long_pos", request.getIn_ATT_long_pos());
		params.addValue("in_ATT_lat_pos", request.getIn_ATT_lat_pos());
		outParams.putAll(stmCall.execute(params));
		if (outParams != null && outParams.size() > 0) {
			resultset.put("output", outParams.get("P_OUT_ERR_NO"));
			resultset.put("Message", outParams.get("P_OUT_ERR_M"));
		}
		return resultset;
	}

}
