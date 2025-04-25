package com.app.service.aop;

import java.net.InetAddress;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.app.service.dao.AuditDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Aspect
@Component
public class LoggingAspect {

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    AuditDao auditDao;

    @Value("${spring.application.name}")
    private String serviceName;
    @Value("${application.code}")
    private String applCode;
    @Value("${service.id}")
    private Integer serviceId;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postAction() {
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)"
            + " || within(@org.springframework.stereotype.Service *)"
            + " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    @Pointcut("execution(* com.app.service.contoller.ServiceApi.*(..))")
    public void anyApplicationService() {

    }

    @Around("anyApplicationService()")
    public Object applicationLogger(ProceedingJoinPoint joinPoint) {
        String payload = null;
        Object response = null;
        Map<String, Object> inputParams = new HashMap<String, Object>();
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            URI uri = new URI(request.getRequestURL().toString());
            System.out.println("joinPoint.getArgs()[0] --XX-- " + joinPoint.getArgs()[0]);
            if (joinPoint.getArgs().length > 0 && joinPoint.getArgs()[0] != null) {
                System.out.println("joinPoint.getArgs()[0] --XX-- " + joinPoint.getArgs()[0].toString());
                payload = joinPoint.getArgs()[0].toString();
            } else {
                payload = "No Input Recieved";
            }
            System.out.println("payload --XX-- " + payload);
            log.info(" for the Method : " + uri.getPath() + " Request received : {}", payload);
            Date beforeDate = new Date();
            response = joinPoint.proceed();
            log.info(" for the Method :" + joinPoint.getSignature().getName() + " Response received : {}", response);

            //set audit method parameters.
            String clientIp = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR");  //Client IP Address
            InetAddress inetAddress = InetAddress.getByName(request.getRemoteAddr());
            String f5Ip = inetAddress.getHostAddress(); //F5Ip IP Address
            String serverIp = InetAddress.getLocalHost().getHostAddress(); //Server IP Address
            ResponseEntity responseEntity = (ResponseEntity) response;
            String notes = "Status is " + responseEntity.getStatusCodeValue() + ", "
                    + responseEntity.getStatusCode().name() + " for call service " + joinPoint.getSignature().getName()
                    + " - Service URL: " + uri.getPath();
            inputParams.put("p_service_name", joinPoint.getSignature().getName());
            inputParams.put("p_APP_CODE", applCode);
            inputParams.put("p_client_ip", request.getRemoteAddr());
            inputParams.put("p_pInput", payload);
            inputParams.put("p_poutput", response.toString());
            inputParams.put("p_status_code", responseEntity.getStatusCodeValue());
            inputParams.put("p_status_desc", responseEntity.getStatusCode().name());
            inputParams.put("p_full_status_desc", " ");
            inputParams.put("p_note", notes);
            inputParams.put("p_service_id", serviceId);
            inputParams.put("P_SERVER_IP", serverIp);
            inputParams.put("P_F5", f5Ip);
            inputParams.put("P_SOURCE_TYPE", getDeviceType(request));
            inputParams.put("p_call_date", beforeDate);
            inputParams.put("p_before_call_date", beforeDate);
            inputParams.put("p_after_call_date", new Date());
            inputParams.put("P_USER_CODE", "");
            inputParams.put("P_DIR_CODE", null);
            Object auditSerial = auditDao.addApiAudit(inputParams);
            log.info(joinPoint.getSignature().getName() + "Service Call Audit Serial is: " + auditSerial);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            log.info("@Around applicationLogger done...");
        }
        return response;
    }

    @AfterThrowing(pointcut = "anyApplicationService() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in ", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                e.getCause() != null ? e.getMessage() : "NULL");
    }

    public String getDeviceType(HttpServletRequest request) {
        String deviceType = "curl/Web-Browser";
        if (request != null && request.getHeader("User-Agent") != null) {
            String userAgent = request.getHeader("User-Agent");
            String[] agentVals = userAgent.split("/");
            if (userAgent.contains("Mobi")) {
                deviceType = agentVals[0] + "/Mobile";
            } else {
                deviceType = agentVals[0] + "/Web-Browser";
            }
        }
        return deviceType;
    }

}