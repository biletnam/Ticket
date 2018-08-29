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
.fx-bd-tablehead{
  background-color: #333;
  color: white;
}
.txtoflow{

     
    overflow: hidden;
    text-overflow: ellipsis;

}
.highcharts-credits
{
display: none;
}
</style>
<script type="text/javascript">
$(document).ready(function()
{	
	$('.taxDetailCls').addClass("active");
	
});
</script>
</head>
<body>

    <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2 class="font-bold ">Theater Name <small>(AC Multiplex)</small> </h2>

               
            </div>
            <div class="col-lg-2">

            </div>
        </div>
   <div class="row m-t-xs">
       <div class="ibox-content  ">
           <div class="row">
                    <div class="col-lg-4">
                        <div class="ibox  ibox-shadow float-e-margins">
                            <div class="ibox-title fx-bg-yellow">
                            <span class="pull-right"><i class="fa fa-life-ring fx-pink fx-fa"></i></span>
                                <h5>TOTAL SEAT COUNT</h5>
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
                                <h5>TOTAL SEATS SOLD</h5>
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
                                <h5>TOTAL AMOUNT <small class="font-bold">(INR)</small></h5>
                            </div>
                            <div class="ibox-content">
                                <h1 class="no-margins font-bold">1000</h1>
                             
                                
                            </div>
                        </div>
                    </div>
             
        </div>
         
       </div>

       <div class="ibox-content">
        

         <div class="row m-t-md">
           <div class="col-md-8">
             <div class="row">
               <div id="container-t1" ></div>
               
             </div>
             <div class="row">
               <div class="ibox-content">

                        <table class="table table-striped font-bold">
                            <thead class="fx-bd-tablehead">
                            <tr>
                                <th>Screen Name</th>
                                <th>Total Seat Count</th>
                                <th>Sold Tickets</th>
                                <th>Total Amount</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Krishna Theatre</td>
                                <td>300</td>
                                <td>250</td>
                                <td> 25000 </td>
                            </tr>
                           
                         
                          
                            </tbody>
                        </table>
                    </div>
             </div>
           </div>
           <div class="col-md-4 gray-bg">
             <h2>Theatre Owner Details</h2>
             <div class="row">
               <div class="col-lg-12">
                <div class="contact-box">
                    
                    <div class="col-sm-4">
                        <div class="text-center">
                            <img alt="image" class="img-circle m-t-xs img-responsive" src="Dynamic/img/Profile.png">
                            
                        </div>
                    </div>
                    <div class="col-sm-8">
                        <h3><strong>Thangaraj Kumar </strong></h3>
                        <!-- <p><i class="fa fa-map-marker"></i> Sadhasivam nagar, Chennai</p> -->
                        <p title="thangarajkumar@gmail.com" class="txtoflow m-b-sm"> thangarajkumar@gmail.com </p> 
                        <address>
                            <strong>Address</strong><br>
                            49,1st Floor, Bazzar road, Sadhasivam Nagar<br>
                            Madipakkam, Chennai.<br>

                            <b title="Phone">Ph:</b> 7904111912
                        </address>
                    </div>
                    <div class="clearfix"></div>
                        
                </div>
            </div>
             </div>
           </div>
         </div>
        


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
Highcharts.chart('container-t1', {
    chart: {
        type: 'pie'
    },
    title: {
        text: 'Screenwise seat count'
    },
  
    plotOptions: {
        series: {
            dataLabels: {
                enabled: true,
            }
        }
    },

    series: [{
        name: 'THEATRE DETAILS',
        colorByPoint: true,
        data: [{
            name: 'SCREEN 1',
            y: 14200,
            drilldown: 'SCREEN 1'
        },{
            name: 'SCREEN 3',
            y: 124500,
            drilldown: 'SCREEN 3'
        },{
            name: 'SCREEN 4',
            y: 165200,
            drilldown: 'SCREEN 4'
        },
        {
            name: 'SCREEN 2',
            y: 15460,
            drilldown: 'SCREEN 2'
          }]
    }],
    drilldown: {
        series: [{
            name: 'SCREEN 1',
            id: 'SCREEN 1',
            data: [
                ['TOTAL SEAT COUNT', 600],
                ['SOLD TICKETS', 512],
                ['TOTAL AMOUNT', 12500]
                
            ]
        }, 
        {
            name: 'SCREEN 3',
            id: 'SCREEN 3',
            data: [
            	 ['TOTAL SEAT COUNT', 356],
                 ['SOLD TICKETS', 257],
                 ['TOTAL AMOUNT', 145263]
            ]
      
        },
        {
            name: 'SCREEN 4',
            id: 'SCREEN 4',
            data: [
            	 ['TOTAL SEAT COUNT', 300],
                 ['SOLD TICKETS', 200],
                 ['TOTAL AMOUNT', 1350]
            ]
      
        },
        
        {
            name: 'SCREEN 2',
            id: 'SCREEN 2',
            data: [
            	 ['TOTAL SEAT COUNT', 300],
                 ['SOLD TICKETS', 215],
                 ['TOTAL AMOUNT', 13500]
            ]
      
        }]
    }
});
    </script> 
 
</body>
</html>