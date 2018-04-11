$(document).ready(function() {
	var jobDefine = new Vue({
		el : '#job-define',
		data : {
			jobs : [ {
				name : 'datatransform',
				group : 'prod',
				description : 'balabala',
				status : 'stopped'
			}, {
				name : 'sync',
				group : 'prod',
				description : 'my god',
				status : 'running'
			} ]
		}
	});

	var triggerDefine = new Vue({
		el : '#trigger-define',
		data : {
			show : false,
			tirggers : [ {
				name : 'datatransform',
				group : 'prod',
				description : 'balabala',
				status : 'stopped'
			}, {
				name : 'sync',
				group : 'prod',
				description : 'my god',
				status : 'running'
			} ]
		}
	});

	var createJob = new Vue({
		el : '#jobCreateModal',
		data : {
			name : '',
			group : '',
			jobClass : '',
			description : '',
			recover : true,
			jobDataMap : {}
		}
	});
})