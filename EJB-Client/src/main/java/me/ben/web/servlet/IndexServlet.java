package me.ben.web.servlet;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.ben.ejb.test.HelloWorldRemote;
/**
 * EJB调用测试
 * 
 * @author Administrator
 * 
 */
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 这里调用完JSP之后
		HttpSession session = req.getSession(true);
		session.setAttribute("strFromEJB", callEJB());
		String contextPath = req.getServletContext().getContextPath();
		resp.sendRedirect(contextPath + "/index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	private String callEJB(){
		Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		try {
			Context context = new InitialContext(jndiProperties);

			final String appName = "";
			final String moduleName = "EJB-TestProject";	//工程名
			final String distinctName = "";
			//从server启动日志得到的信息:
			/*
			    java:global/EJB-TestProject/HelloWorld!me.ben.eib.test.HelloWorldRemote
				java:app/EJB-TestProject/HelloWorld!me.ben.eib.test.HelloWorldRemote
				java:module/HelloWorld!me.ben.eib.test.HelloWorldRemote
				java:jboss/exported/EJB-TestProject/HelloWorld!me.ben.eib.test.HelloWorldRemote
				java:global/EJB-TestProject/HelloWorld!me.ben.eib.test.HelloWorldLocal
				java:app/EJB-TestProject/HelloWorld!me.ben.eib.test.HelloWorldLocal
				java:module/HelloWorld!me.ben.eib.test.HelloWorldLocal
			 */
			Object obj = context.lookup("ejb:" + appName + "/" + moduleName
					+ "/" + distinctName
					+ "/HelloWorld!me.ben.ejb.test.HelloWorldRemote");

			HelloWorldRemote hwr = (HelloWorldRemote) obj;
			String result = hwr.sayHello("XiXia");
			return result;
		} catch (NamingException e) {
			e.printStackTrace();
			return "调用失败";
		}
	}
}
