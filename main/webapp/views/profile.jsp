
<!DOCTYPE html>
<head>

 <link href="data:image/x-icon;base64,AAABAAEAEBAQAAEABAAoAQAAFgAAACgAAAAQAAAAIAAAAAEABAAAAAAAgAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAHQX6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAREQAAAAAAEREREQAAAAEREREREAAAERABEAERAAERAAEQABEQARAAARAAARABAAABEAAAEAAAAREREAAAAAABEREQAAAAAAABEAAAAAAAABERAAAAAAABEREQAAAAAAEQARAAAAAAAREREAAAAAAAEREAAAD+fwAA/D8AAPAPAADgBwAAxmMAAI5xAACeeQAAvn0AAPgfAAD4HwAA/n8AAPw/AAD4HwAA+Z8AAPgfAAD8PwAA" rel="icon" type="image/x-icon" />
        <title>DoBattle</title>
        <link rel="stylesheet" type="text/css" href="views/style/profile.css">

  <script src="views/createTable.js" type="text/javascript" >
  </script>
  <style type="text/css">@charset "UTF-8";[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){display:none !important;}ng\:form{display:block;}
  </style>
  <meta charset="UTF-8">
  <title>Log in/Sign up screen animation</title>


</head>
<body>
  <div class="btn_back">
    <input class="button" type="submit" name="go_back" value= "Go back">
  </div>
  <header>
    <div class="header">
      Profile setting
    </div>
  </header>
    <div class="container">
      <table width = 900px height = 575px border="0px" cellspacing="0" id="table">
       <tr>
         <td>
      <div class="frame">
        <div class="frame_t" type="text">
          Change your name
        </div>
          <div ng-app="" ng-init="checked = false" class="ng-scope">
              <form class="form-signup ng-pristine ng-valid" action="" method="post" name="form" onsubmit="return checkPass()">
                <label for="password">Password</label>
                <input class="form-styling" id = "p1" type="password" name="password" maxlength = "20" placeholder="" required>
                <label for="confirmpassword">New name</label>
                <input class="form-styling" type="text" name="newfullname" placeholder="" required>
                <a class="btn-signup">
                  <input class="submit" type="submit"  name="submit" value= "Confirm">
                </a>
               </form>
          </div>
        </div>
        </td>
        <td>
                <div class="frame">
        <div class="frame_t" type="text">
          Change your password
        </div>
          <div ng-app="" ng-init="checked = false" class="ng-scope">
              <form class="form-signup ng-pristine ng-valid" action="" method="post" name="form" onsubmit="return checkPass()">
                <label for="fullname">Password</label>
                <input class="form-styling" id = "p1" type="password" name="password" placeholder="" required>
                <label for="password">New password</label>
                <input class="form-styling" id = "p1" type="password" name="newpassword" maxlength = "20" placeholder="" required>
                <label for="confirmpassword">Confirm password</label>
                <input class="form-styling" id = "p2" type="password" name="confirmpassword" maxlength = "20" placeholder="" required>
                <a class="btn-signup">
                  <input class="submit" type="submit"  name="submit" value= "Confirm">
                </a>
               </form>
          </div>
        </div>
        </td>
        </tr>
        </table>
   </div>
</body>