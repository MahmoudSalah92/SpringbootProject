package com.app.service.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.service.dao.RequestDao;
import com.app.service.dto.Request;
import com.app.service.srinterface.IAtsService;

@Service
public class AtsService implements IAtsService {

	@Autowired
	RequestDao requestDao;

	@Override
	public Map<String, Object> insAtsEventLog(Request request) {
		Map<String, Object> outputData = new HashMap<String, Object>();
		Map<String, Object> dataSet = new HashMap<String, Object>();
		outputData = requestDao.insAtsEventLog(request);
		System.out.println("xxx :: " + outputData);
		if (outputData.size() > 0) {
			if (outputData.get("output") != null && Integer.parseInt(outputData.get("output").toString()) == -1) {

				dataSet = returnJsonDataTemp(2, null, "E0001", outputData.get("Message").toString());
			} else {
				dataSet = returnJsonDataTemp(1, outputData, null, null);
			}
		} else {
			dataSet = returnJsonDataTemp(2, null, "E0002", "No Request Data Found.");
		}
		return dataSet;
	}

	// returnJsonDataTemp --> (main template of return json data success of fail)
	public Map<String, Object> returnJsonDataTemp(int flag, Map<String, Object> outputData, String errorCode,
			String errorMsg) {
		Map<String, Object> tempJsonData = new HashMap<String, Object>();
		Map<String, Object> statusDetails = new HashMap<String, Object>();
		if (flag == 1) { // success Process
			statusDetails.put("StatusCode", "0");
			statusDetails.put("StatusMessage", "Success Process");
			tempJsonData.put("OutputData", outputData);
		} else { // 2 fail
			statusDetails.put("StatusCode", errorCode);
			statusDetails.put("StatusMessage", errorMsg);
		}
		tempJsonData.put("StatusDetails", statusDetails);
		return tempJsonData;
	}

}
