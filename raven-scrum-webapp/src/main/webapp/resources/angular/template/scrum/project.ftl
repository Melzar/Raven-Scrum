<div class="project-wrapper">
	<div class="project-content">
		<div class="project">
			<div class="project-item-avatar">
				<a href="#"><img class="media-object" data-src="holder.js/140x140" alt="100x100" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAACDUlEQVR4Xu2Yz6/BQBDHpxoEcfTjVBVx4yjEv+/EQdwa14pTE04OBO+92WSavqoXOuFp+u1JY3d29rvfmQ9r7Xa7L8rxY0EAOAAlgB6Q4x5IaIKgACgACoACoECOFQAGgUFgEBgEBnMMAfwZAgaBQWAQGAQGgcEcK6DG4Pl8ptlsRpfLxcjYarVoOBz+knSz2dB6vU78Lkn7V8S8d8YqAa7XK83ncyoUCjQej2m5XNIPVmkwGFC73TZrypjD4fCQAK+I+ZfBVQLwZlerFXU6Her1eonreJ5HQRAQn2qj0TDukHm1Ws0Ix2O2260RrlQqpYqZtopVAoi1y+UyHY9Hk0O32w3FkI06jkO+74cC8Dh2y36/p8lkQovFgqrVqhFDEzONCCoB5OSk7qMl0Gw2w/Lo9/vmVMUBnGi0zi3Loul0SpVKJXRDmphvF0BOS049+n46nW5sHRVAXMAuiTZObcxnRVA5IN4DJHnXdU3dc+OLP/V63Vhd5haLRVM+0jg1MZ/dPI9XCZDUsbmuxc6SkGxKHCDzGJ2j0cj0A/7Mwti2fUOWR2Km2bxagHgt83sUgfcEkN4RLx0phfjvgEdi/psAaRf+lHmqEviUTWjygAC4EcKNEG6EcCOk6aJZnwsKgAKgACgACmS9k2vyBwVAAVAAFAAFNF0063NBAVAAFAAFQIGsd3JN/qBA3inwDTUHcp+19ttaAAAAAElFTkSuQmCC" style="width: 85px; height: 85px;">
				</a>
			</div>			
			<div class="project-item-menu">
				<a href="{{templatedata.sourcelink}}/project/scrumboard?project={{project.idProject}}" class="btn btn-default btn-xs"><i class="fa fa-gear icon-padding"></i>Scrumboard</a>
				<a href="{{templatedata.sourcelink}}/project/dashboard?project={{project.idProject}}" class="btn btn-default btn-xs"><i class="fa fa-dashboard icon-padding"></i>Dashboard</a>
				<a href="{{templatedata.sourcelink}}/project/backlog?project={{project.idProject}}" class="btn btn-default btn-xs"><i class="fa fa-list icon-padding"></i>Backlog</a>
			</div>
			<div class="project-item-details">
				<div class="project-item-title text-overflow text-limit-1">{{project.title}}</div>
				<div class="project-item-description text-overflow text-limit-2">
					{{project.description}}
				</div>
			</div>
		</div>
	</div>
</div>