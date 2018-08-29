<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">

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
<script type="text/javascript">
	$(document).ready(function()
	{
		$(document).on('click','.movieCls',function()
		{
			var movieId = $(this).attr('id');
			jQuery.ajax({
				url : "getLoginStatusDetail",
				type : "POST",
				success : function(results) 
				{
					var page = JSON.parse(results);
					
					if(page['status'] == "Logged In")
					{
						 $("#formHiddenMvId").val(movieId);
			             $("#movieFormSubmit").submit(); 
					}
					else if(page['status'] == "Logged Out")
					{
						$('#loginBtnId').click();
					}	
				}
					
			});
			
			
		
		});
		
	});
</script>

</head>
<body class="fx-greycolor">

<s:form action="movie-cinemas" id="movieFormSubmit">
<s:hidden name="theaterOwnerBean.master_movie_id" id="formHiddenMvId"></s:hidden>
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

  <!--/.Carousel Wrapper-->
    <div class="container">
     <div class="row m-top">
    <div class="col-md-12 col-sm-12 col-xs-12 col-lg-12 m-bot section-header">
      <div class="section-title font ">Now Running</div><span class="divider"></span>
    </div>
  </div>
 <div class="row">
 
    <s:iterator value="theaterOwnerBean.movieList">
 
   <div class="col-md-3 col-sm-3 col-xs-3 text-center">
       <div class="b-size">
   <img src="getMasterMoviePoster.action?modelId=<s:property value="master_movie_id" />"  alt="Movie" class="image img-responsive">
          <div class="overlay">
             <div class="text"><a id="<s:property value="master_movie_id" />" class="movieCls"><i class="fa fa-ticket f-size" aria-hidden="true"></i></a></div>
          </div>
        </div>
      <div class="m-t-xs m-b-xl">
         
         <%--  <span><i class="fa fa-star fs" aria-hidden="true"></i><span class="rc">&nbsp;
          77%</span></span>  --%>
          <div class=" text-center text-dark font-weight-bold text-truncate"><s:property value="movie_name"/></div>
           <span class="mc"><s:property value="movie_certification_name"/></span>  
          <span class="fx-marginleft"><b><s:property value="movie_language"/> | <s:property value="movie_genre"/></b></span> 
         
      </div>

   </div>
   
   </s:iterator>
   
 </div>
</div>
</div>
  
  <a href="#" class="back-to-top bt"><i class="fa fa-chevron-up"></i></a>

</body>
</html>