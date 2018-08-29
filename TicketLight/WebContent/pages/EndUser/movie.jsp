<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                    <%@ taglib uri="/struts-tags" prefix="s"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">

 .imgbanner{
  background:  linear-gradient(45deg, rgba(22, 23, 23, 0.88), rgba(22, 23, 23, 0.27)),center top no-repeat; 
  height: 277px;
  background-size: cover;  
  margin-top: 86px;
  position: relative;

  } 
 
  .imgbanner1{
  background:  linear-gradient(45deg, rgba(22, 23, 23, 0.88), rgba(22, 23, 23, 0.27)),center top no-repeat; 
  height: 277px;
  background-size: cover;  
  margin-top: 86px;
  position: relative;

  }  
.fontcolor1{

  color: white;
  font-weight: bold;
  }

  .mtop1{
       margin-top: 156px;
  }
  .img1{
    width: 56px;
    height: 51px;
    border-radius: 25px;

  }
  .figure-caption {
    font-size: 45%!important;
    color: white!important;
}
.fontsize2{
      font-size: 8px;
}
.fontsize1{
      font-size: 13px;
}

.border3{
    border: 0.5px solid white;
    padding: 3px 12px;
    border-radius: 10px;
    color: white;
    font-size: 12px;

  }
  .border2{
    border: 1px solid white;
    padding:5px;
    border-radius: 50%;
     color: white;
    font-size: 10px

  }
  
  .theater{
  font-size: 14px;
  font-weight: 600;
  color: black;
  margin-top: 20px;
}
.theater>a {
  color: black;
}
.theater>a:hover{
  text-decoration: underline;
}
.date{
  font-size: 13px;
  font-weight: 600;
  color: black;
}
.tamil-2D{
margin-top: 28px;
}
.list1{
  list-style: none;
  margin-left: -3%;
  
}
.list1>li{
  float: left;
  padding: 10px;
}
.ticket1{
border-bottom: 1px solid #ddd
}
.filtop{
  margin-top: 18px;
}
.filttop{
  margin-top: 18px;
}
 @media only screen and (max-width: 800px) and (min-width: 300px)  {
    .mtop1{
      margin-top: 2px;
    }
.fontsize1{
      font-size: 9px;
}
 .img1{
        width: 30px;
    height: 35px;
        border-radius: 25px;
  }
  .fontsize11{
    font-size: 20px;
  }

  }

.jR3DCarouselGalleryCustomeTemplate a{
	text-decoration: none;			
}
  }
.d-m-l-30{
  margin-left: 30px!important;
}
.ticpro{
  margin-left: 30px;
  margin-right: 30px;
}
.datesize{
font-size:18px; 
display: block;
font-weight: bold;
}

.moviename{
  font-size: 35px;
  color: white;
  font-weight: bold
}
.mntop{
  margin-top: 160px;
  margin-left: -40px;
}
.mv-details{
  font-size: 12px;
  color: white;
  margin-left: -20px
}

.mv-details1{
  font-size: 12px;
  color: white;
  
}
.d-m-l-30{
  margin-left: 30px!important;
}

@media (max-width: 767px) {
 .mntop{
  margin-top: 160px;
  margin-left: 5px;
}
.mv-details{
  font-size: 12px;
  color: white;
  margin-left: 25px
}
.imgsize{
  width: 20%!important;
}
.headsize{
	font-size: 11px;
	
}

     .footer {
    background: none repeat scroll 0 0 white;
    border-top: 1px solid #e7eaec;
    bottom: 0;
    left: 0;
    padding: 10px 20px;
    position: fixed!important;
    right: 0;
}
@media only screen and (max-width: 600px) {
     .footer {
    background: none repeat scroll 0 0 white;
    border-top: 1px solid #e7eaec;
    bottom: -129px;
    left: 0;
    padding: 10px 20px;
    position: absolute!important;
    right: 0;
}
}
.m-left{
margin-left: 150px;
}
}

 
  .list1{
  list-style: none;
  margin-left: 1%;
  margin-top: 10px;
  
}
 
.list1>li{
  float: left;
  padding: 4px;
}
.list2{
  list-style: none;
  
}
.list2>li{
  float: left;
  padding: 1px;
  margin-right: 5px;
}
 

</style>


<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="dist/jR3DCarousel.min.js"></script>

<!-- click show timing -->


<script type="text/javascript">
$(document).ready(function(){
	
	var carouselCustomeTemplateProps =  {
	 		  width: 1000, 				/* largest allowed width */
			  height: 475, 				/* largest allowed height */
			  slideLayout : 'fill',     /* "contain" (fit according to aspect ratio), "fill" (stretches object to fill) and "cover" (overflows box but maintains ratio) */
			  animation: 'slide3D', 	/* slide | scroll | fade | zoomInSlide | zoomInScroll */
			  animationCurve: 'ease',
			  animationDuration: 1900,
			  animationInterval: 2000,
			  slideClass: 'jR3DCarouselCustomSlide',
			  autoplay: true,
			  controls: true,			/* control buttons */
			  navigation: ''			/* circles | squares | '' */,
			  perspective:425,
			  rotationDirection: 'rtl',
			  onSlideShow: slideShownCallback
				  
		}


	function slideShownCallback($slide){
		console.log("Slide shown: ", $slide.find('img').attr('src'))
	}

	jR3DCarouselCustomeTemplate = $('.jR3DCarouselGalleryCustomeTemplate').jR3DCarousel(carouselCustomeTemplateProps);

  })

</script>
<script>
$(document).ready(function(){

$(".1").hide();
         $(".2").hide();
          $(".3").hide();
           $(".4").hide();
            $(".5").hide();
              $(".6").hide();
                   $(".7").hide();

           

    $(".btn1").hover(function(){
        $(".1").show();
         $(".2").hide();
          $(".3").hide();
           $(".4").hide();
            $(".5").hide();
             $(".6").hide();
                 $(".7").hide();
           
           
            
    });
    $(".btn2").hover(function(){
        $(".1").hide();
         $(".2").show();
          $(".3").hide();
           $(".4").hide();
              $(".5").hide();
               $(".6").hide();
                  $(".7").hide();
             
    });
    $(".btn3").hover(function(){
        $(".1").hide();
         $(".2").hide();
          $(".3").show();
           $(".4").hide();
            $(".5").hide();
             $(".6").hide();
                 $(".7").hide();
         
             
            
    });
    $(".btn4").hover(function(){
        $(".1").hide();
         $(".2").hide();
          $(".3").hide();
           $(".4").show();
            $(".5").hide();
             $(".6").hide();
               $(".7").hide();
           
             
            
    });
    $(".btn5").hover(function(){
        $(".1").hide();
         $(".2").hide();
          $(".3").hide();
           $(".4").show();
         $(".5").hide();
          $(".6").hide();
            $(".7").hide();
      
  
    });
     $(".btn6").hover(function(){
        $(".1").hide();
         $(".2").hide();
          $(".3").hide();
           $(".4").hide();
            $(".5").show();
             $(".6").hide();
               $(".7").hide();
          
           
    });
     $(".btn7").hover(function(){
        $(".1").hide();
         $(".2").hide();
          $(".3").hide();
           $(".4").hide();
            $(".5").hide();
             $(".6").show();
               $(".7").hide();
            
           
  
    });
      $(".btn8").hover(function(){
        $(".1").hide();
         $(".2").hide();
          $(".3").hide();
           $(".4").hide();
            $(".5").hide();
             $(".6").show();
               $(".7").hide();
  
    });
       $(".btn9").hover(function(){
        $(".1").hide();
         $(".2").hide();
          $(".3").hide();
           $(".4").hide();
            $(".5").hide();
             $(".6").hide();
                   $(".7").show();
             

           
  
    });
        $(".btn10").hover(function(){
        $(".1").hide();
         $(".2").hide();
          $(".3").hide();
           $(".4").hide();
            $(".5").hide();
             $(".6").hide();
              $(".7").show();
             
  
    });
});
</script>
<script type="text/javascript">
	$(document).ready(function()
	{
	
		$(document).on('click','.theaterCls',function()
		{
			var theaterId = $(this).attr('id');
			
			$("#hiddenTheaterId").val(theaterId);
			$("#theaterFormSubmitId").submit();
		});
		
		$(document).on('click','.dateSelectCls',function()
		{
			var id = $(this).attr('id');
			
			var id1 = id.split("_");
			var movie_id = id1[0];
			var date_id = id1[1];
							
			jQuery.ajax({
   			 
   			 data : {
   				 movieId : movie_id,
   				 dateId : date_id,
   				 },
   		    url  : "dateFilterInMoviewiseTheater",
   			type : "post",
   			success : function (results) {
   			
			 var page =  JSON.parse(results);
   			 var obj = page['theaterDetailList'];
   			 var date = page['date_list'];
   			 
			 var str = '';
			 var str1 = '';
			 
			 $("#theaterDateMainDivId").empty();

			 for(var z in date){
				 var obj1 = date[z];
				
				 if(z == 0){
					 
					 if(obj1['status'] == 'Success'){
						 
						 str1 +='<button type="button" class="d-m-l-30  btn btn-success  btn-sm m-r-xs active datsize dateSelectCls" id='+page['master_movie_id']+'_'+obj1['date_id']+'><span class="datesize">'+obj1['date']+'</span><small>'+obj1['dayName']+'</small></button>';
					 }
					 else{
						 str1 +='<button type="button" class="d-m-l-30  btn btn-light btn-success m-r-xs btn-sm dateSelectCls" id='+page['master_movie_id']+'_'+obj1['date_id']+'><span class="datesize">'+obj1['date']+'</span><small >'+obj1['dayName']+'</small></button>';
					 }
					 
				 }
				 else if(obj1['status'] == 'Success'){
					 
					 str1 +='<button type="button" class="btn btn-success  btn-sm m-r-xs active datsize dateSelectCls" id='+page['master_movie_id']+'_'+obj1['date_id']+'><span class="datesize">'+obj1['date']+'</span><small>'+obj1['dayName']+'</small></button>';
				 }
				 else{
					 str1 +='<button type="button" class="btn btn-light btn-success m-r-xs btn-sm dateSelectCls" id='+page['master_movie_id']+'_'+obj1['date_id']+'><span class="datesize">'+obj1['date']+'</span><small >'+obj1['dayName']+'</small></button>';
				 }
			 }
			 $("#theaterDateMainDivId").append(str1);
			 
			 $("#theaterMainDivId").empty();
		
			 for(var x in obj){
				 var obj1 = obj[x];
				 
				 str +='<div class="row ticket1 bg-white">';
				 str +='<div class="col-md-2 ">';
				 str +='<h6 class="theater"><a href="#" id='+obj1['theater_id']+' class="theaterCls">'+obj1['theater_name']+'</a></h6>';
				 str +='</div>';
				 str +='<div class="col-md-10 col-sm-12">';
				 str +='<ul class="list1">';
			
				 var obj2 = obj1['show_list'];

				 for(var y in obj2){
					 var obj3 = obj2[y];
				 
					 str +='<li>';
					 str +='<button id='+obj3['screen_id']+'_'+obj1['theater_id']+'_'+obj3['show_id']+'_'+obj3['movie_detail_id']+'_'+obj3['show_detail_id']+'_'+obj3['show_timing']+'_'+obj1['theater_name']+' type="button" class="btn btn-outline-primary btn-sm showTimingCls" data-toggle="modal" data-target="#myModal8">'+obj3['show_timing']+'</button>';
					 str +='</li>';
				
				 }
				str +='</ul>';
				str +='</div>';
				str +='</div>';
			 }
			 
			 $("#theaterMainDivId").append(str);
   				
			}
		   });
		});
		
	});
</script>

<script type="text/javascript">

$(document).on('click',".showTimingCls",function(){
	var id = $(this).attr("id");
	
	var res = id.split("_");
	var screen_id=res[0];
	var theatre_id=res[1];
	var show_time_id=res[2];
	var movie_details_id=res[3];
	var show_details_id=res[4];
	var show_timing=res[5];
	
	$("#hiddenfloorMovieName").val($('#floorMovieName').val());
	$("#hiddenfloorDateValue").val($('#floorDateValue').val());
	$("#hiddenfloorShowTiming").val(show_timing);
	$("#hiddenfloortheatreName").val($('#floortheatreName').val());
	  $("#hiddenValues").val(res);
	  $("#screenId").val(screen_id);
	  $("#theatreId").val(theatre_id);
	  $("#showTimeId").val(show_time_id);
	  $("#movieDetailsId").val(movie_details_id);
	  $("#showDetailsId").val(show_details_id);
	  
	   $(".btn2").addClass("active");
	   $(".2").show();
	   $("#hiddenSeatCountId").val(2);
	   
});


$(document).on('click',".seatCountCls",function(){
	var seat_count = $(this).attr('id');
	$("#hiddenSeatCountId").val(seat_count);
});

$(document).on('click',"#seatSubmitBtnId",function(){

	 $("#showLayoutSubFormId").submit();
});

</script>
</head>
<body class="fx-greycolor">

<s:form action="ticket-show-layout" id="showLayoutSubFormId" method="post">
<s:hidden name="userBean.show_values" id="hiddenValues"></s:hidden>
<s:hidden name="userBean.screen_id" id="screenId"></s:hidden>
<s:hidden name="userBean.theater_id" id="theatreId"></s:hidden>
<s:hidden name="userBean.show_timing_id" id="showTimeId"></s:hidden>
<s:hidden name="userBean.movie_details_id" id="movieDetailsId"></s:hidden>
<s:hidden name="userBean.show_detail_id" id="showDetailsId"></s:hidden>
<s:hidden name="userBean.movie_name" id="hiddenfloorMovieName" />
<s:hidden name="userBean.date" id="hiddenfloorDateValue" />
<s:hidden name="userBean.show_timing" id="hiddenfloorShowTiming" />
<s:hidden name="userBean.theatre_name" id="hiddenfloortheatreName" />
<s:hidden name="theaterOwnerBean.master_movie_id"></s:hidden>
<s:hidden name="theaterOwnerBean.city_id"></s:hidden>
<s:hidden name="theaterOwnerBean.status_id" id="1"></s:hidden>
<s:hidden name="userBean.selected_seat_count" id="hiddenSeatCountId"></s:hidden> 
</s:form>

<s:form action="theater-movies" id="theaterFormSubmitId">
<s:hidden name="theaterOwnerBean.theater_id" id="hiddenTheaterId"></s:hidden>
<s:hidden name="theaterOwnerBean.city_id"></s:hidden>
</s:form>


<div class="row imgbanner">
  <div class="col-md-12">
   <!--  <img class="intro-about1" src="getMasterMovieBgPoster.action?modelId=1" >  -->
  
   <div class="container">
    <div class="mntop">
       <span class="moviename"><s:property value="theaterOwnerBean.movie_name"/></span> <span class="border2 fontcolor1"><s:property value="theaterOwnerBean.movie_certification_name"/></span>
      
      <s:hidden name="theaterOwnerBean.movie_name" id="floorMovieName"/>
      
    </div>
     <div class="row m-t-sm" style="">
         <span class="mv-details" > <i class="fa fa-calendar"></i> <s:property value="theaterOwnerBean.movie_release_date"/></span>
         <span class="m-l-md mv-details1"> <i class="fa fa-clock-o m-r-xs"></i><s:property value="theaterOwnerBean.movie_duration"/></span>
       
      <s:iterator value="theaterOwnerBean.language_list">
        <span class="border3 m-l-md"><s:property/></span>
     </s:iterator>
   
      <s:iterator value="theaterOwnerBean.genre_list">
        <span class="border3 m-l-md"><s:property/></span>
   </s:iterator>
   <span class="border2 m-l-sm fontcolor1"><s:property value="theaterOwnerBean.movie_format_name"/></span>
       </div>
    
   </div>
 </div>
</div>


<div class="row m-t-xs m-b-xs bg-white p" id="theaterDateMainDivId">
    
    <s:iterator value="theaterOwnerBean.date_list" status="status">
    
     <s:if test="%{theaterOwnerBean.date_list[#status.index].status == 'Success'}">
    
      <button type="button" class="d-m-l-30  btn btn-success  btn-sm m-r-xs active datsize dateSelectCls" id="<s:property value="theaterOwnerBean.master_movie_id"/>_<s:property value="date_id"/>"><span class="datesize"><s:property value="date"/></span><small><s:property value="dayName"/></small></button>
      </s:if>
      <s:else>
      <button type="button" class="btn btn-light btn-success m-r-xs btn-sm dateSelectCls" id="<s:property value="theaterOwnerBean.master_movie_id"/>_<s:property value="date_id"/>"><span class="datesize"><s:property value="date"/></span><small ><s:property value="dayName"/></small></button>
      </s:else>  
      
      <s:hidden name="date" id="floorDateValue"/>
       
    </s:iterator> 
</div>
<div class="ticpro">
  
  <s:iterator value="theaterOwnerBean.theaterDetailList">
  <div class="row ticket1 bg-white m-b-xl">
    <div class="col-md-2 ">
        <h6 class="theater"><a href="#" id="<s:property value="theater_id"/>" class="theaterCls"><s:property value="theater_name"/></a></h6>
        </div>
        
        <s:hidden name="theater_name" id="floortheatreName"/>
        
        <div class="col-md-10 col-sm-12">
      
   <ul class="list1">
     <s:iterator value="show_list">
        <li>
   <button  id="<s:property value="screen_id"/>_<s:property value="theater_id"/>_<s:property value="show_id"/>_<s:property value="movie_detail_id"/>_<s:property value="show_detail_id"/>_ <s:property value="show_timing"/>" type="button" class="btn btn-outline-danger btn-sm showTimingCls" data-toggle="modal" data-target="#myModal8">
    <s:property value="show_timing"/>
    
      </button>
       </li>
</s:iterator>
      </ul>
</div>
      </div>
</s:iterator>
    </div>
    
    
    <div class="modal inmodal" id="myModal8" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content animated flipInY">
                                        <div class="modal-header">
                                          
                                          	<div class="m-left">
                                           <span class="font-weight-bold">How Many Tickets?</span>
                                         </div>
                                     
                                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                            
                                           
                                        </div>
         <div class="modal-body">
                                          
            <div class="text-center m-t-lg">
                <img src="images/rsz_cycle.png" class="1 imgsize">
                   <img src="images/rsz_bike.png" class="2 imgsize"> 
                   <img src="images/rsz_auto.png" class="3 imgsize">
                   <img src="images/rsz_minicar.png" class="4 imgsize">
                   <img src="images/rsz_car3.png" class="5 imgsize">
                   <img src="images/rsz_van2.png" class="6 imgsize">
                   <img src="images/rsz_bus3.png" class="7 imgsize">
            </div>          
            <div class="row m-t-md">
                                            <ul class="list2">
                                              <li><button type="button" id="1" class="btn btn-outline-primary btn1 seatCountCls">1</button></li>
                                              <li><button type="button" id="2" class="btn btn-outline-primary btn2 seatCountCls">2</button></li>
                                              <li><button type="button" id="3" class="btn btn-outline-primary btn3 seatCountCls">3</button></li>
                                              <li><button type="button" id="4" class="btn btn-outline-primary btn4 seatCountCls">4</button></li>
                                              <li><button type="button" id="5" class="btn btn-outline-primary btn5 seatCountCls">5</button></li>
                                              <li><button type="button" id="6" class="btn btn-outline-primary btn6 seatCountCls">6</button></li>
                                              <li><button type="button" id="7" class="btn btn-outline-primary btn7 seatCountCls">7</button></li>
                                              <li><button type="button" id="8" class="btn btn-outline-primary btn8 seatCountCls">8</button></li>
                                              <li><button type="button" id="9" class="btn btn-outline-primary btn9 seatCountCls">9</button></li>
                                              <li><button type="button" id="10" class="btn btn-outline-primary btn10 seatCountCls">10</button></li>
                                            </ul>
                                        </div>
                                    </div>
                                        <%-- <div class="row m-t-sm">
                                          <div class="col-md-3">
                                            
                                          </div>
                                         <div class="col-md-3">
                                        <div>
                                                  <small>ELITE_2D</small><br>
                                              <span><i class="fa fa-inr" aria-hidden="true"></i></span><span class=" font-weight-bold">165.78</span><br>
                                              <span><small>Available</small></span>
                                            </div>
                                          </div>

                                         
                                        <div class="col-md-3">
                                             <small>BUDGET</small><br>
                                             <span><i class="fa fa-inr" aria-hidden="true"></i></span><span class="p font-weight-bold">63.62</span><br>
                                               <small>Available</small>
                                           </div>
                                           <div class="col-md-3">
                                             
                                           </div>
                                         </div> --%>
                                         <div class="row justify-content-center m-t-md m-bot">
                                            <div class="m-b-md">
                                          <button type="button" id="seatSubmitBtnId" class="btn btn-primary btn-md m-b-md">Select Seats</button>
                                        </div>
                                        </div>
                                    </div>
                                        </div>
                                    </div>
                                    
                                    
  <script type="text/javascript">
  $(document).ready(function(){
  $('ul li button').click(function(){
  $('li button').removeClass("active");
  $(this).addClass("active");

});
});
</script>
</body>
</html>