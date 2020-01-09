<!DOCTYPE html>
<meta http-equiv="Cache-Control" content="no-cache">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    <link rel="stylesheet" type="text/css" href="views/style/addUser.css">
    <link href="data:image/x-icon;base64,AAABAAEAEBAQAAEABAAoAQAAFgAAACgAAAAQAAAAIAAAAAEABAAAAAAAgAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAHQX6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAREQAAAAAAEREREQAAAAEREREREAAAERABEAERAAERAAEQABEQARAAARAAARABAAABEAAAEAAAAREREAAAAAABEREQAAAAAAABEAAAAAAAABERAAAAAAABEREQAAAAAAEQARAAAAAAAREREAAAAAAAEREAAAD+fwAA/D8AAPAPAADgBwAAxmMAAI5xAACeeQAAvn0AAPgfAAD4HwAA/n8AAPw/AAD4HwAA+Z8AAPgfAAD8PwAA" rel="icon" type="image/x-icon" />
        <title>Join the maritime fraternity</title>
          <script src="views/addUser.js">

          </script>
          <style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}
          </style>

          <meta charset="UTF-8">
        <link rel="icon" href="/src/main/java/app/servlets/favicon.ico" type="image/x-icon">


    </head>

    <body>
        <div class="container">
          <div class="frame">
            <div class="btn_back">
                <input class="button" type="submit" name="go_back" onclick="location.href='index.jsp'" value= "go back">
            </div>
            <div class="frame_t" type="text">
              Join the maritime fraternity
            </div>
              <div ng-app="" ng-init="checked = false" class="ng-scope">
                  <form class="form-signup ng-pristine ng-valid" action="" method="post" name="form" onsubmit="return checkPass()">
                    <label for="fullname">Name</label>
                    <input class="form-styling" type="text" name="name" placeholder="" required>
                    <label for="password">Password</label>
                    <input class="form-styling" id = "p1" type="password" name="pass" maxlength = "20" placeholder="" required>
                    <label for="confirmpassword">Confirm password</label>
                    <input class="form-styling" id = "p2" type="password" name="confirmpassword" maxlength = "20" placeholder="" required>
                    <div>
                    <%
                                    if (request.getAttribute("userName") != null) {
                                        out.println("<ui>" + request.getAttribute("userName") + "</ui>");
                                    }
                                %>
                    </div>
                    <a class="btn-signup">
                      <input class="submit" type="submit" onclick= check() name="submit" value= "Sign up">
                    </a>
                  </form>
              </div>
            </div>
        </div>
    </body>
</html>