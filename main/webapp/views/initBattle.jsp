<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" type="text/css" href="views/style/initBattle.css">
  <link href="data:image/x-icon;base64,AAABAAEAEBAQAAEABAAoAQAAFgAAACgAAAAQAAAAIAAAAAEABAAAAAAAgAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAHQX6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAREQAAAAAAEREREQAAAAEREREREAAAERABEAERAAERAAEQABEQARAAARAAARABAAABEAAAEAAAAREREAAAAAABEREQAAAAAAABEAAAAAAAABERAAAAAAABEREQAAAAAAEQARAAAAAAAREREAAAAAAAEREAAAD+fwAA/D8AAPAPAADgBwAAxmMAAI5xAACeeQAAvn0AAPgfAAD4HwAA/n8AAPw/AAD4HwAA+Z8AAPgfAAD8PwAA" rel="icon" type="image/x-icon" />
  <script>
    window.onload = function() {
    document.querySelector('#enemy').oninput = function(){
      let val=this.value.trim();
      let enemyItems = document.querySelectorAll('.rectangle li');
      if(val!=''){
        enemyItems.forEach(function(elem){
          if(elem.innerText.search(val)==-1){
             elem.classList.add('hide');
          }
          else{
            elem.classList.remove('hide');
          }
        });
      }
      else{
        enemyItems.forEach(function(elem){
             elem.classList.remove('hide');
        });
      }
    }}
  </script>
  <title>Choose enemy</title>
</head>
  <body>

  <div class="btn_back">
    <input class="button" type="button" name="go_back" onclick="location.href='/MainMenuServlet'" value= "Back to main">
  </div>
  <header>
    <div class="header">
      Sea fight
    </div>
  </header>
    <div class="frame">
        <div class = "find">
          <input type= "text", id = "enemy", size = "30" placeholder = "Enter name of user">
        </div>

    <form method="post">
      <b>
        <button type="submit" name="isGame" value = "1" >Accept an invitation</button>
      </b>
            <%

              List<String> names = (List<String>) request.getAttribute("userNames");

              if (names != null && !names.isEmpty()) {
                  out.println("<ol class=\"rectangle\">");
                  for (String s : names) {
                      out.println("<li><a><button type=\"submit\" name=\"replyingName\" value =" + s + ">" + s + "</button></a></li>");
                  }
                  out.println("</ol>");
              } else out.println("<p>There are no users yet!</p>");

            %>
    </form>
  </div>
</body>