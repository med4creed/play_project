package controllers;


import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.*;
import models.*;

import java.util.*;

import play.mvc.BodyParser;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;




import play.data.*;

public class Application extends Controller {

	static Form<Task> taskForm = form(Task.class);

	public static Result index() {
		// return ok(index.render("Your new application is ready."));
		// return ok("Hello world");
		return redirect(routes.Application.tasks());
	}


	public static Result tasks() {
		return ok(views.html.index.render(Task.findAll(), taskForm));
	}

	public static Result newTask() {
		Form<Task> filledForm = taskForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(Task.findAll(), filledForm));
		} else {
			Task.create(filledForm.get());
			return redirect(routes.Application.tasks());
		}
	}

	public static Result deleteTask(Long id) {
		Task.delete(id);
		return redirect(routes.Application.tasks());
	}
	
	public static Result jsonTask() {
	  ObjectNode result = Json.newObject();
	  List<Task> tasks = Task.findAll();
	  int i =0;
	  ArrayNode arrayJson = result.putArray("tasks");
	  for (Task task : tasks) {
		  i++;
		  arrayJson.add(Json.toJson(task));
	}
	  
	    return ok(result);	    
	  
	}

}
