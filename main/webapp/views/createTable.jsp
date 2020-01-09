 <head>
    <link href="data:image/x-icon;base64,AAABAAEAEBAQAAEABAAoAQAAFgAAACgAAAAQAAAAIAAAAAEABAAAAAAAgAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAHQX6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAREQAAAAAAEREREQAAAAEREREREAAAERABEAERAAERAAEQABEQARAAARAAARABAAABEAAAEAAAAREREAAAAAABEREQAAAAAAABEAAAAAAAABERAAAAAAABEREQAAAAAAEQARAAAAAAAREREAAAAAAAEREAAAD+fwAA/D8AAPAPAADgBwAAxmMAAI5xAACeeQAAvn0AAPgfAAD4HwAA/n8AAPw/AAD4HwAA+Z8AAPgfAAD8PwAA" rel="icon" type="image/x-icon" />
        <title>DoBattle</title>
        <link rel="stylesheet" type="text/css" href="views/style/createTable.css">
 </head>


<body>
<!-- Yandex.Metrika counter --> <script src="views/createTable.js" type="text/javascript" > (function (d, w, c) { (w[c] = w[c] || []).push(function() { try { w.yaCounter45744036 = new Ya.Metrika({ id:45744036, clickmap:true, trackLinks:true, accurateTrackBounce:true, webvisor:true }); } catch(e) { } }); var n = d.getElementsByTagName("script")[0], s = d.createElement("script"), f = function () { n.parentNode.insertBefore(s, n); }; s.type = "text/javascript"; s.async = true; s.src = "https://mc.yandex.ru/metrika/watch.js"; if (w.opera == "[object Opera]") { d.addEventListener("DOMContentLoaded", f, false); } else { f(); } })(document, window, "yandex_metrika_callbacks"); </script> <noscript><div><img src="https://mc.yandex.ru/watch/45744036" style="position:absolute; left:-9999px;" alt="" /></div></noscript><!-- /Yandex.Metrika counter -->
<!--LiveInternet counter--><script type="text/javascript">
new Image().src = "//counter.yadro.ru/hit?r"+
escape(document.referrer)+((typeof(screen)=="undefined")?"":
";s"+screen.width+"*"+screen.height+"*"+(screen.colorDepth?
screen.colorDepth:screen.pixelDepth))+";u"+escape(document.URL)+
";h"+escape(document.title.substring(0,80))+
";"+Math.random();</script><!--/LiveInternet-->
  <div class="battlefield">
    <!-- верхний блок для вывода текстовых сообщений -->
    <div id="text_top" class="text-top">Ships arrangement</div>
    <div class="outer clearfix">
      <!-- поле игрока -->
      <div class="field field-user">
        <div id="field_user" class="ships"></div>
      </div>

      <!-- поле компьютера -->
      <div class="field field-comp" data-hidden="true">
        <div id="field_comp" class="ships"></div>
      </div>

      <!-- инструкция -->
      <div id="instruction" class="instruction" data-hidden="false">
        <div id="type_placement" class="type-placement-box">
           <span class="link" data-target="random">Random</span><br>
           <span class="link" data-target="manually">By myself</span>
        </div>
        <div id="ships_collection" class="ships-collection" data-hidden="true">
          <!-- набор кораблей для перетаскивания -->
          <ul id='initial_ships' class="initial-ships">
            <li>
              <div id="fourdeck1" class="ship fourdeck"></div>
              <div id="tripledeck1" class="ship tripledeck tripledeck1"></div>
              <div id="tripledeck2" class="ship tripledeck tripledeck2"></div>
            </li>
            <li>
              <div id="doubledeck1" class="ship doubledeck"></div>
              <div id="doubledeck2" class="ship doubledeck doubledeck2"></div>
              <div id="doubledeck3" class="ship doubledeck doubledeck3"></div>
            </li>
            <li>
              <div id="singledeck1" class="ship singledeck"></div>
              <div id="singledeck2" class="ship singledeck singledeck2"></div>
              <div id="singledeck3" class="ship singledeck singledeck3"></div>
              <div id="singledeck4" class="ship singledeck singledeck4"></div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <span id="play" class="btn-play" data-hidden="true"> Play </span>
  </div>
</body>