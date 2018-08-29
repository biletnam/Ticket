<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Counter Movie</title>
<style type="text/css">
        /*hover effect*/
    .hovereffect {
  width: 100%;
  height: 100%;
  float: left;
  overflow: hidden;
  position: relative;
  text-align: center;
  cursor: default;
}

.hovereffect .overlay {
  width: 100%;
  height: 100%;
  position: absolute;
  overflow: hidden;
  top: 0;
  left: 0;
  background-color: rgba(0,0,0,0.6);
  opacity: 0;
  filter: alpha(opacity=0);
  -webkit-transform: translate(460px, -100px) rotate(180deg);
  -ms-transform: translate(460px, -100px) rotate(180deg);
  transform: translate(460px, -100px) rotate(180deg);
  -webkit-transition: all 0.2s 0.4s ease-in-out;
  transition: all 0.2s 0.4s ease-in-out;
}

.hovereffect img {
  display: block;
  position: relative;
  -webkit-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
}

.hovereffect h2 {
  text-transform: uppercase;
  color: #fff;
  text-align: center;
  position: relative;
  font-size: 17px;
  padding: 10px;
  background: rgba(0, 0, 0, 0.6);
}

.hovereffect a.info {
  display: inline-block;
  text-decoration: none;
  padding: 7px 14px;
  text-transform: uppercase;
  color: #fff;
  border: 1px solid #fff;
  margin: 150px 0 0 0;
  background-color: transparent;
  -webkit-transform: translateY(-200px);
  -ms-transform: translateY(-200px);
  transform: translateY(-200px);
  -webkit-transition: all 0.2s ease-in-out;
  transition: all 0.2s ease-in-out;
}

.hovereffect a.info:hover {
  box-shadow: 0 0 5px #fff;
}

.hovereffect:hover .overlay {
  opacity: 1;
  filter: alpha(opacity=100);
  -webkit-transition-delay: 0s;
  transition-delay: 0s;
  -webkit-transform: translate(0px, 0px);
  -ms-transform: translate(0px, 0px);
  transform: translate(0px, 0px);
}

.hovereffect:hover h2 {
  -webkit-transform: translateY(0px);
  -ms-transform: translateY(0px);
  transform: translateY(0px);
  -webkit-transition-delay: 0.5s;
  transition-delay: 0.5s;
}

.hovereffect:hover a.info {
  -webkit-transform: translateY(0px);
  -ms-transform: translateY(0px);
  transform: translateY(0px);
  -webkit-transition-delay: 0.3s;
  transition-delay: 0.3s;
}
.mc{
      color: #fff;
    padding: 2px;
    background: #0b556b;
    border-radius: 17px;
    top: -1px;
    left:0px;
    font-size: 10px;
    position: relative;
}
.m-l{
 margin-left: 3px;
    font-size: 13px;
   color: #271f1f;
    white-space: nowrap!important; 
    width: 90%!important; 
    overflow: hidden!important;
    text-overflow: ellipsis!important; 
}
.f-s{
        font-size: 18px;
          color: #111;  
}

.fx-overflow{

  white-space: nowrap; 
    width: 100%; 
    overflow: hidden;
    text-overflow: ellipsis; 
}

.rc{
      color: green;
    padding-right: 8px;
}
.fs{
      font-size: 15px;
    padding: 3px;
        background: linear-gradient(45deg, #1de099, #1dc8cd);
    color: #fff;
}

    </style>
</head>
<body>
 <div class=" row wrapper wrapper-content">
  <s:iterator value="movieList"> 
         <div class="col-md-3 col-sm-6 col-xs-12">
  
        <div class="hovereffect">
         <img  id="imgPhoto" src="getCounterMoviePoster.action?modelId=<s:property value="movie_details_id" />"  alt="Movie" class="img-responsive">
          <!--   <img src="img/11.jpg" alt="movie" class="img-responsive" >  -->
              <div class="overlay">
                
                 <a class="info"  href="getCounterMovieDetails.action?modelId=<s:property value="movie_details_id"/>">Book</a>
              </div>
         </div>
          <div>
                  <p class="text-center"> <b class="f-s fx-overflow" ><s:property value="movie_name"/></b></p>
                 <p class="text-center m-l">
                 <span class="mc"><s:property value="movie_certification_name"/></span> 
                 <span><b><s:property value="movie_languge_name"/></b></span>  
                <%--  <s:property value="movie_release_date"/>
                 <s:property value="movie_duration"/> --%>
                  <span><b><s:property value="movie_genre_name"/></b></span> 
                 </p>
            </div>
           
         </div>
           </s:iterator> 
         
     </div>
     

</body>
</html>