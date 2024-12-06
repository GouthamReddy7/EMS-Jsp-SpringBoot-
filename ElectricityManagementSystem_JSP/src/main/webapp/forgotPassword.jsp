<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

<head>

  <meta charset="UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Forgot Password</title>

<link rel="stylesheet" href="css/f_password.css">

</head>

<body>

  <div class="forgot-password-container">

    <h2>Forgot Password</h2>
    <p>Please enter your email, password hint, and a new password to reset.</p>

    <form id="forgot-password-form" action="UserForgetPasswordServlet" method="get">

      <div class="form-group">

        <label for="email"><b>Email:</b></label>

        <input type="email" id="email" name="email" placeholder="Enter your email" required />

      </div>

      <div class="form-group">

        <label for="passwordHint"><b>Favourite Place:</b></label>

        <input type="text" id="passwordHint" name="passwordHint" placeholder="Enter your favourite place" required />

      </div>

      <div class="form-group">

        <label for="password"><b>New Password:</b></label>

        <input type="password" id="password" name="password" placeholder="Enter your new password" required />

      </div>

      <button type="submit" id="changePasswordbtn">Change Password</button>

      <button onclick="window.location.href='loginHome.jsp'" type="button" class="secondary-btn">Go Back</button>

    </form>

  </div>
<jsp:include page="footer.jsp" />
</body>

</html>