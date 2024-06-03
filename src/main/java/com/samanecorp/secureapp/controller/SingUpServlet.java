
package com.samanecorp.secureapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.secureapp.dto.AccountUserDto;
import com.samanecorp.secureapp.service.LoginService;


@WebServlet(name ="singup",value="/singup")
public class SingUpServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private LoginService loginService;
	private final String LOGIN_PAGE = "index.jsp";
	
	public SingUpServlet() {
		super();
	}
	
	@Override
	public void init() throws ServletException {
		
		loginService = new LoginService();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		loadIndex(null,req, resp);
	}

	private void loadIndex(String message, HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
		req.setAttribute("message", message);
		req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
		
	}

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("mail");
		String password = req.getParameter("pwd");
		logger.info("Tentative d'inscription avec{}",email);
		String message = null;
		
		try {
			AccountUserDto accountUserDto = new AccountUserDto();
			accountUserDto.setEmail(email);
			boolean result = loginService.save(accountUserDto);
			accountUserDto.setPassword(password);
			if(result) {
				message ="information ajoutées dans la base de données";
				logger.info("{}",message);
			}
		} catch (Exception e) {
			message = "information non ajoutées";
			logger.error("{}",message);
		}
		loadIndex(message,req,resp);
	}
}

