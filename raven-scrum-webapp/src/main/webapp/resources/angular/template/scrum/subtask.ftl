<div class="subtask-avatar pull-right">
</div>
<div class="subtask-type {{subtask.type}}">
</div>
<div class="subtask-details">			
<a class="subtask-title {{subtask.state}} text-overflow text-limit-1" ng-click="showPanel(subtask, {{$index}}, task)">{{subtask.title}} - {{subtask.id}}</a>
  <span class="subtask-description text-overflow">{{subtask.description}}</span>
</div>