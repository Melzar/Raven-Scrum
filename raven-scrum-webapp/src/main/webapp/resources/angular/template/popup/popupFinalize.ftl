<div class="mask-wrapper">
		<div class="popup popup-middle transitionable" ng-class="{'animated fadeIn' : openSprintPopup}" >
			<button type="button" class="close" ng-click="hideSprintPopup()">×</button>
			<div class="popup-content-wrapper">
					<div class="popup-header">
					    <p>Close current sprint</p>
					</div>
					<div class="popup-content">
						<p>Do you want close current sprint?</p>
						<a class="btn btn-primary btn-sm pull-right" ng-click="finalizeSprint()">Close sprint</a>
						<a class="btn btn-primary btn-sm pull-left" ng-click="hideSprintPopup()">Cancel</a>
					</div>
			</div>
		</div>
		<div class="mask"></div>
</div>