<!DOCTYPE html>
<meta http-equiv="Cache-Control" content="no-cache">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
<link href="data:image/x-icon;base64,AAABAAEAEBAQAAEABAAoAQAAFgAAACgAAAAQAAAAIAAAAAEABAAAAAAAgAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAHQX6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAREQAAAAAAEREREQAAAAEREREREAAAERABEAERAAERAAEQABEQARAAARAAARABAAABEAAAEAAAAREREAAAAAABEREQAAAAAAABEAAAAAAAABERAAAAAAABEREQAAAAAAEQARAAAAAAAREREAAAAAAAEREAAAD+fwAA/D8AAPAPAADgBwAAxmMAAI5xAACeeQAAvn0AAPgfAAD4HwAA/n8AAPw/AAD4HwAA+Z8AAPgfAAD8PwAA" rel="icon" type="image/x-icon" />
        <script>
            function myFunction() {
                var x = document.getElementById("p1");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
            }
        </script>
        <title>Log In</title>
          <style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}
          </style>
          <meta charset="UTF-8">
          <title>Log in/Sign up screen animation</title>


        <link rel="stylesheet" type="text/css" href="views/style/logIn.css">
        <link rel="SHORTCUT ICON" href="/src/main/java/app/servlets/favicon.ico" type="image/x-icon">
    </head>

    <div>
    <div>

    <body>
        <div class="container">
              <div class="frame">
                <div class="btn_back">
                    <input class="button" type="button" name="go_back" onclick="location.href='index.jsp'" value= "go back">
                </div>
                <div class="frame_t" type="text">
                  LOG IN
                </div>
                  <div ng-app="" ng-init="checked = false" class="ng-scope">
                      <form class="form-signin ng-pristine ng-valid" action="" method="post" name="form">
                        <label for="username">Username</label><br>
                        <input class="form-styling" type="text" name="name" maxlength = "20" placeholder="" required><br>
                        <label for="password">Password</label><br>
                        <input class="form-styling" id="p1"type="password" name="password" placeholder="" required>
                        <input type="checkbox" onclick="myFunction()">Show Password
                        <div>
                                                <%
                                                if (request.getAttribute("userName") != null) {
                                                    out.println("<ui>" + request.getAttribute("userName") + "</ui>");
                                                }
                                                %>
                                                </div>
                        <div class="btn-animate">
                          <a class="btn-signin">
                            <input class="submit" type="submit"  name="submit" value= "go to sea">
                          </a>
                        </div>
                      </form>
              </div>
        </div>
    </body>
</html>