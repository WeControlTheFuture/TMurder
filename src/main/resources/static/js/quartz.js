$(document).ready(function() {
	$('.job-create').click(function(e) {
		e.preventDefault();
		$('#jobCreateModal').modal('show');
	});

	$('.trigger-create').click(function(e) {
		e.preventDefault();
		$('#triggerCreateModal').modal('show');
	});
	

	$('.btn-job-submmit').click(function(e) {
	});
	

	// don't work ,can not find triggerDefine
	$('.btn-show-trigger').click(function(e) {
		e.preventDefault();
		triggerDefine.data.show = true;
	});
	
	
})