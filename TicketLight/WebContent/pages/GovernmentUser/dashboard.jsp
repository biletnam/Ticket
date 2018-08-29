<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
    .fx-pink-bg{
  color: #fb0174;
}
.fx-font16{
  font-size: 16px;
}

ul li{
  list-style: none;
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
.ibox-shadow{
    box-shadow: 5px 7px 11px 8px rgba(173,164,164,0.5);
}
.highcharts-credits
{
	display: none;
}
</style>
<script type="text/javascript">
$(document).ready(function()
{	
	$('.dashboardCls').addClass("active");
	
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
   <div class="row m-t-xs">
       <div class="ibox-content  ">
            <div class="row">
             
           
           <div class="col-md-4">
           <label>Date Range</label>
              <div class="input-daterange input-group" id="datepicker">
                  <input type="text" class="input-sm form-control" name="start" value="05/14/2014"/>
                   <span class="input-group-addon">to</span>
                  <input type="text" class="input-sm form-control" name="end" value="05/22/2014" />
             </div>
           </div>
           <div class="col-md-2">
             <button class="btn btn-primary m-t-md pull-right font-bold">Submit</button>
           </div>
         </div>
         
       </div>

       <div class="ibox-content">
         <div id="container-g1" ></div>
       </div>


        
       <div class="ibox-content m-t-xs ">
       <div class="row">
       <div class="col-md-12 m-b-sm">
          <h3>Theatre Wise TAX Details</h3>
       </div>
       </div>
            <div class="row">
             <div class="col-md-3">
             <label >District</label>
              <select class="form-control">
                  <option value="">Select</option>
                  <option value="">All</option>
                  <option value="1">Chennai</option>
                  <option value="1">Salem</option>
                  <option value="1">Coimbatore</option>
                  <option value="1">Erode</option>
                  <option value="1"> Tiruppur </option>
             </select>
           </div>
           <div class="col-md-3">
           <label>City</label>
              <select class="form-control">
                  <option value="">Select</option>
                  <option value="">All</option>            
                  <option value="1">Chennai</option>
                  <option value="1">Salem</option>
                  <option value="1">Coimbatore</option>
                  <option value="1">Erode</option>
                  <option value="1"> Tiruppur </option>
             </select>
           </div>
           
           <div class="col-md-4">
           <label>Date Range</label>
              <div class="input-daterange input-group" id="datepicker">
                  <input type="text" class="input-sm form-control" name="start" value="05/14/2014"/>
                   <span class="input-group-addon">to</span>
                  <input type="text" class="input-sm form-control" name="end" value="05/22/2014" />
             </div>
           </div>
           <div class="col-md-2">
             <button class="btn btn-primary m-t-md pull-right font-bold">Submit</button>
           </div>
         </div>
         
       </div>


<div class="ibox-content">
 <input type="text" class="form-control input-sm m-b-xs" id="filter"
                                   placeholder="Search in table">
                            <table class="footable table table-stripped " data-page-size="8" data-filter=#filter>
                                <thead>
                                <tr>

                                    <th >Theatre Name</th>
                                    <th>District</th>
                                    <th>City</th>
                                    <th>Date Range</th>
                                    <th>Paymnet ( &#8377;)</th>
                                    <th>Tax Amount ( &#8377;)</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td class="fx-font16 font-bold"><a href="#"> PVR CINEMAS </a></td>
                                    <td>Vellore</td>
                                    <td>Arcot</td>
                                    <td>21-07-2018 to 21-08-2018</td>
                                    <td>1500000</td>
                                    <td>15000</td>
                                    <td><label class="label label-danger">Pending</label></td>
                                </tr>
                                  <tr>
                                    <td class="fx-font16 font-bold"><a href="#"> SR CINEMAS </a></td>
                                    <td>Vellore</td>
                                    <td>Katpadi</td>
                                     <td>21-07-2018 to 21-08-2018</td>
                                    <td>1500000</td>
                                    <td>15000</td>
                                    <td><label class="label label-danger">Pending</label></td>
                                </tr>
                                  <tr>
                                    <td class="fx-font16 font-bold"><a href="#"> ROHINI SILVER SCREEN </a></td>
                                    <td>Erode</td>
                                    <td>Erode</td>
                                     <td>21-07-2018 to 21-08-2018</td>
                                    <td>1500000</td>
                                    <td>12000</td>
                                    <td><label class="label label-danger">Pending</label></td>
                                </tr>
                                  <tr>
                                    <td class="fx-font16 font-bold"><a href="#"> KASI TALKIES </a></td>
                                    <td>Chennai</td>
                                    <td>Chennai</td>
                                     <td>21-07-2018 to 21-08-2018</td>
                                    <td>1500000</td>
                                    <td>15000</td>
                                    <td><label class="label label-primary">Success</label></td>
                                </tr>
                                
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="7">
                                        <ul class="pagination pull-right"></ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>

                        </div>
  </div>
   </div>
   
      <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
   

     <!-- FooTable -->
    <script src="js/plugins/footable/footable.all.min.js"></script>
  <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function() {

            $('.footable').footable();
            $('.footable2').footable();

        });

    </script>

    <script src="Dynamic/js/highstock.js"></script>
    <script src="Dynamic/js/exporting.js"></script>

    <script src="js/data.js"></script>
<script src="js/drilldown.js"></script>


    <script type="text/javascript">

// Create the chart
Highcharts.chart('container-g1', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'District Wise Payment Report'
    },
    xAxis: {
        type: 'category'
    },
    yAxis: {
        title: {
            text: 'Total percent market share'
        }

    },
    legend: {
        enabled: false
    },
    plotOptions: {
        series: {
            borderWidth: 0,
            dataLabels: {
                enabled: true,
            }
        }
    },
    series: [{
        name: 'Brands',
        colorByPoint: true,
        data: [{
            name: 'Chennai',
            y: 2123512,
            drilldown: 'Chennai'
        }, {
            name: 'Coimbatore',
            y: 1889843,
            drilldown: 'Coimbatore'
        }, {
            name: 'Vellor',
            y: 4149856,
            drilldown: 'Vellor'
        }, {
            name: 'Erode',
            y: 2326136,
            drilldown: 'Erode'
        }, {
            name: 'Kumbakonam',
            y: 1778456,
            drilldown: 'Kumbakonam'
        }]
    }],
    drilldown: {
        series: [{
            name: 'Chennai',
            id: 'Chennai',
            data: [
                [
                    'PVR',
                    12520
                ],
                [
                    'SR CINEMAS',
                    135005
                ],
                [
                    'KASI',
                    168900
                ],
                [
                    'ROHINI SILVER SCREEN',
                    157000
                ],
                [
                    'SK CINEMAS',
                    1650087
                ]
            ]
        }, {
            name: 'Coimbatore',
            id: 'Coimbatore',
            data: [
                [
                    'APA CINEMAS',
                    145689
                ],
                [
                    'ARASAN THEATRE',
                    157654
                ],
                [
                    'FUN CINEMAS',
                    1405600
                ],
                [
                    'KAVERI CINEMA',
					180900                    
                ]
            ]
        }, {
            name: 'Vellor',
            id: 'Vellor',
            data: [
                [
                    'LAKSHMI THEARE',
                    154000
                ],
                [
                    'VENUS THEATRE',
                    170356
                ],
                [
                    'SRI VISHNU THEATRE',
                    148900
                ],
                [
                    'APADARA THEATRE',
                    1780065
                ],
                [
                    'PVR',
                    1896535
                ]
            ]
        }, {
            name: 'Erode',
            id: 'Erode',
            data: [
                [
                    'ABIRAMI MULTIPLEX',
                    189600
                ],
                [
                    'ANNAPORANI THEATRE',
                    205000
                ],
                [
                    'VIJAYAN THEATRE',
                    145006
                ],
                [
                    'MAHARAJA MULTIPLEX',
                    1786530
                ]
            ]
        }, {
            name: 'Kumbakonam',
            id: 'Kumbakonam',
            data: [
                [
                    'KASI THEATRE',
                    125300
                ],
                [
                    'VASU THEATRE',
                    145600
                ],
                [
                    'BARANIKA THEATRE',
                    1365056
                ],
                [
                    'SELVAM THEATRE',
                    142500
                ]
            ]
        }]
    }
});
    </script>

</body>
</html>