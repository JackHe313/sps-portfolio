package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<String> data = new ArrayList<String>();
    data.add("Hello! I am Jack!");
    data.add("Nice to meet you");
    data.add("Thanks for visiting");
    String json= convertToJsonUsingGson(data);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  private String convertToJsonUsingGson(ArrayList<String> data ) {
    Gson gson = new Gson();
    String json = gson.toJson(data);
    return json;
  }
}

