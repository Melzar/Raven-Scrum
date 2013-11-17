<div class="box">
<div class="sidebar-list">
	<#list navlinks as nav>
		<a href="<@spring.url '${nav.url}'/>" class="sidebar-item <#if nav.type??> active </#if>">
			<i class="${nav.icon}"></i>
			<span>${nav.name}</span>
			
		</a>
	</#list>
	<!-- <a href="#" class="list-group-item active">
		<i class="icon-gear"></i>
		<span>Scrumboard</span>
	</a>
	<a href="#" class="list-group-item">
		<i class="icon-envelope"></i>
		<span>Poczta</span>
		<span class="badge badge-important">5</span>
	</a>
	<a href="#" class="list-group-item">
		<i class="icon-bar-chart"></i>
		<span>Statystyki konta</span>
	</a>
	<a href="#" class="list-group-item">
		<i class="icon-gears"></i>
		<span>Ustawienia powiadomien</span>
	</a>
	<a href="<@spring.url '/account/edit'/>" class="list-group-item">
		<i class="icon-edit"></i>
		<span>Edycja danych konta</span>
	</a>
	<a href="#" class="list-group-item">
		<i class="icon-trash"></i>
		<span>Usunięcie konta</span>
	</a> -->
</div>
</div>