	<div ng-repeat="task in scrumtasks">
			<div class="row loadin">
				<div class="col-lg-12">
					<div class="scrum-columns columns-header">
						<a class="subtask-title">{{task.title}}</a>
						<span class="pull-right">		
									T: {{task.progress.TODO.length}}
									D: {{task.progress.DOING.length}}
									U: {{task.progress.UAT.length}}
									DN: {{task.progress.DONE.length}}
						</span>
					</div>
				</div>
			</div>
			<div class="row loadin table" id="{{task.id}}">
					<div class="col-lg-3 cell scrum-columns columns-inside-padding" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true, onDrop: 'changestate'}" ng-model="task.progress.TODO" subtask="{{task.id}}" ng-dropped state="TODO">
						<div class="subtask" ng-model="task.progress.TODO" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: 'getsubtask'}" ng-repeat="subtask in task.progress.TODO" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}' }" data-idsub="{{subtask.id}}" data-drag="true" ng-init="subtask.state = 'TODO'" ng-scrum-task></div>
					</div>
					<div class="col-lg-3 cell scrum-columns columns-inside-padding second" data-drop="true" ng-model="task.progress.DOING" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true, onDrop: 'changestate'}" subtask="{{task.id}}" ng-dropped state="DOING">
						<div class="subtask" ng-model="task.progress.DOING" ng-repeat="subtask in task.progress.DOING" data-drag="true"  data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}'}" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: 'getsubtask'}" data-idsub="{{subtask.id}}" ng-init="subtask.state = 'DOING'" ng-scrum-task></div>
					</div>
					<div class="col-lg-3 cell scrum-columns columns-inside-padding" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true, onDrop: 'changestate'}" ng-model="task.progress.UAT" subtask="{{task.id}}" ng-dropped state="UAT">
						<div class="subtask" ng-model="task.progress.UAT" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: 'getsubtask'}" ng-repeat="subtask in task.progress.UAT" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}'}" data-idsub="{{subtask.id}}" data-drag="true" ng-init="subtask.state = 'UAT'" ng-scrum-task></div>
					</div>
					<div class="col-lg-3 cell scrum-columns columns-inside-padding second" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true, onDrop: 'changestate'}" ng-model="task.progress.DONE" subtask="{{task.id}}" ng-dropped state="DONE">
						<div class="subtask" ng-model="task.progress.DONE" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: 'getsubtask'}" ng-repeat="subtask in task.progress.DONE" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}'}" data-idsub="{{subtask.id}}" data-drag="true" ng-init="subtask.state = 'DONE'" ng-scrum-task></div>					
					</div>		
			</div>
	</div>