package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String error = validateInput(request);
    if (error.isEmpty()) {
    // Get the value entered in the form.
    String textValueName = request.getParameter("text-input-name");
    String textValueEmail = request.getParameter("text-input-email");

    // Print the value so you can see it in the server logs.
    System.out.println(textValueName + " submitted contact email: " + textValueEmail);

    writeToDatastore(textValueName, textValueEmail);

    // Write the value to the response so the user can see it.
    response.getWriter().println(textValueName + " submitted contact email: " + textValueEmail);
    }
    else{
    response.getWriter().println("Input information error, please check for your spelling!");
    }

    response.sendRedirect("https://zhe-sps-summer22.appspot.com/");
  }

  public void writeToDatastore(String textValueName, String textValueEmail){
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactor = datastore.newKeyFactory().setKind("contact");
    FullEntity contactEntity = 
        Entity.newBuilder(keyFactor.newKey())
            .set("name", textValueName)
            .set("email", textValueEmail)
            .build();
    
    datastore.put(contactEntity);
  }

  public String validateInput(HttpServletRequest request){
    if(!request.getParameter("text-input-name").matches("[\\w*\\s*]*")){
        return "error";
    }
    if(!request.getParameter("text-input-email").matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")){
        return "error";
    }
    return "";
  }
}