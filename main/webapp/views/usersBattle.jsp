<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>

        <script language="JavaScript" >

            function a(i,j) {

                var btn = this;
                var xml = new XMLHttpRequest();
                xml.open('POST', '/BattleServlet', true);
                xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

                xml.onreadystatechange = function() {

                    if (xml.readyState != 4) {
                        return
                    }

                    if (xml.status == 200) {
                        var response = xml.responseText;
                        window.location.reload();
                    } else {
                        alert("Error");
                    }

                }

                var message = "fire1=" + i + " " + j;
                xml.send(message);

            }

        </script>

    <head>
    <link rel="stylesheet" type="text/css" href="views/style/usersBattle.css">
    <link href="data:image/x-icon;base64,AAABAAEAEBAQAAEABAAoAQAAFgAAACgAAAAQAAAAIAAAAAEABAAAAAAAgAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAHQX6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAREQAAAAAAEREREQAAAAEREREREAAAERABEAERAAERAAEQABEQARAAARAAARABAAABEAAAEAAAAREREAAAAAABEREQAAAAAAABEAAAAAAAABERAAAAAAABEREQAAAAAAEQARAAAAAAAREREAAAAAAAEREAAAD+fwAA/D8AAPAPAADgBwAAxmMAAI5xAACeeQAAvn0AAPgfAAD4HwAA/n8AAPw/AAD4HwAA+Z8AAPgfAAD8PwAA" rel="icon" type="image/x-icon" />
        <title>DoBattle</title>

        <script type="text/javascript">

            /*function sleep(ms) {
                ms += new Date().getTime();
                while (new Date() < ms){}
            }

            function loop() {
                while (true) {
                    sleep(1500);
                    alert('ok');
                }
            }*/

            function onload() {
               var btn = this;
               var xml = new XMLHttpRequest();
               xml.open('POST', '/BattleServlet', true);
               xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

               xml.onreadystatechange = function() {

                   if (xml.readyState != 4) {
                       return
                   }

                   if (xml.status == 200) {
                       var response = xml.responseText;
                       window.location.reload();
                   } else {
                       alert("Error");
                   }

               }

               var message = "cantFire=cant";
               xml.send(message);
               //alert("cantFire");
            };

        </script>

    </head>

    <body>

    <header>
    <div class="header">
    Sea fight
    </div>
    </header>

        <div>
            <div id="content">


                <%
                String canFire = null;

                if (request.getAttribute("canFire") != null) {
                    canFire = (String) request.getAttribute("canFire");

                    if (canFire.equals("yes")) {
                        out.println("<a>Your turn</a>");
                    }
                    else {
                        out.println("<a>Enemy turn</a>");
                        out.println("<script> onload(); </script>");
                    }
                }

                String endGame = (String) request.getAttribute("endGame");

                if (endGame == null) {
                    List<List<Integer>> myTable = (List<List<Integer>>) request.getAttribute("myTable");
                    List<List<Integer>> enemyTable = (List<List<Integer>>) request.getAttribute("enemyTable");

                    if (myTable != null && !myTable.isEmpty()) {

                        out.println("<table class=\"markUp\" width = 900px height = 364px border=\"0\" cellspacing=\"0\" id=\"table\">");

                        out.println("<tr>");
                        out.println("<td>");

                        out.println("<a>Your ships, " + (String) request.getAttribute("yourName") + "</a>");
                        out.println("<div class=\"frame\">");
                        out.println("<table width = 338px height = 334px border=\"0\" cellspacing=\"0\" id=\"table\">");

                        for (int i = 0; i < myTable.size(); i++) {
                            out.println("<tr>");
                            for (int j = 0; j < myTable.get(i).size(); j++) {
                            String mod = "class = ";
                            if (myTable.get(i).get(j) == 0) {
                                mod += "\"empty\"";
                            }
                            if (myTable.get(i).get(j) == 1) {
                                mod += "\"ship\"";
                            }
                            if (myTable.get(i).get(j) == 2) {
                                mod += "\"miss\"";
                            }
                            if (myTable.get(i).get(j) == 3) {
                                mod += "\"hit\"";
                            }
                                out.println("<td><button " + mod + " type=\"button\" name=\"empty\" value =" + i + " " + j + ">" + "</button></td>");
                            }
                            out.println("</tr>");
                        }
                        out.println("</table>");
                        out.println("</div>");

                        out.println("</td>");

                        out.println("<td>");

                        out.println("<a>" + (String) request.getAttribute("enemyName") + "'s ships</a>");

                        out.println("<div class=\"frame\">");
                        out.println("<table width = 338px height = 334px border=\"0\" cellspacing=\"0\" id=\"table\">");

                        for (int i = 0; i < enemyTable.size(); i++) {
                            out.println("<tr>");
                            for (int j = 0; j < enemyTable.get(i).size(); j++) {
                                String mod = "class = ";
                                if (enemyTable.get(i).get(j) == 0) {
                                    mod += "\"empty\"";
                                }
                                if (enemyTable.get(i).get(j) == 1) {
                                    mod += "\"ship\"";
                                }
                                if (enemyTable.get(i).get(j) == 2) {
                                    mod += "\"miss\"";
                                }
                                if (enemyTable.get(i).get(j) == 3) {
                                    mod += "\"hit\"";
                                }

                                if (request.getAttribute("canFire") != null) {
                                    if (canFire.equals("yes")) {
                                        out.println("<td><button " + mod + " type=\"button\" name=\"fire\" value =\"" + i + " " + j + "\"  OnClick=\"a(" + i + ", " + j + ");\" >"+"</button></td>");
                                    }
                                    else {
                                        out.println("<td><button " + mod + " type=\"button\" name=\"empty\" value =" + i + " " + j + ">" + "</button></td>");
                                    }
                                }
                            }
                            out.println("</tr>");
                        }
                        out.println("</table>");
                        out.println("</div>");
                        out.println("</td>");
                        out.println("</tr>");

                        out.println("<form method=\"post\">");
                        out.println("<button class=\"button\" type=\"submit\" name=\"over\" value =\"yes\">" + "over" + "</button>");
                        out.println("</form>");

                    } else
                    out.println("<button type=\"submit\" name=\"isGame\" value =\"yes\"> </button>");
                    } else {
                        if (endGame == "5") {
                            out.println("You won in battle with bot!");
                        }
                        if (endGame == "-5") {
                            out.println("You lose in battle with bot...");
                        }
                    }

                %>
            </div>
        </div>

        <div>
            <form method="post">
            <button class="button" type="submit" name="menu" value ="yes"> Back to main </button>
            </form>
        </div>


    </body>
</html>