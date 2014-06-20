$(function () {
	$("#personAge").click(personAge);
	$("#personSex").click(personSex);
	$("#personAddress").click(personAddress);
	$("#VIPState").click(VIPState);
	$("#recordByDay").click(recordByDay);
	$("#placeUsage").click(placeUsage);
	$("#coachUsage").click(coachUsage);
});
function personAge(){
	var tentoeighteen = 0;
	var eighteentothirty = 0;
	var thirtytoforty = 0;
	var fortytofifty = 0;
	var fiftytosixty = 0;
	$.ajaxSetup({
		async:false
	});
	$.post("personAge",function(json){
		json = json.substr(1,json.length-2);
		var array = json.split(",");
		$.each(array , function(key,val){
			if(parseInt(val) >= 10 && parseInt(val)< 18){
				tentoeighteen++;
			}else if(parseInt(val) >= 18 && parseInt(val)< 30){
				eighteentothirty++;
			}else if(parseInt(val) >= 30 && parseInt(val)< 40){
				thirtytoforty++;
			}else if(parseInt(val) >= 40 && parseInt(val)< 50){
				fortytofifty++;
			}else if(parseInt(val) >= 50 && parseInt(val)< 60){
				fiftytosixty++;
			}
		});
		
	});
	var data = [
                ['10-18',  tentoeighteen],
                ['18-30',       eighteentothirty],
                ['30-40', thirtytoforty],
                ['40-50',    fortytofifty],
                ['50-60',     fiftytosixty]
            ];
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '会员各年龄段百分比'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '所占比例',
            data: data
        }]
    });
}

function personSex(){
	var male = 0;
	var female = 0;
	$.ajaxSetup({
		async:false
	});
	$.post("personSex",function(json){
		json = json.substr(1,json.length-2);
		var array = json.split(",");
		$.each(array , function(key,val){
			val = val.trim();
			if(val == "男" || val == "male"){
				male++;
			}else if(val == "女" || val == "female"){
				female++;
			}
		});
		
	});
	var data = [male,female];
	 $('#container').highcharts({
         chart: {
             type: 'column'
         },
         title: {
             text: '会员性别统计'
         },
         xAxis: {
             categories: [
                 '男',
                 '女'
             ],
             labels: {
                 align: 'right',
                 style: {
                     fontSize: '13px',
                     fontFamily: 'Verdana, sans-serif'
                 }
             }
         },
         yAxis: {
             min: 0,
             title: {
                 text: '人数'
             }
         },
         legend: {
             enabled: false
         },
         tooltip: {
             pointFormat: '人数: <b>{point.y:.1f}</b>',
         },
         series: [{
             data: data
         }]
     });
}

function personAddress(){
	var col = [];
	var data = [];
	$.ajaxSetup({
		async:false
	});
	$.post("personAddress",function(json){
		json = json.substr(1,json.length-2);
		var array = json.split(",");
		$.each(array , function(key,val){
			val = val.trim();
			if($.inArray(val,col) >= 0){
				data[$.inArray(val,col)] += 1;
			}else{
				col.push(val);
				data.push(1);
			}
		});
		
	});
	 $('#container').highcharts({
         chart: {
             type: 'column'
         },
         title: {
             text: '地区统计'
         },
         xAxis: {
             categories: col,
             labels: {
                 align: 'right',
                 style: {
                     fontSize: '13px',
                     fontFamily: 'Verdana, sans-serif'
                 }
             }
         },
         yAxis: {
             min: 0,
             title: {
                 text: '人数'
             }
         },
         legend: {
             enabled: false
         },
         tooltip: {
             pointFormat: '人数: <b>{point.y:.1f}</b>',
         },
         series: [{
             data: data
         }]
     });
}


function VIPState(){
	var active = 0;
	var suspend = 0;
	var stop = 0;
	var cancel = 0;
	$.ajaxSetup({
		async:false
	});
	$.post("VIPState",function(json){
		json = json.substr(1,json.length-2);
		var array = json.split(",");
		$.each(array , function(key,val){
			if(val.trim() == "active"){
				active++;
			}else if(val.trim() == "suspend"){
				suspend++;
			}else if(val.trim() == "stop"){
				stop++;
			}else if(val.trim() == "cancel"){
				cancel++;
			}
		});
		
	});
	var data = [active,suspend,stop,cancel];
	 $('#container').highcharts({
         chart: {
             type: 'column'
         },
         title: {
             text: '会员状态统计'
         },
         xAxis: {
             categories: [
                 '活动',
                 '暂停',
                 '停止',
                 '报停'
             ],
             labels: {
                 align: 'right',
                 style: {
                     fontSize: '13px',
                     fontFamily: 'Verdana, sans-serif'
                 }
             }
         },
         yAxis: {
             min: 0,
             title: {
                 text: '人数'
             }
         },
         legend: {
             enabled: false
         },
         tooltip: {
             pointFormat: '人数: <b>{point.y:.1f}</b>',
         },
         series: [{
             data: data
         }]
     });
}

function recordByDay(){
	var col = [];
	var data = [];
	$.ajaxSetup({
		async:false
	});
	$.post("recordByDay",function(json){
		json = json.substr(1,json.length-2);
		var array = json.split(",");
		$.each(array , function(key,val){
			val = val.trim();
			if($.inArray(val,col) >= 0){
				data[$.inArray(val,col)] += 1;
			}else{
				col.push(val);
				data.push(1);
			}
		});
		
	});
	 $('#container').highcharts({
         chart: {
             type: 'column'
         },
         title: {
             text: '每日使用统计'
         },
         xAxis: {
             categories: col,
             labels: {
                 align: 'right',
                 style: {
                     fontSize: '13px',
                     fontFamily: 'Verdana, sans-serif'
                 }
             }
         },
         yAxis: {
             min: 0,
             title: {
                 text: '次数'
             }
         },
         legend: {
             enabled: false
         },
         tooltip: {
             pointFormat: '使用次数: <b>{point.y:.1f}</b>',
         },
         series: [{
             data: data
         }]
     });
}

function placeUsage(){
	var col = [];
	var data = [];
	$.ajaxSetup({
		async:false
	});
	$.post("placeUsage",function(json){
		json = json.substr(1,json.length-2);
		var array = json.split(",");
		$.each(array , function(key,val){
			val = val.trim();
			if($.inArray(val,col) >= 0){
				data[$.inArray(val,col)] += 1;
			}else{
				col.push(val);
				data.push(1);
			}
		});
		
	});
	 $('#container').highcharts({
         chart: {
             type: 'column'
         },
         title: {
             text: '场地使用统计'
         },
         xAxis: {
             categories: col,
             labels: {
                 align: 'right',
                 style: {
                     fontSize: '13px',
                     fontFamily: 'Verdana, sans-serif'
                 }
             }
         },
         yAxis: {
             min: 0,
             title: {
                 text: '次数'
             }
         },
         legend: {
             enabled: false
         },
         tooltip: {
             pointFormat: '使用次数: <b>{point.y:.1f}</b>',
         },
         series: [{
             data: data
         }]
     });
}

function coachUsage(){
	var col = [];
	var data = [];
	$.ajaxSetup({
		async:false
	});
	$.post("coachUsage",function(json){
		json = json.substr(1,json.length-2);
		var array = json.split(",");
		$.each(array , function(key,val){
			val = val.trim();
			if($.inArray(val,col) >= 0){
				data[$.inArray(val,col)] += 1;
			}else{
				col.push(val);
				data.push(1);
			}
		});
		
	});
	 $('#container').highcharts({
         chart: {
             type: 'column'
         },
         title: {
             text: '教练活动统计'
         },
         xAxis: {
             categories: col,
             labels: {
                 align: 'right',
                 style: {
                     fontSize: '13px',
                     fontFamily: 'Verdana, sans-serif'
                 }
             }
         },
         yAxis: {
             min: 0,
             title: {
                 text: '次数'
             }
         },
         legend: {
             enabled: false
         },
         tooltip: {
             pointFormat: '活动次数: <b>{point.y:.1f}</b>',
         },
         series: [{
             data: data
         }]
     });
}