
package com.samanecorp.secureapp.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.secureapp.dto.AccountUserDto;
import com.samanecorp.secureapp.service.LoginService;

@WebServlet(name="login", value="/login")

public class LoginServlet extends HttpServlet {
	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private LoginService loginService;
	private final String LOGIN_PAGE = "index.jsp";
	private final String WELCOME_PAGE ="welcome.jsp";
  
	@Override
	public void init() throws ServletException {
		loginService = new LoginService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		loadIndex(null,req, resp);
	}
	
	private void loadIndex(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("message",message);
		req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		logger.info("Tantative de connexion avec {} et {}", email, password);
		
		try {
			Optional<AccountUserDto> accountUserDto = loginService.logException(email,password);
			AccountUserDto userDto = accountUserDto.get();
			req.getSession().setAttribute("username",userDto.getEmail());
			resp.sendRedirect(WELCOME_PAGE);
			
		} catch (Exception e) {
			String message ="Inforation de connexion incorrecte";
			logger.error("{}", message);
			loadIndex(message,req,resp);
			
		}
	}
	
}
