package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.toto;
import models.*;
import java.util.*;
import play.data.*;

public class Application extends Controller {

	static Form<Task> taskForm = form(Task.class);

	public static Result index() {
		// return ok(index.render("Your new application is ready."));
		// return ok("Hello world");
		return redirect(routes.Application.tasks());
	}

	public static Result toto() {
		return ok(toto.render("boooooooooooooooooomm!!!"));

	}

	public static Result tasks() {
		return ok(views.html.index.render(Task.all(), taskForm));
	}

	public static Result newTask() {
		Form<Task> filledForm = taskForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(Task.all(), filledForm));
		} else {
			Task.create(filledForm.get());
			return redirect(routes.Application.tasks());
		}
	}

	public static Result deleteTask(Long id) {
		Task.delete(id);
		return redirect(routes.Application.tasks());
	}

}
