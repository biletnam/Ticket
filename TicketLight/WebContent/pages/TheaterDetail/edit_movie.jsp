<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie Insert</title>
<link href="css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
<link href="Dynamic/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<script src="Dynamic/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/validationEngine.jquery.css"/>
<script src="js/jquery.validationEngine-en.js"></script>
<script src="js/jquery.validationEngine.js"></script> 

<style type="text/css">
    /*overlay*/
.b-size {
  position: relative;
  width: 100%;
}

.image {
  display: block;
  width: 100%;
  height: auto;
  border-radius: 4px;
}

.overlay {
  position: absolute;
  bottom: 0;
  left: 0px;
  right: 0;
  background-color: #08080885;
  overflow: hidden;
  width: 100%;
  height: 100%;
  -webkit-transform: scale(0);
  -ms-transform: scale(0);
  transform: scale(0);
  -webkit-transition: .3s ease;
  transition: .3s ease;
}

.b-size:hover .overlay {
   -webkit-transform: scale(1);
  -ms-transform: scale(1);
  transform: scale(1);
}

.text {
  color: white;
  font-size: 20px;
  position: absolute;
  top: 50%;
  left: 43%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  text-align: center;
}
.f-size{
 font-size: 30px;
   
    color: #f5f1ef;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	
	 $('.movieDetailCls').addClass("active"); 
	 $('.addMovieCls').addClass("active");  
		
	 $("#updateMovieDetailFormId").validationEngine(); 
	 
	$(document).on('click','.editMovieClass',function(){
		var movieId=$(this).attr('id');
		$("#hidMovieId").val(movieId);
		
		$("#editMovieFormId").submit();
		
	});
	
	/* deleteMovieClass */
	$(document).on('click','.deleteMovieClass',function(){
		var movie=$(this).attr('id');
		var id = movie.split('_');
	    var movieId = id[0];
	   	var movieName = id[1];
	   	$("#popUpMovieNameId").empty();
	    $("#popUpMovieNameId").append(movieName);
		$("#hidMovieId1").val(movieId);
	   	$("#popUpId").modal({modal: true});    
		
		
	});
	
    $(document).on('click',"#deleteMoviePopupBtnId",function(){
		$("#deleteMovieFormId").submit();

   });
	
	 $(".dateSelect").datepicker({
    	 todayBtn: "linked",
         autoclose: true,
         format: "yyyy-mm-dd",
         startDate: new Date()
     });
	 
	  $(document).on('change',"#changePosterId",function(){
          
 		 var filename = $(this).val();
 		 
		  		var validextensions = /(\.jpg|\.jpeg|\.gif|\.png)$/i;
		  		if (validextensions.test(filename)) {
		  			var sizeoffile = $('#changePosterId')[0].files[0].size;
		  			if (sizeoffile > 1000000) {
		  			    $('#spanErrorId').html("Selected file size too large maximum size 1MB.");
		  				$('#submitBtnId').attr('disabled', true);
		  				
		  			} else {
		  			    $('#spanErrorId').html("");
		  				$('#submitBtnId').attr('disabled',false);
		  				
		  			}
		  		} else {
		  		    $('#spanErrorId').html("Please select valid file (.jpeg|.png|.jpg) format");
		  			$('#submitBtnId').attr('disabled', true);
		  			
		  		}
      });
});
</script>

</head>
<body>

<div class="row wrapper border-bottom white-bg ">
            <div class="col-lg-10">
                
                <ol class="breadcrumb m-t-xs m-b-xs">
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li>
                        <strong>Edit Movies</strong>
                    </li>
                    
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
        </div>
        <div class="wrapper wrapper-content ">
       <div class="row">
                <div class="col-md-8">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h2 class="font-bold">Edit Movie</h2>
                        </div>                           
                         <s:form action="editMovieDetailsSubmit" id="updateMovieDetailFormId" method="post" cssClass="form-horizontal" enctype="multipart/form-data" theme="simple">
                        <div class="ibox-content">
                             
                               <div class="form-group">
                      <s:hidden name="theaterOwnerBean.movie_details_id"/>
                      
                    <label  class="col-sm-3  control-label">
                        Movie Name :
                    </label>
                    <div class="col-sm-6 ">
                    
                         <s:select list="movieIdMap"  name="theaterOwnerBean.master_movie_id" id="movieNameId"  cssClass="form-control validate[required]" headerKey="" headerValue="Select Movie Name"/>
                        <%-- <s:textfield cssClass="form-control validate[required]"  name="theaterOwnerBean.movie_name"/> --%>
                    </div>
                </div>


                 <div class="form-group">
                      <label  class="col-sm-3 control-label">Language :</label>
                         <div class="col-sm-6">
                         <s:select list="movieLangMap"  name="theaterOwnerBean.movie_language_id"  cssClass="form-control validate[required]" headerKey="" headerValue="Select Language"/>
                           
                         </div> 
                  </div>

                   <div class="form-group">
                      <label class="col-sm-3 control-label">Genre :</label>
                            <div class="col-sm-6">
                            <s:select list="movieGenreMap" name="theaterOwnerBean.movie_genre_id"  cssClass=" form-control validate[required]" headerKey="" headerValue="Select Genre"/>
                             
                            </div>

                         
                  </div>

                   <div class="form-group">
                      <label class="col-sm-3 control-label" >Format :</label>
                          <div class="col-sm-6">
                           <s:select list="movieFormatMap" name="theaterOwnerBean.movie_format_id"  cssClass=" form-control validate[required]" headerKey="" headerValue="Select Format"/>
                          </div>
                  </div>

                 <div class="form-group">
                      <label class="col-sm-3 control-label" >Certification :</label>
                          <div class="col-sm-6">
                           <s:select list="movieCertificationMap" name="theaterOwnerBean.movie_certification_id" cssClass="form-control validate[required]" headerKey="" headerValue="Select Movie Certification"/>
                          </div>
                  </div>
                  
                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                       Movie Duration :
                    </label>
                
                    <div class="col-sm-6 clockpicker"  data-autoclose="true">
                            <s:textfield cssClass="form-control validate[required]"  name="theaterOwnerBean.movie_duration" headerKey="" headerValue="Select Movie Duration"/>
                    </div>
                    
                </div>
                
                  <div class="form-group">
                    <label  class="col-sm-3  control-label">
                       Movie Release Date :
                    </label>
                     <div class="col-sm-6">
		        
                      <div class="input-group date dateSelect">
                      <span class="input-group-addon"><i class="fa fa-calendar"></i></span><s:textfield name="theaterOwnerBean.movie_release_date" id="movieReleaseDateId" cssClass="form-control validate[required]" placeholder="Movie Release Date"/>
                     </div>
	             </div>
                   </div>

                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                      <i class="fa fa-question-circle" style="cursor:pointer" title="Upload .png, .jpg and .jpeg files only"></i> Movie Poster :
                    </label>
                
                         <div class="col-sm-6">
                       <img  id="imgPhoto" src="getMoviePoster.action?modelId=<s:property value="theaterOwnerBean.movie_details_id" />" alt="Movie" class="img-responsive">
                           <s:file cssClass="form-control"  id="changePosterId" name="theaterOwnerBean.movie_poster"/>&nbsp;<span id="spanErrorId" style="color: red;">
                    <%--    <span id="fileDivId" style="color:red">* Upload .png, .jpg and .jpeg files only</span> --%>
                      </div>
               
                          <%-- <small class="text-danger"> You can add Multiple Posters *</small> --%>
                    </div>
                    
             
                <div class="form-group">
                <div class="col-sm-3 col-sm-offset-7">
                <button type="submit" id="submitBtnId" class="btn btn-primary m-l-md">Update</button>
                    </div>
                </div>
                </div>
                           </s:form>
                        </div>
                    </div>
                
                    <div class="col-md-4">
                        <div class="ibox float-e-margins">
                           <div class="ibox-content">
                               <div class="row">
                                 <s:iterator value="movieList"> 
                                   <div class="col-sm-6">
                                          <div class="b-size">
                                            <img  id="imgPhoto" src="getMoviePoster.action?modelId=<s:property value="movie_details_id" />"  alt="Movie" class="image">
                                             <div class="overlay" >
                                                 <div class=" p-md text-center">
                                                    <a><i class="fa fa-pencil m-r-sm f-size editMovieClass" aria-hidden="true" id="<s:property value="movie_details_id" />"></i>
                                                    </a>
                                                    <a href="#"><i class="fa fa-trash-o f-size deleteMovieClass" aria-hidden="true" id="<s:property value="movie_details_id" />_<s:property value="movie_name" />"></i>
                                                    </a>
                                                 </div>
                                              </div>
                                          </div>
                                          <div class="col-sm-12">
                                           
                                           <div class="text-center">
                                           <span class="font-bold "><s:property value="movie_name" /></span>
                                          <%--  <small class="font-bold "><s:property value="movie_id" /></small> --%>
                                         </div>
                                    </div>
                                   </div>
                                   </s:iterator> 
                                   
                              
                           </div> 
                        </div>  
                    </div>
                </div>
            </div>
</div>


<div class="modal fade revise-page-modal" id="popUpId" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header revise-page-model-header">
               
              <button type="button" class="close close1" data-dismiss="modal" aria-hidden="true">
              </button>
              <h3 class="modal-title" id="myModalLabel">
              <span id="popUpMovieNameId"></span>
              </h3>
            </div>
            <div>
            <div class="modal-body">
              <h3 class="font-bold text-danger text-center">Are you sure you want to delete this Movie Permanently?</h3>
               </div>
            <div class="modal-footer">
               <button type="submit" class="btn btn-primary" id="deleteMoviePopupBtnId">Yes</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">No</button> 
            </div>
            </div>
          </div>
        </div>
   </div> 
   
         
   <!--  editMovieFormId -->
     <s:form action="edit-movie-details" id="editMovieFormId">
     <s:hidden id="hidMovieId" name="theaterOwnerBean.movie_details_id"/>
  </s:form>
    <!-- deleteMovieFormId -->
    <s:form action="delete-movie-details" id="deleteMovieFormId">
     <s:hidden id="hidMovieId1" name="theaterOwnerBean.movie_details_id"/>
  </s:form>
    <script type="text/javascript">
    $(document).ready(function(){
      $('.clockpicker').clockpicker();
    });
</script>


 <!-- Clock picker -->
    <script src="js/plugins/clockpicker.js"></script>

</body>
</html>