package com.loveoffashion.core.servlets;


import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;


@Component(service = Servlet.class, property = {
		Constants.SERVICE_DESCRIPTION + "= Tenant Content Fragment Json Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/mycustomservlet",
		"sling.servlet.extensions=json" })
@ServiceDescription("My Custom Servlet")
public class MyCustomServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 9138623700967024120L;

	/*@Reference
	private ContentFragmentService contentFragmentService;*/

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text");

		String jsonString = "My Servlet Response - Got the ajax request!";
		
		
		resp.getWriter().write(jsonString);
		resp.getWriter().close();

	}
}