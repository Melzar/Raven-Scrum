<div class="navbar navbar-inverse">
              <div class="navbar-inner">
                  <div class="navbar-collapse collapse">
						<ul class="nav navbar-nav pull-right">
						  
						  <@security.authorize ifAllGranted="ROLE_ANONYMOUS">
						  		<li><a href="<@spring.url '/login'/>"><i class="icon-power-off icon-padding"></i>Zaloguj</a></li>
						  </@security.authorize>
						  <@security.authorize ifNotGranted="ROLE_ANONYMOUS">
						  		<li><a href="#" data-toggle="tooltip" title="Powiadomienia" data-placement="top"><i class="icon-envelope-alt icon-padding"></i><span>number</span></a></li> 	
						  		<li><a href="#"><i class="icon-gears icon-padding"></i>Ustawienia</a></li>
						  		<li><a><@security.authentication property="name" ></@security.authentication></b> [maly avatar]</a></li>
								<li><a href="<@spring.url '/j_spring_security_logout'/>"><i class="icon-power-off icon-padding"></i>Wyloguj</a></li>
						  </@security.authorize>
               			  </ul>
                  </div>
              </div>
 </div>
