package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms
import play.api.data.Form
import play.api.data.Forms._

case class LoginForm(email: String, password: String)
object Application extends Controller {

  val loginForm = Form(
    Forms.mapping(
      "email" -> email,
      "password" -> nonEmptyText)(LoginForm.apply)(LoginForm.unapply))

  def index = Action {
    Ok(views.html.index(loginForm.fill(LoginForm("ruchi@knoldus.com","123456")),"Your new application is ready."))
  }

  def printMessage = Action {
    Ok("This is my message")
  }

  def authenticateUser = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      errors => Ok("There is some error"),
      logInForm => {
        val email = logInForm.email
        val password = logInForm.password
        println(email + "-----------" + password)
        if (email.equals("ruchi@knoldus.com") && password.equals("123456")) {
          Ok("Login Successfull")
        } else {
          Ok("Invalid")
        }
      })

  }

}