<!DOCTYPE html>
<meta http-equiv="Cache-Control" content="no-cache">
       <meta http-equiv="Pragma" content="no-cache" />
       <html lang="en">
       <%@ page contentType="text/html;charset=UTF-8" language="java" %>
       <head>
       <title>Menu</title>
       <link href="data:image/x-icon;base64,AAABAAEAEBAQAAEABAAoAQAAFgAAACgAAAAQAAAAIAAAAAEABAAAAAAAgAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAHQX6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAREQAAAAAAEREREQAAAAEREREREAAAERABEAERAAERAAEQABEQARAAARAAARABAAABEAAAEAAAAREREAAAAAABEREQAAAAAAABEAAAAAAAABERAAAAAAABEREQAAAAAAEQARAAAAAAAREREAAAAAAAEREAAAD+fwAA/D8AAPAPAADgBwAAxmMAAI5xAACeeQAAvn0AAPgfAAD4HwAA/n8AAPw/AAD4HwAA+Z8AAPgfAAD8PwAA" rel="icon" type="image/x-icon" />
            <meta charset="UTF-8">
           <script>
           function alert() {
               alert('still in  development')
           }
         </script>
         <link rel="stylesheet" type="text/css" href="views/style/mainMenu.css">
       </head>

       <body>

         <div class="btn_back">
           <input class="button" type="button" name="go_back" onclick="location.href='/LogoutServlet'" value= "Log out">
         </div>
         <header>
           <div class="header">
             Sea Fight
           </div>
            <div class="header">
            <%

                if (request.getAttribute("lose") != null) {
                    out.println(request.getAttribute("lose"));
                }

             %>
           </div>

         </header>
         <nav>
           <li><a href="#">
             <button class="submit" type="submit" name="profile" onclick="location.href='/ProfileServlet'"> profile </button>
             </a>
           </li>
           <li><a href="#">
             <button class="submit" type="submit" name="submit" onclick="location.href='/BattleServlet'"> to battle </button>
             </a>
           </li>
           <li><a href="#">
             <button class="submit" type="submit" name="submit" onclick= "location.href='/DoBattleServlet'"> battle with bot </button>
             </a>
           </li>
           <li><a href="#">
             <button class="submit" type="submit" name="submit" onclick="location.href='RatingServlet'"> rating </button>
             </a>
           </li>
       </body>
 </html>
