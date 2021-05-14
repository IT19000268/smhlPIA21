$(document).on("click", "#btnSave", function(event)
{ 
	
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
 
 
// Form validation-------------------
var status = validateResearchForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 

//if valid
var type = ($("#hidResearchIDSave").val() == "") ? "POST" : "PUT";

$.ajax(
		{ 
		 url : "ResearchAPI", 
		 type : type, 
		 data : $("#formResearch").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onResearchSaveComplete(response.responseText, status); 
		 } 
		});
});

function onResearchSaveComplete(response, status)
{ 
	
if (status == "success") 
 { 
	
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
	 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } 
 
 else if (resultSet.status.trim() == "error") 
 {  
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 }

else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } 

else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }

$("#hidResearchIDSave").val(""); 
$("#formResearch")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidResearchIDSave").val($(this).data("rid")); 
		 $("#userId").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#rName").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#rDesc").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#rStatus").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#rBudget").val($(this).closest("tr").find('td:eq(4)').text()); 
		});

$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "ResearchAPI", 
		 type : "DELETE", 
		 data : "rid=" + $(this).data("rid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
			 onResearchDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});

function onResearchDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}





