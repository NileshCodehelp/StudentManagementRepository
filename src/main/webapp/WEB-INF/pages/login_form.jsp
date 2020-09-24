<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://www.springframework.org/tags/form"   prefix="form"%>
    
    <h1 style="color:red;text-align:Center">Login Page </h1>
    
    <form:form  modelAttribute="userCmd">
        Login username:: <form:input path="user"/> <br>
        Login Password :: <form:input path="pwd"/> <br>
        <input type="submit"  value="Login">
    </form:form>
