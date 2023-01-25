package com.jatin.test;

//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//import com.jatin.web.UserServlet;

public class testApplication {
	
//	private HttpServletRequest request;
//	private HttpServletResponse response;
//	private StringWriter response_writer;
//
//	@InjectMocks
//	private UserServlet servlet = new UserServlet();
//	
//	public void Instantiation() {
//		request= mock(HttpServletRequest.class);
//		response= mock(HttpServletRequest.class);
//		response_writer = new StringWriter();
//		when(response.getWriter()).thenReturn(new PrintWriter(response_writer));
//	}
//	@Test
//	public void newCallInstantiation() {
//		when(request.getServletPath()).thenAnswer(new String("/new"));
//		verify(servlet, times(1)).showNewForm(request,response);
//	}
//	@Test
//	public void insertCallInstantiation() {
//		when(request.getServletPath()).thenAnswer(new String("/insert"));
//		verify(servlet, times(1)).insertUser(request,response);
//	}
//	@Test
//	public void deleteCallInstantiation() {
//		when(request.getServletPath()).thenAnswer(new String("/delete"));
//		verify(servlet, times(1)).deleteUser(request,response);
//	}
//	@Test
//	public void editCallInstantiation() {
//		when(request.getServletPath()).thenAnswer(new String("/edit"));
//		verify(servlet, times(1)).showEditForm(request,response);
//	}
//	@Test
//	public void updateCallInstantiation() {
//		when(request.getServletPath()).thenAnswer(new String("/update"));
//		verify(servlet, times(1)).updateUser(request,response);
//	}
//	@Test
//	public void defaultCallInstantiation() {
//		when(request.getServletPath()).thenAnswer(new String("/"));
//		verify(servlet, times(1)).listUser(request,response);
//	}
//	
//	@Test
//	public void test() {
//		Instantiation();
//		newCallInstantiation();
//		insertCallInstantiation();
//		deleteCallInstantiation();
//		editCallInstantiation();
//		updateCallInstantiation();
//		defaultCallInstantiation();
//	}

}
