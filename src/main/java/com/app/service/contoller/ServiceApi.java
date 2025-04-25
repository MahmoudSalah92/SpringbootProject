package com.app.service.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.dto.Request;
import com.app.service.srinterface.IAtsService;

@RestController
@RequestMapping("/apps/ats/services/")
public class ServiceApi {

    @Autowired
    IAtsService atsService;

    // Add ATS new request.
    @PostMapping("/addAtsEventLog")
    public ResponseEntity<Map<String, Object>> insAtsEventLog(@RequestBody @Valid Request request) throws Throwable {
        System.out.println("Request xxxxxxxxxxxxxxxxx: " + request.toString());
        Map<String, Object> resultSet = new HashMap<String, Object>();
        Map<String, Object> statusDetails = new HashMap<String, Object>();
        String statusCode = "";
        resultSet = atsService.insAtsEventLog(request);
        System.out.println("StatusDetails :: " + resultSet.get("StatusDetails"));
        statusDetails = (Map<String, Object>) resultSet.get("StatusDetails");
        if (statusDetails != null && !statusDetails.isEmpty() && statusDetails.get("StatusCode") != null) {
            statusCode = (String) statusDetails.get("StatusCode");
            System.out.println("statusCode :: " + statusCode);
            if (statusCode != null && statusCode.equals("0")) {
                return ResponseEntity.ok().body(resultSet);
            } else if (statusCode != null && statusCode.equals("E0001")) {
                return ResponseEntity.internalServerError().body(resultSet);
            } else if (statusCode != null && statusCode.equals("E0002")) {
                return ResponseEntity.badRequest().body(resultSet);
            } else {
                return ResponseEntity.ok().body(resultSet);
            }
        } else {
            return ResponseEntity.badRequest().body(resultSet);
        }
    }
}
