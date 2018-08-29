<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
.pop-class {
    width: 70%;
    box-sizing: border-box;
    border: 1px solid #c22e5e;
    border-radius: 4px;
    font-size: 16px;
    background-color: white;
    background-position: 10px 10px; 
    background-repeat: no-repeat;
    padding: 4px 10px 4px 40px;
    -webkit-transition: width 0.4s ease-in-out;
    transition: width 0.4s ease-in-out;

}
.pop-header{

    height: 110px!important;
    
}

.fx-broad{
  width: 100%!important;
}
.fx-close{
      margin-top: -40px;
          margin-right: 10px;
    color: #ffffff;
}
.fx-close:hover{
  color: #fb0051;
}
.fx-select{
      top: -15px;
}
.fx-select1{
      top: 18px;
}
.menuhover:hover{
  background-color: #7b296f;
  color: white;
  padding: 5px 12px;
  border-radius: 3px;
}

@media (min-width: 1200px) { /*goldy*/
 .logowidth{
  width: 70%;
 }
}
@media (min-width: 992px) {
  .logowidth{
    width: 70%!important;
  }
}
@media (max-width: 992px) {
  .logowidth{
    width: 100%!important;
  }
}
@media (max-width: 767px) {
 .logowidth{
    width: 30%!important;
  }
  .loc-h{
    display: none;
  }
  .search-h{
    display: none;
      }
}

.jR3DCarouselGallery,.jR3DCarouselGalleryCustomeTemplate {
	margin: 40px auto; /* optional - if want to center align */
}


.jR3DCarouselGalleryCustomeTemplate a{
	text-decoration: none;			
}

  .fx-pophead{
margin-left: 240px!important;
  }
  .citypop{
    padding: 5px 12px;
  }

.active {
  background-color: #7b296f;
  color: white!important;
  padding: 5px 10px !important;
  border-radius: 3px;
}
  .fx-black{
    color: #131111;
  }
  .fx-fontsize{
    font-size: 13px;
  }
  .fx-greycolor {
    background-color: #fff !important;
}

</style>

<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="dist/jR3DCarousel.min.js"></script>

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
			  autoplay: false,
			  controls: true,			/* control buttons */
			  navigation: ''			/* circles | squares | '' */,
			  perspective:425,
			  rotationDirection: 'ltr',
			  onSlideShow: slideShownCallback
				  
		}

	function slideShownCallback($slide){
		console.log("Slide shown: ", $slide.find('img').attr('src'))
	}

	jR3DCarouselCustomeTemplate = $('.jR3DCarouselGalleryCustomeTemplate').jR3DCarousel(carouselCustomeTemplateProps);

  })

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
		
	});
</script>

</head>
<body >

<s:form action="theater-movies" id="theaterFormSubmitId">
<s:hidden name="theaterOwnerBean.theater_id" id="hiddenTheaterId"></s:hidden>
<s:hidden name="theaterOwnerBean.city_id"></s:hidden>
</s:form>  

  <div class="container ">
    
    <div class="jR3DCarouselGallery now row">
    
    
    <div class="jR3DCarouselGalleryCustomeTemplate carous">
    
         <s:iterator value="theaterOwnerBean.movie_detail_id_list">
    
      <div class="jR3DCarouselCustomSlide">
           <img src="getMasterMovieBgPoster.action?modelId=<s:property/>"  class="imag img-responsive">
        
      </div>
      </s:iterator> 
    </div>
    </div>
  </div>

  <!--Cinemas List-->
  <div class="bg-white  m-b-xl">
    

  <div class="container ">
    <div class="row" >
      <h3 class="color-concept">Theatre List</h3>
    </div>

      <div class="row " >
   <s:iterator value="theaterOwnerBean.theaterDetailList">
    <div class="col-md-4 ">
      <ul>
         <li>
           <a href="#" id="<s:property value="theater_id"/>" class="fx-black fx-fontsize theaterCls"><s:property value="theater_name"/></a>
         </li>
      </ul>
    </div>
    </s:iterator>
    
  </div> 

  </div>
  </div>
  
</div>
  
  <a href="#" class="back-to-top bt"><i class="fa fa-chevron-up"></i></a>

</body>
</html>