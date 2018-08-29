<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib uri="/struts-tags" prefix="s"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.9.1.js"></script>
<link href="Dynamic/css/plugins/chartist/chartist.min.css" rel="stylesheet">


<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
 <link href="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css"
        rel="stylesheet" type="text/css" />
    <script src="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/js/bootstrap-multiselect.js"
        type="text/javascript"></script>
 <link href="loader/dist/loading.min.css" rel="stylesheet">
 <link href="Dynamic/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <script src="Dynamic/js/plugins/datapicker/bootstrap-datepicker.js"></script>
 
    <script type="text/javascript">
$(document).ready(function(){
	 
	  $("#dateSelect12").datepicker({
	   	 todayBtn: "linked",
	        autoclose: true,
	        format: "yyyy-mm-dd",
	    }); 
	  	
});	
</script>
   <style>
        
        .multiselect
        {
        width:125%;
        
        }
        .multiselect-container {
  position: absolute;
  list-style-type: none;
  margin: 0;
  padding: 0;
  height: 200px;
  overflow: auto;
}
</style>
 
<title>Dashboard</title>


<style type="text/css">
    .fx-bordcolor{
        color: #ec0202;
        
    }
    .ibox-shadow{
    box-shadow: 5px 7px 11px 8px rgba(173,164,164,0.5);
}
.fx-bg-blue{
      border-color: #1c84c6!important;
}
.fx-pink{
      color: #ed5565!important;
}
.fx-yellow{
    color: #f89c59!important;
}
.fx-bg-pink{
     border-color: #ed5565!important;
}
.fx-bg-yellow{
     border-color: #f89c59!important;
}
.fx-bg-lazur{
     border-color: #23c6c8!important;
}
.fx-bg-navy{
     border-color: #1ab394!important;
}
.fx-fa{
font-size:25px;
}
</style>

 
<%-- <script type="text/javascript">

$(document).ready(function()
{
	 
	//TAX DETAIL
	$('.taxDetailSubmitCls').click(function()
	{
		alert();
		var theatre_id = $('#theaterId').val();
		var date =$("#dateId").val(); 
		alert(theatre_id);
		alert(date);
		jQuery.ajax({
			data :{
				theatre_id : theatre_id,
				date : date
				},
			url : "theatreTaxDetail",
			type : "POST",
			success : function(results) 
			{
				 alert(results); 
				 var page = JSON.parse(results);
			
				 var str = "";
				 
				 $("#taxDetailDivId").empty();
				 
				 str +='<form class="form-horizontal">';
				 str +='<div class="row">';
				 str +='<div class="col-md-10">';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">Theatre Name</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['theater_name']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">Screen Count</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7">'+page['screen_count']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">Collection Amount</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['total_amount']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">CGST(14%)</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['cgst_tax']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">SGST(14%)</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['sgst_tax']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">ET(8%)</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['et_tax']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">Total Payable amount</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['total_payable_amount']+'</div>';
				 str +='</div>';
				 str +='<div class="row">';
				 str +='<div class="col-md-3">';
				 str +='<div class="form-group text-center">';
				 str +='<button class="col-md-12 btn btn-danger pull-right">Pay tax</button>';
				 str +='</div>';
				 str +='</div>';
				 str +='</div>';
				 str +='</div>';
				 str +='<div class="col-md-2 col-sm-12 col-xs-12">';
				 str +='</div>';
				 str +='</div>';
				 str +='</form>';
				 $("#taxDetailDivId").append(str);
				 $("#mainDivId").show();
			}
		});
	});
	
});
</script> --%>

<script type="text/javascript">

$(document).ready(function()
{
	 
	//TAX DETAIL
	$('.taxDetailSubmitCls').click(function()
	{
		alert();
		var theatre_id = $('#theaterId').val();
		var dateRange =$("#dateControlDivId1").val(); 
		alert(theatre_id);
		jQuery.ajax({
			data :{
				theatre_id : theatre_id,
				dateRange : dateRange
				},
			url : "theatreTaxDetail",
			type : "POST",
			success : function(results) 
			{
				 alert(results); 
				 var page = JSON.parse(results);
			
				 var str = "";
				 
				 $("#taxDetailDivId").empty();
				 
				 str +='<form class="form-horizontal">';
				 str +='<div class="row">';
				 str +='<div class="col-md-10">';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">Theatre Name</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['theater_name']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">Screen Count</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7">'+page['screen_count']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">Collection Amount</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['total_amount']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">CGST(14%)</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['cgst_tax']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">SGST(14%)</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['sgst_tax']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">ET(8%)</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['et_tax']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label class="col-md-4">Total Payable amount</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['total_payable_amount']+'</div>';
				 str +='</div>';
				 str +='<div class="row">';
				 str +='<div class="col-md-3">';
				 str +='<div class="form-group text-center">';
				 str +='<button class="col-md-12 btn btn-danger pull-right">Pay tax</button>';
				 str +='</div>';
				 str +='</div>';
				 str +='</div>';
				 str +='</div>';
				 str +='<div class="col-md-2 col-sm-12 col-xs-12">';
				 str +='</div>';
				 str +='</div>';
				 str +='</form>';
				 $("#taxDetailDivId").append(str);
				 $("#mainDivId").show();
			}
		});
	});
	
});
</script>


</head>
<body>

 <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>DashBoard </h2>
               
            </div>
            <div class="col-lg-2">

            </div>
        </div>
      

        <div class="wrapper wrapper-content ">
         <div class="row">
                    <div class="col-lg-4">
                        <div class="ibox  ibox-shadow float-e-margins">
                            <div class="ibox-title fx-bg-yellow">
                            <span class="pull-right"><i class="fa fa-life-ring fx-pink fx-fa"></i></span>
                                <h5>THEATRE COUNT</h5>
                            </div>
                            <div class="ibox-content">
                                <h1 class="no-margins font-bold">5</h1>
                              
                         
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="ibox ibox-shadow float-e-margins">
                            <div class="ibox-title fx-bg-pink">
                          <span class="pull-right "><i class="fa fa-film fx-yellow fx-fa"></i></span>
                                <h5>SCREEN COUNT</h5>
                            </div>
                            <div class="ibox-content">
                                <h1 class="no-margins font-bold">10</h1>
                             
                                
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="ibox ibox-shadow float-e-margins">
                            <div class="ibox-title fx-bg-blue">
                          <span class="pull-right "><i class="fa fa-money text-navy fx-fa"></i></span>
                                <h5>TOTAL INCOME <small class="font-bold">(INR)</small></h5>
                            </div>
                            <div class="ibox-content">
                                <h1 class="no-margins font-bold">1000</h1>
                             
                                
                            </div>
                        </div>
                    </div>
             
        </div>  
        <div class="ibox-content fx-bg-navy m-t-lg">
         <div class="row">
             <div class="col-md-3">
                   <label>Select Theatre</label>
              <s:select list="taxMap" id="theaterId" name="theaterOwnerBean.theatre_id" headerKey="" headerValue="--Select Theatre--" cssClass="validate[required] theatreIdCls form-control"/><br>
                  <!-- <option value="">Select Theatres</option>
                  <option value="1">Theatre 1</option>
                  <option value="1">Theatre 2</option>
                  <option value="1">Theatre 3</option>
                  <option value="1">Theatre 4</option>
                  <option value="1">Theatre 5</option> -->
           </div>
           <div class="col-md-3">
           <label>Date Range</label>
                  <input type="text" class="input-sm form-control " id="dateControlDivId1" name="start"  value=""/>
           </div>
           <div class="col-md-1">
             <button class="btn btn-primary m-t-md pull-right font-bold taxDetailSubmitCls"  >Submit</button>
           </div>
         </div>
     </div>
        <div class="row" id="mainDivId" style="display: none">
  <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="ibox-title fx-bg-navy m-t-lg">
     <h2> Tax details</h2>
    </div>
    <div class="ibox-content" id="taxDetailDivId">
    <form class="form-horizontal">
    <div class="row">
      <div class="col-md-10">
       <div class="form-group">
              <label class="col-md-4">
                 Theatre Name
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                    <s:property value="theater_name"/>
                </div>
        </div>

         <div class="form-group">
              <label class="col-md-4">
                  Screen Count
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7">
                    <s:property value="screen_name"/>
                </div>
        </div>

         

         <div class="form-group">
              <label class="col-md-4">
                Collection Amount
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                    <s:property value="total_amount"/>
                </div>
        </div>

        
         <div class="form-group">
              <label class="col-md-4">
                 CGST(14%)
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                   <s:property value="cgst_tax"/>
                </div>
        </div>
         <div class="form-group">
              <label class="col-md-4">
                  SGST(14%)
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                   <s:property value="sgst_tax"/>
                </div>
        </div>
         <div class="form-group">
              <label class="col-md-4">
                 ET(8%)
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                   <s:property value="et_tax"/>
                </div>
        </div>
        
         <div class="form-group">
              <label class="col-md-4">
                  Total Payable amount
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                   <s:property value="total_payable_amount"/>
                </div>
        </div>
      <!--    <div class="form-group">  
           <label class="col-md-12 text-center">
                  Need to pay tax
              </label>
          </div> -->
    <div class="row">
     <div class="col-md-3">
        <div class="form-group text-center">  
           <button class="col-md-12 btn btn-danger pull-right">
                  Pay tax
              </button> 
          </div>
      </div>
  </div>
</div>

      <div class="col-md-2 col-sm-12 col-xs-12">
        
      </div>
    </div>
  </form>
 </div>
</div>
</div> 
         <div class="row">
                <div class="col-lg-12">
                        <div class="ibox-content">

                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-1"><b><i class="fa fa-film fx-bordcolor"></i> Movie</b></a></li>
                            <li class=""><a data-toggle="tab" href="#tab-2"><i class="fa fa-desktop fx-bordcolor"></i>Screen</a></li>
                        </ul>
                        <div class="tab-content">
                               <div id="tab-1" class="tab-pane active">
                                        <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-3 col-sm-9 col-xs-12 pull-left">
                <label>Date</label>
             <input type="text" class="input-sm form-control dateControlDivId12" id="dateId1" name="start"  value=""/>
                
<!--               <input type="text" class="input-sm form-control "  id="dateSelect12" name="start" value=""/>
 -->       
          </div>
         <div class="col-md-1"><button class="btn btn-primary m-t-md TheatreticketCountPiecls">Submit</button>
       
         </div>
         
          
            <div id="container-tml" >
         </div>
      </div>
                                     
          <div class="row m-t-xl">
            <div class="col-md-7">
                  
                               
            </div>
            <div class="col-md-5 ">
                                <!-- hover table -->
                           <div class="table-responsive" id="tableDivId" style="display: none">
                            <table class="table table-bordered " style="overflow-x: scroll;text-overflow: ellipsis;">
                                <thead>
                                <tr>
                                    <th>Movie Name</th>
                                    <th>Sold Tickets</th>
                                    <th>Total Collection Amount</th>
                                    
                                </tr>
                                </thead>
                                <tbody id="movieDetailDivId">
                                <tr>
                                    <td>Pyaar prema kaadhal</td>
                                    <td>500</td>
                                    <td>Rs.10,000</td>
                                     
                                </tr>
                                </tbody>
                            </table>
                        </div>
            </div>
              
          </div>

                   </div>
                            </div>
                            <div id="tab-2" class="tab-pane">
                                <div class="panel-body">
                                  <div class="row">
                                    <div class="col-md-3 col-sm-9 col-xs-12 pull-left">
                <label>Date</label>
                <input type="text" class="input-sm form-control dateSelect2" id="selectShowDateId1" name="start" value=""/>          
          </div>
          <div class="col-md-1"><button class="btn btn-primary m-t-md ScreenCountPiecls">Submit</button></div>
      </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                        
                                            <div id="container-donut"></div>
                                        </div>
                                    </div>
                                     <hr>
                                  <div class="row">
                                    <h3><b>SHOW-WISE DETAILS</b></h3>
                                      <div class="col-md-12">
                                          <div id="container-show1" ></div>
                                          
                                      </div>
                                      
                                  </div>
                                </div>
                            </div>
                        </div>


                    </div>
               
                        </div>
                  
                </div>

            </div>
            
         

        </div>

</div>

 <script src="Dynamic/js/highstock.js"></script>
    <script src="Dynamic/js/exporting.js"></script>
    <script src="Dynamic/js/highcharts-3d.js"></script>
    <script src="Dynamic/js/highcharts-more.js"></script>
  
  <script type="text/javascript">


// Radialize the colors
Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
    return {
        radialGradient: {
            cx: 0.5,
            cy: 0.3,
            r: 0.7
        },
        stops: [
            [0, color],
            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
        ]
    };
});

// Build the chart
Highcharts.chart('container-scl', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: 'Theatre Movie List'
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
                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                style: {
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                },
                connectorColor: 'silver'
            }
        }
    },
    series: [{
        name: 'Movies',
        data: [
            { name: ' Pyaar prema kaadhal', y:4.77  },
            {
                name: 'Viswaroopam2',
                y: 24.03,
                sliced: true,
                selected: true
            },
            { name: 'Junga', y: 10.38 },
            { name: 'Gold', y: 56.33}, 
            { name: 'Satyameva jayathey', y: 0.91 },
            
        ]
    }]
});
        </script>



    


 
   <script type="text/javascript">
        $(document).ready(function(){
          
        	  
            $(document).on('click','.TheatreticketCountPiecls',function(){
            	alert();
            	  var date = $("#dateId1").val();
            		alert(date);
    jQuery.ajax({
           data :{
            	date : date
          },      
       url : "TheatreOwnerMovieDetails",
        type : "POST",
        async:false,
        success : function(results) 
        {
            
            var page = JSON.parse(results);
            var str ="";
             alert(results);
            var labelcatPair = [];
            var countPair = [];
        for(var x in page)
        {
        var obj1 = page[x];

        labelcatPair.push(obj1['movie_name']);
        countPair.push({ name: obj1['movie_name'], y: parseInt(obj1['ticket_amount'])});
        
        $("#movieDetailDivId").empty();
    	
    	str +='<tr>';
    	str +='<td>'+obj1['movie_name']+'</td>';
    	str +='<td>'+obj1['seat_count']+'</td>';
    	str +='<td>Rs.'+obj1['ticket_amount']+'</td>';
    	str +='</tr>';
    	
    	$("#movieDetailDivId").append(str);
        }  

        $("#tableDivId").show();
        
        
        
Highcharts.chart('container-tml', {
    chart: {
        type: 'pie',
        options3d: {
            enabled: true,
           alpha: 45,
            beta: 0
        }
    },
    title: {
        text: 'Movie Wise Details'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            depth: 35,
            dataLabels: {
                enabled: true,
                format: '{point.name}'
            }
        },
        series: {
            point: {
                 events: {
                  click: function (event) { 
                      name = this.name; 
                      alert(name); 
                      var mvName = "";
                      var ticket = "";
                      var amount = "";
                      for(var x in page)
                      {
                      var obj1 = page[x];
                        
                      if(name == obj1['movie_name'] ){
                    	  mvName = obj1['movie_name'];
                    	  ticket  = parseInt(obj1['seat_count']);
                    	  amount  = parseInt(obj1['ticket_amount']);
                          
                    	  movieCollectionDetail(mvName,ticket,amount);
                      }
                            
                      }  
                     }
                 }
             }
         }
    },
    series: [{
        type: 'pie',
        name: 'Collections',
        data: countPair
    }]
});
     }
    });
    });
            
            function movieCollectionDetail(mvName,ticket,amount){
            	
            	var str ="";
            	
            	$("#movieDetailDivId").empty();
            	
            	str +='<tr>';
            	str +='<td>'+mvName+'</td>';
            	str +='<td>'+ticket+'</td>';
            	str +='<td>Rs.'+amount+'</td>';
            	str +='</tr>';
            	
            	$("#movieDetailDivId").append(str);
            } 
            
            
            
    });
        
 </script>
 
 
 
 //Screen-Wise
 
     <script type="text/javascript">
   $(document).ready(function(){
       
      
       $(document).on('click','.ScreenCountPiecls',function(){
           var date = $("#selectShowDateId1").val();
            alert(date); 
jQuery.ajax({
    data :{
        date : date
     },      
  url : "TheatreOwnerMovieScreenWiseDetails",
   type : "POST",
   async:false,
   success : function(results) 
   {
       
       var page = JSON.parse(results);
       /* var str =""; */
        alert(results);
       var labelcatPair = [];
       var countPair = [];
   for(var x in page)
   {
   var obj1 = page[x];

   labelcatPair.push(obj1['screen_name']);
   countPair.push({ name: obj1['screen_name'], y: parseInt(obj1['ticket_amount'])});
   
   }  

   $("#tableDivId").show();

var colors = Highcharts.getOptions().colors,
    categories = ['dog', 'bird', ],

    data = [{
        y: 56.33,
        drilldown: {
            name: 'Collections',
            categories: labelcatPair,
            data: countPair,
            
                 }
    }],
    browserData = [],
    versionsData = [],
    i,
    j,
    dataLen = data.length,
    drillDataLen,
    brightness;


// Build the data arrays
for (i = 0; i < dataLen; i += 1) {

    // add browser data
    browserData.push({
        name: categories[i],
        y: data[i].y,
        color: data[i].color
    });

    // add version data
    drillDataLen = data[i].drilldown.data.length;
    for (j = 0; j < drillDataLen; j += 1) {
        brightness = 0.2 - (j / drillDataLen) / 5;
        versionsData.push({
            name: data[i].drilldown.categories[j],
            y: data[i].drilldown.data[j],
            color: Highcharts.Color(data[i].color).brighten(brightness).get()
        });
    }
}

// Create the chart
Highcharts.chart('container-donut', {
    chart: {
        type: 'pie'
    },
    title: {
        text: 'Screen Wise Details'
    },
    yAxis: {
        title: {
            text: 'Total percent market share'
        }
    },
    plotOptions: {
        pie: {
            shadow: false,
            center: ['50%', '50%']
        }
    },
     ////////For Color/////////
    plotOptions: {
        series: {
            colorByPoint: true
        }
    },
    ////////For Color/////////
       

    series: [ {
        name: 'Collections',
        data: countPair,
        size: '80%',
        innerSize: '60%',
      
        id: 'versions'
    }],
 
    responsive: {
        rules: [{
            condition: {
                maxWidth: 400
            },
            chartOptions: {
                series: [{
                    id: 'versions',
                    dataLabels: {
                        enabled: false
                    }
                }]
            }
        }]
    },
    
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            depth: 35,
            dataLabels: {
                enabled: true,
                format: '{point.name}'
            }
        },
        series: {
            point: {
                 events: {
                  click: function (event) { 
                		labelcatPair = [];
           	     		countPair = [];
                	    for(var x in page)
                	    {	
                	    var obj1 = page[x];
           	     	
           	     		alert(obj1['screen_name']);
           	     		alert(obj1);
                	    	/* var obj2 = page[x]; */
                	    	
                	    	var show = obj1['showwiseList'];
                	    	
                	    	for(var i in show)
                	    		{
                	    		var list = show[i];
                    	    	labelcatPair.push(list['show_timing']);
                         	    countPair.push(list['ticket_amount']);
                	    		}
                	
                	    }
                	 
                	var chart = Highcharts.chart('container-show1', {

                	    title: {
                	        text: 'Show Wise Details'
                	    },

                	    xAxis: {
                	    	type: 'category',
                	        categories: labelcatPair,
                	    },

                	    series: [{
                	        type: 'column',
                	        colorByPoint: true,
                	        name: 'Collection',
                	        data: countPair,
                	        showInLegend: false
                	    }]

                	});


                	$('#plain').click(function () {
                	    chart.update({
                	        chart: {
                	            inverted: false,
                	            polar: false
                	        },
                	        subtitle: {
                	            text: 'Plain'
                	        }
                	    });
                	});  
                     }
                 }
             }
         }
    },
    
    
});
} 
});
});
});
</script>
    

  <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

 <script>
$(function() {
	
	$('#dateControlDivId1').val('');

  $('#dateControlDivId1').daterangepicker({
	  "autoApply": true,
     opens: 'center', 
     locale: {
         format: 'YYYY/MM/DD'
       }
  });
  
	$('#dateControlDivId1').val('');

});


</script> 

 <script>
$(function() {
	
	$('.dateControlDivId12').val('');

  $('.dateControlDivId12').daterangepicker({
	  "autoApply": true,
     opens: 'center', 
     locale: {
         format: 'YYYY/MM/DD'
       }
  });
  
	$('.dateControlDivId12').val('');

});
</script> 

   <script type="text/javascript">
$(document).ready(function(){
	 
	  $(".dateSelect2").datepicker({
	   	 todayBtn: "linked",
	        autoclose: true,
	        format: "yyyy-mm-dd",
	    }); 
	  	
});	
</script>
   
  
  
         
         </body>
</html>