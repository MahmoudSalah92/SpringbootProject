package com.app.service.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class AuditDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Object addApiAudit(Map<String, Object> datafields) {
		Object result = null;
		Map<String, Object> outParams = new HashMap<String, Object>();
		try {
			jdbcTemplate.setResultsMapCaseInsensitive(true);
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("CMNV3")
					.withFunctionName("add_SERVICE_aud_ALL_F")
					.declareParameters(new SqlParameter("P_SERVICE_NAME", Types.VARCHAR),
							new SqlParameter("p_APP_CODE", Types.VARCHAR),
							new SqlParameter("P_CLIENT_IP", Types.VARCHAR), new SqlParameter("P_PINPUT", Types.CLOB),
							new SqlParameter("P_POUTPUT", Types.CLOB), new SqlParameter("P_STATUS_CODE", Types.NUMERIC),
							new SqlParameter("P_STATUS_DESC", Types.VARCHAR),
							new SqlParameter("P_FULL_STATUS_DESC", Types.CLOB),
							new SqlParameter("P_NOTE", Types.VARCHAR), new SqlParameter("P_SERVICE_ID", Types.NUMERIC),
							new SqlParameter("P_SERVER_IP", Types.VARCHAR), new SqlParameter("P_F5", Types.VARCHAR),
							new SqlParameter("P_SOURCE_TYPE", Types.VARCHAR),
							new SqlParameter("P_CALL_DATE", Types.DATE),
							new SqlParameter("P_BEFORE_CALL_DATE", Types.DATE),
							new SqlParameter("P_AFTER_CALL_DATE", Types.DATE),
							new SqlParameter("P_USER_CODE", Types.VARCHAR),
							new SqlParameter("P_DIR_CODE", Types.NUMERIC));
			MapSqlParameterSource params = new MapSqlParameterSource(datafields);
			outParams.putAll(simpleJdbcCall.execute(params));
			if (outParams != null && outParams.size() > 0) {
				result = outParams.get("return");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
