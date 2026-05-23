<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
  <title>App user page</title>
  <script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>
<h1>App user info:</h1>
<form:form method="post" action="/addAppUser" modelAttribute="appUserDto">
  <sec:csrfInput />

  <table>
    <tr>
      <td><form:label path="firstName">First Name</form:label></td>
      <td><form:input path="firstName" /></td>
    </tr>
    <tr>
      <td><form:label path="lastName">Last Name</form:label></td>
      <td><form:input path="lastName" /></td>
    </tr>
    <tr>
      <td><form:label path="email">Email</form:label></td>
      <td><form:input path="email" /></td>
    </tr>
    <tr>
      <td><form:label path="telephone">Telephone</form:label></td>
      <td><form:input path="telephone" /></td>
    </tr>
    <tr>
      <td><form:label path="password">Password</form:label></td>
      <td><form:password path="password" /></td>
    </tr>

    <tr>
      <td><form:label path="confirmPassword">Confirm Password</form:label></td>
      <td><form:password path="confirmPassword" /></td>
    </tr>
    <tr>
      <td colspan="3">
        <div class="g-recaptcha" data-sitekey="6LcNEfksAAAAALRBy0oP3vWUAXnKWMzqVHQgJE4q"></div>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="Add AppUser"/>
      </td>
    </tr>
  </table>

</form:form>
</body>
</html>

