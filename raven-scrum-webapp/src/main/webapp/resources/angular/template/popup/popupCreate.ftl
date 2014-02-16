<div class="mask-wrapper">
		<div class="popup popup-middle transitionable" ng-class="{'animated fadeIn' : openSprintPopup}" >
			<button type="button" class="close" ng-click="hideSprintPopup()">×</button>
			<div class="popup-content-wrapper">
					<div class="popup-header">
					    <p>Creating new sprint</p>
					</div>
					<div class="popup-content">
									<div class="col-md-6">
									 	<h4>Start date</h4>
	   									<div class="date-container">
	   									{{startdate}}
	    										<div class="well well-sm" ng-model="startdate">
	       											<datepicker min="minDate" show-weeks="showWeeks"></datepicker>
	      										</div>
	   									</div>
									</div>
									<div class="col-md-6">
									 <h4>End date</h4>
									 	<div class="date-container">
									 	{{enddate}}
	    										<div class="well well-sm" ng-model="enddate">
	       											<datepicker min="minDate" show-weeks="showWeeks"></datepicker>
	      										</div>
	      										<a class="btn btn-primary btn-sm pull-right" ng-click="createNewSprint()">Create new sprint</a>
	   								 	</div>
									</div>
					</div>
			</div>
		</div>
		<div class="mask"></div>
</div>