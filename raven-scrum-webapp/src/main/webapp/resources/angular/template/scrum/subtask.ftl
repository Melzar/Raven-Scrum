<div class="row">
	<div class="col-lg-2">
		<div class="subtask-type {{subtask.type}}">
			
		</div>
	</div>
	<div class="col-lg-8">
		<div class="row">
			<a class="subtask-title {{subtask.state}} subtask-text text-limit-1" ng-click="showPanel(subtask, {{$index}}, task)">{{subtask.title}} - {{subtask.id}}</a>
		</div>
		<div class="row">
		    <span class="subtask-description subtask-text text-limit-1">{{subtask.description}}</span>
		</div>
	</div>
	<div class="col-lg-2">
	    <div class="subtask-avatar pull-right">
	    </div>
	</div>
</div>