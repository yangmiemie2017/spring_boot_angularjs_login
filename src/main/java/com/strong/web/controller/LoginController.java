package com.strong.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.strong.model.entity.UserToken;
import com.strong.web.model.LoginUser;
import com.strong.web.model.LoginResponse;
import com.strong.exception.BusinessException;
import com.strong.exception.InputValidationException;
import com.strong.exception.ErrorDetail;
import com.strong.service.LoginService;

import com.strong.web.validator.LoginRequestValidator;

@RestController
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new LoginRequestValidator());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> loginAction(@RequestBody @Valid LoginUser loginUser, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result.getAllErrors());
        }

        logger.info("loginAction -- new login request for user - " + loginUser.getLoginName());

        com.strong.model.entity.UserToken token = loginService.authentication(loginUser.getLoginName(), loginUser.getPassword());
        
        if (token != null) {
    		return ResponseEntity
    				.status(HttpStatus.OK)
    				.body(new LoginResponse(loginUser.getLoginName(), token));            
        } else {
            ErrorDetail error = new ErrorDetail();
            error.setCode("LoginFail");
            error.setField(ErrorDetail.DEFAULT_CODE);
            error.setMessage("Login fail, please check id & password and retry");
            throw new BusinessException(error);
        }
    }

    @RequestMapping(value = "/logoff", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    //@GetMapping(value = "/logoff")    
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> logoffAction() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserToken token = (UserToken) authentication.getPrincipal();
        loginService.logoff(token.getId());
        
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("logoffSuccess");
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBusinessError(BusinessException e) {
        return ResponseEntity
        		.status(HttpStatus.BAD_REQUEST)
        		.body("BusinessException occurred - " + e.getMessage());
    }

}
