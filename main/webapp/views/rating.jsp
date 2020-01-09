
<head>
<link rel="stylesheet" type="text/css" href="views/style/rating.css">\

  <link href="data:image/x-icon;base64,AAABAAEAEBAQAAEABAAoAQAAFgAAACgAAAAQAAAAIAAAAAEABAAAAAAAgAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAHQX6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAREQAAAAAAEREREQAAAAEREREREAAAERABEAERAAERAAEQABEQARAAARAAARABAAABEAAAEAAAAREREAAAAAABEREQAAAAAAABEAAAAAAAABERAAAAAAABEREQAAAAAAEQARAAAAAAAREREAAAAAAAEREAAAD+fwAA/D8AAPAPAADgBwAAxmMAAI5xAACeeQAAvn0AAPgfAAD4HwAA/n8AAPw/AAD4HwAA+Z8AAPgfAAD8PwAA" rel="icon" type="image/x-icon" />
  <script>
    window.onload = function() {
    document.querySelector('#enemy').oninput = function(){
      let val=this.value.trim();
      let enemyItems = document.querySelectorAll('.rectangle tr');
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
      Rating
    </div>
  </header>
   <div class="frame">
    <form method="post">
      <div class = "find">
        <input class="search" type= "text", id = "enemy", size = "30" placeholder = "Enter name of desired rival">
      </div><br>
        <table class="head"border="1px" cellspacing="0" id="table"><tr><td class = "first">Name</td>
          <td>PwE</td><td>PwP</td></tr>
        <table class="rectangle"border="1px" cellspacing="0" id="table">
          <tr><td class = "first">a</td><td>1</td><td>1</td></tr>
          <tr><td class = "first">b</td><td>1</td><td>3</td></tr>
          <tr><td class = "first">c</td><td>1</td><td>7</td></tr>
          <tr><td class = "first">d</td><td>5</td><td>7</td></tr>
          <tr><td class = "first">e</td><td>9</td><td>7</td></tr>
          <tr><td class = "first">Kodan</td><td>0</td><td>8</td></tr>
          <tr><td class = "first">NotKodan</td><td>5</td><td>2</td></tr>
    </form>
  </div>
</body>