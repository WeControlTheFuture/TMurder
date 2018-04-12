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
			name : 'bixy',
			group : 'prod',
			jobClass : 'org.wctf.quartz.job.NoOpJob',
			description : 'just for test',
			recover : true,
			jobDataMap : [{
				key : '1',
				value : 'a'
			},{
				key : '2',
				value : 'b'
			}]
		},
		methods : {
			addJobParam : function() {
				this.jobDataMap.push({
					key : '',
					value : ''
				});
			},
			delParam : function(index) {
				this.jobDataMap.splice(index, 1);
			},
			saveJobDefine : function() {
				console.log(this);
				console.log(this.$data);
				$.ajax({
					type : "POST",
					url : "/quartz/createjob",
					contentType: "application/json;charset=UTF-8",
					data : JSON.stringify(this.$data),
					success : function(data) {
						alert(data);
					},
					error : function() {
						alert("error");
					}

				});
			}
		}
	});
})