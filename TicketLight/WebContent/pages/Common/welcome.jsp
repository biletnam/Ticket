<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
</head>

<style type="text/css">


.jR3DCarouselGallery,.jR3DCarouselGalleryCustomeTemplate {
	margin: 40px auto; /* optional - if want to center align */
}


.jR3DCarouselGalleryCustomeTemplate a{
	text-decoration: none;			
}
  }

</style>

<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="dist/jR3DCarousel.min.js"></script>
<!-- <script type="text/javascript" src="src/jR3DCarousel.js"></script> -->

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
<body class="fx-greycolor">

	<div class="container ">
		
		<div class="jR3DCarouselGallery now row">
		
		
		<div class="jR3DCarouselGalleryCustomeTemplate carous">
			<div class="jR3DCarouselCustomSlide">
				<img src="images/m4.jpg" class="img-responsive imag" />
				
			</div>
			<div class="jR3DCarouselCustomSlide">
					<img src="images/m2.jpg" class="img-responsive imag"/>			
			</div>

			<div class="jR3DCarouselCustomSlide">
				<img src="images/m3.jpg" class="img-responsive imag"/>
			
			</div>
			<div class="jR3DCarouselCustomSlide">
				<img src="images/m1.jpg" class="img-responsive imag"/>
	
			</div>
			<div class="jR3DCarouselCustomSlide">
				<img src="images/m5.jpg" class="img-responsive imag"/>

			</div>
      <div class="jR3DCarouselCustomSlide">
        <img src="images/m6.jpg" class="img-responsive imag"/>

      </div>
      <div class="jR3DCarouselCustomSlide">
        <img src="images/m7.jpg" class="img-responsive imag"/>

      </div>
      <div class="jR3DCarouselCustomSlide">
        <img src="images/m8.jpg" class="img-responsive imag"/>

      </div>
      <div class="jR3DCarouselCustomSlide">
        <img src="images/m9.jpg" class="img-responsive imag"/>

      </div>
       <div class="jR3DCarouselCustomSlide">
        <img src="images/m10.jpg" class="img-responsive imag"/>

      </div>
      <div class="jR3DCarouselCustomSlide">
        <img src="images/m11.jpg" class="img-responsive imag"/>

      </div>


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
   <div class="col-md-3 col-sm-3 col-xs-3 text-center">
       <div class="b-size">
          <img src="images/11.jpg" alt="Pyaar prema kaadhal" class="image img-responsive">
          <div class="overlay">
             <div class="text"><a href="#"><i class="fa fa-ticket f-size" aria-hidden="true"></i></a></div>
          </div>
        </div>
      <div>
          <p><b class="f-s text-center">Pyaar prema kaadhal</b><br>
           <span class="mc">U/A</span>  
          <span class="fx-marginleft"><b> Tamil | love Comedy</b></span> 
         </p>
      </div>

   </div>
      <div class="col-md-3 col-sm-3 col-xs-3 text-center">
       <div class="b-size">
          <img src="images/12.jpg" alt="Viswaroopam 2" class="image img-responsive">
          <div class="overlay">
             <div class="text"><a href="#"><i class="fa fa-ticket f-size" aria-hidden="true"></i></a></div>
          </div>
        </div>
      <div>
          <p><b class="f-s">Viswaroopam 2</b><br>
           <span class="mc">U/A</span>  
          <span class="fx-marginleft"><b> Tamil | action thriller</b></span> 
         </p>
      </div>

   </div>
    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
       <div class="b-size">
          <img src="images/13.jpg" alt="Satyameva Jayathe" class="image img-responsive">
          <div class="overlay">
            <div class="text"><a href="#"><i class="fa fa-ticket f-size" aria-hidden="true"></i></a></div>
          </div>
        </div>
       <div>
         <p><b class="f-s">Satyameva Jayathe</b><br>
          <span class="mc">U/A</span>
         <span class="fx-marginleft"><b> Hindi | action thriller</b></span>  
         </p>
      </div>

   </div>
   <div class="col-md-3 col-sm-3 col-xs-3 text-center">
       <div class="b-size">
          <img src="images/14.jpg" alt="Kolamaavu kokila" class="image img-responsive">
          <div class="overlay">
            <div class="text"><a href="#"><i class="fa fa-ticket f-size" aria-hidden="true"></i></a></div>
          </div>
       </div>
         <div>
           <p><b class="f-s">Kolamaavu kokila</b><br>
            <span class="mc">U/A</span>
         <span class="fx-marginleft"><b>Tamil | comedy thriller</b></span>  
         </p>
      </div>
   </div>
 </div>
 <div class="row">
    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
        <div class="b-size">
          <img src="images/15.jpg" alt="NUN" class="image img-responsive">
          <div class="overlay">
             <div class="text"><a href="#"><i class="fa fa-ticket f-size" aria-hidden="true"></i></a></div>
          </div>
        </div>
         <div>
          <p><b class="f-s">The NUN</b><br>
            <span class="mc">U/A</span>
         <span class="fx-marginleft"><b>English | horror thriller</b></span>  
         </p>
      </div>
   </div>
    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
        <div class="b-size">
          <img src="images/16.jpg" alt="Gold" class="image img-responsive">
          <div class="overlay">
             <div class="text"><a href="#"><i class="fa fa-ticket f-size" aria-hidden="true"></i></a></div>
          </div>
        </div>
         <div>
          <p><b class="f-s">Gold</b><br>
            <span class="mc">U/A</span>
         <span class="fx-marginleft"><b>Hindi | action thriller</b></span>  
         </p>
      </div>
   </div>
    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
        <div class="b-size">
          <img src="images/17.jpg" alt="Geetha Govindam" class="image img-responsive">
          <div class="overlay">
             <div class="text"><a href="#"><i class="fa fa-ticket f-size" aria-hidden="true"></i></a></div>
          </div>
        </div>
         <div>
           <p><b class="f-s">Geetha Govindam</b><br>
            <span class="mc">U/A</span>
          <span class="fx-marginleft"><b>Telugu | love Comedy</b></span>  
         </p>
      </div>
    </div>

    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
        <div class="b-size">
          <img src="images/18.jpg" alt="Ashke" class="image img-responsive">
          <div class="overlay">
            <div class="text"><a href="#"><i class="fa fa-ticket f-size" aria-hidden="true"></i></a></div>
          </div>
        </div>
         <div>
           <p><b class="f-s">Goodachari</b><br>
            <span class="mc">U/A</span>
          <span class="fx-marginleft"><b>Telugu | love Comedy</b></span>  
         </p>
      </div>
   </div>
 </div>

<!-- Upcoming -->
 <div class="row m-top">
    <div class="col-md-12 m-bot section-header">
      <div class="section-title font ">Upcoming Movies</div><span class="divider"></span>
    </div>
  </div>
  <div class="row">
   <div class="col-md-3 col-sm-3 col-xs-3 text-center">
       <div class="b-size">
          <img src="images/u1.jpg" alt="2.0" class="image img-responsive">
          
        </div>
      <div>
          <p><b class="f-s">2.0</b><br>
           <span class="mc">U/A</span>  
          <span class="fx-marginleft"><b>Tamil | Thriller entertainment</b></span> 
         </p>
      </div>

   </div>
      <div class="col-md-3 col-sm-3 col-xs-3 text-center">
       <div class="b-size">
          <img src="images/u2.jpg" alt="The Stolen Princess" class="image img-responsive">
          
        </div>
      <div>
          <p><b class="f-s">The Stolen Princess</b><br>
           <span class="mc yellow-bg">U/A</span>  
          <span class="fx-marginleft"><b>English | love entertainment</b></span> 
         </p>
      </div>

   </div>
    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
       <div class="b-size">
          <img src="images/u3.jpg" alt="Yennai noki paayum thotta" class="image img-responsive">
         
        </div>
       <div>
         <p><b class="f-s">Yennai noki paayum thotta</b><br>
          <span class="mc">U/A</span>
         <span class="fx-marginleft"><b>Tamil | love entertainment</b></span>  
         </p>
      </div>

   </div>
   <div class="col-md-3 col-sm-3 col-xs-3 text-center">
       <div class="b-size">
          <img src="images/u4.jpg" alt="Aquaman" class="image img-responsive">
          
       </div>
         <div>
           <p><b class="f-s">Aquaman</b><br>
            <span class="mc">U/A</span>
         <span class="fx-marginleft"><b>English | action entertainment</b></span>  
         </p>
      </div>
   </div>
 </div>
 <div class="row">
    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
        <div class="b-size">
          <img src="images/u5.jpg" alt="Kolamaavu Kokila" class="image img-responsive">
         
        </div>
         <div>
          <p><b class="f-s">Venom</b><br>
            <span class="mc">U/A</span>
         <span class="fx-marginleft"><b>English | action thriller</b></span>  
         </p>
      </div>
   </div>
    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
        <div class="b-size">
          <img src="images/u6.jpg" alt="Alpha" class="image img-responsive">
         
        </div>
         <div>
          <p><b class="f-s">Vadachennai</b><br>
            <span class="mc">U/A</span>
         <span class="fx-marginleft"><b>Tamil | action thriller</b></span>  
         </p>
      </div>
   </div>
    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
        <div class="b-size">
          <img src="images/u7.jpg" alt="Imaika Nodigal" class="image img-responsive">
         
        </div>
         <div>
           <p><b class="f-s">Imaika Nodigal</b><br>
            <span class="mc">U/A</span>
          <span class="fx-marginleft"><b>Tamil | action Thriller</b></span>  
         </p>
      </div>
   </div>
    <div class="col-md-3 col-sm-3 col-xs-3 text-center">
        <div class="b-size">
          <img src="images/u8.jpg" alt="Yezhumin" class="image img-responsive">
          
        </div>
         <div>
           <p><b class="f-s">Yezhumin</b><br>
            <span class="mc">U/A</span>
          <span class="fx-marginleft"><b>Tamil | family entertainment</b></span>  
         </p>
      </div>
   </div>
 </div>
 </div>

</body>
</html>