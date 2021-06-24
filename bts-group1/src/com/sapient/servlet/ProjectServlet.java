package com.sapient.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapient.dao.BugService;
import com.sapient.dao.IBugService;
import com.sapient.dao.IProjectService;
import com.sapient.dao.ProjectService;
import com.sapient.email.SendEmail;
import com.sapient.model.Bug;
import com.sapient.model.Project;

@WebServlet("/")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IProjectService projectService = new ProjectService();
	IBugService bugService = BugService.getInstance();

	public ProjectServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {
		case "/newbug":
			showBugNewForm(request, response);
			break;
		case "/newproject":
			showProjectNewForm(request, response);
			break;
		case "/editbug":
			showEditForm(request, response);
			break;
		case "/listbug":
			listbug(request, response);
			break;
		default:
			listproject(request, response);
			break;
		}

	}

	private void updatebug(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		int project_id = Integer.parseInt(request.getParameter("project_id"));
		String name = request.getParameter("name");

		String description = request.getParameter("description");
		String email = request.getParameter("email");
		String owner = request.getParameter("owner");
		String priority = request.getParameter("priority");
		String status = request.getParameter("status");
		String bugtype = request.getParameter("bugtype");
		Bug updatebug = new Bug(id, project_id, name, description, email, owner, priority, status, bugtype);
		bugService.updateBug(updatebug);
		try {
			SendEmail.sendMail(email, owner, name, description, priority, status);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		response.sendRedirect("listbug");

	}

	private void showProjectNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("project-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listproject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Project> listproject = projectService.selectAllProjects();
		request.setAttribute("listproject", listproject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("project-list.jsp");
		dispatcher.forward(request, response);

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Bug existingBug = bugService.selectBug(id);
		System.out.println(existingBug);
		List<Project> projectlist = projectService.selectAllProjects();
		request.setAttribute("projectlist", projectlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("bug-form.jsp");
		request.setAttribute("bug", existingBug);
		dispatcher.forward(request, response);

	}

	private void showBugNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Project> projectlist = projectService.selectAllProjects();
		request.setAttribute("projectlist", projectlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("bug-form.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {
		case "/insertbug":
			insertbug(request, response);
			break;
		case "/insertproject":
			insertproject(request, response);
			break;
		case "/updatebug":
			try {
				updatebug(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			listproject(request, response);
			break;
		}
	}

	private void insertproject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Project newproject = new Project(name, description);
		projectService.save(newproject);
		response.sendRedirect("listproject");

	}

	private void insertbug(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int project_id = Integer.parseInt(request.getParameter("project_id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String email = request.getParameter("email");
		String owner = request.getParameter("owner");
		String priority = request.getParameter("priority");
		String status = request.getParameter("status");
		String bugtype = request.getParameter("bugtype");

		Bug newbug = new Bug(project_id, name, description, email, owner, priority, status, bugtype);
		bugService.save(newbug);
		try {
			SendEmail.sendMail(email, owner, name, description, priority, status);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		response.sendRedirect("listbug");
	}

	private void listbug(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Bug> listbug = bugService.selectAllBugs();
		request.setAttribute("listbug", listbug);
		RequestDispatcher dispatcher = request.getRequestDispatcher("bug-list.jsp");
		dispatcher.forward(request, response);
	}

}
