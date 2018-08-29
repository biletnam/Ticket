<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="/struts-tags" prefix="s" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="Dynamic/css/plugins/footable/footable.core.css" rel="stylesheet">
<style type="text/css">
  .footable > thead > tr > th > span.footable-sort-indicator {
    display: none;
    }

    .back-color {
    background-color: #1ab394;
    color: white;
    font-weight: bold;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	   $('.movieDetailCls').addClass("active"); 
	   $('.viewMovieCls').addClass("active"); 
	
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
	
});

</script>
</head>
<body>
<div class="row wrapper border-bottom white-bg m-b-md">
                <div class="col-lg-10 col-sm-10">
                    <ol class="breadcrumb m-sm ">
                        <li>
                            <a href="#">Home</a>
                        </li>
                        <li class="active">
                            <strong>View Movie</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2 col-sm-2">
                </div>
            </div>
       
       <div class=" row ">
            <div class="ibox-title">
                <h2 class=""><b>Movie Details</b></h2>
            </div>

                <!-- hover table -->

             <div class="ibox-content col-sm-12 col-lg-12">
                           <div class="table-responsive">
                            <table class="table table-stripped table-hover footable" data-page-size="8" style="overflow-x: scroll;text-overflow: ellipsis;">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Movie Name</th>
                                    <th>Movie Poster</th>
                                   <!--<th>Movie Id</th> -->
                                    <th>Language</th>
                                    <th>Genre</th>
                                    <th>Format</th>
                                    <th>Certification</th>
                                    <th>Movie Duration</th>
                                    <th>Movie Release Date</th>
                                    <th></th>
                                    <th></th>
                                
                                </tr>
                                </thead>
                                <tbody>
                            <s:iterator value="movieList" status="status"> 
                                
                                <tr>
                                  <td><s:property value="%{#status.count}" /></td>
                                  <td><b><s:property value="movie_name"/></b></td>
                                  <td class="text-center">
                                  <img id="imgPhoto" src="getMoviePoster.action?modelId=<s:property value="movie_details_id" />"  alt="Movie" class="img-responsive" style="width: 88px;">
                                   </td>
                                  <%--  <td><s:property value="movie_id"/></td> --%>
                                   <td><s:property value="movie_languge_name"/></td>
                                   <td><s:property value="movie_genre_name"/></td>
                                   <td><s:property value="movie_format_name"/></td>
                                   <td><s:property value="movie_certification_name"/></td>
                                   <td><s:property value="movie_duration"/> hrs</td>
                                   <td><s:property value="movie_release_date"/></td>
                                   <td>
                                    <i class="fa fa-pencil small editMovieClass"  id="<s:property value="movie_details_id"/>"> </i>
                                    </td>
                                    <td>
                                    <i class="fa fa-trash text-danger m-l-xs deleteMovieClass" id="<s:property value="movie_details_id" />_<s:property value="movie_name" />"> </i>
                                    </td>
                                </tr>
                    </s:iterator>
                                </tbody>
                                   <tfoot>
                                <tr>
                                    <td colspan="11">
                                        <ul class="pagination pull-right"></ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
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
              <h3 class="font-bold text-danger text-center">Are you sure you want to delete this Movie Permanently ?</h3>
               </div>
            <div class="modal-footer">
               <button type="submit" class="btn btn-primary" id="deleteMoviePopupBtnId">Yes</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">No</button> 
            </div>
            </div>
          </div>
        </div>
   </div> 
   
  
     <!-- FooTable -->
    <script src="Dynamic/js/plugins/footable/footable.all.min.js"></script>
    <script src="Dynamic/js/plugins/staps/jquery.steps.min.js"></script>
    
   <script>
        $(document).ready(function() {

            $('.footable').footable();
            $('.footable2').footable();

        });

    </script>
</body>
</html>