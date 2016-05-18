<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="fragments/header.jsp"></jsp:include>
</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Spring 4 MVC Ajax Hello World</a>
		</div>
	</div>
</nav>

<div class="container" style="min-height: 500px">

	<div class="starter-template">
		<h1>Search Form</h1>
		<br>

		<div id="feedback"></div>
		<c:set var="serial" value="0"></c:set>
		<c:set var="serial" value="${serial+1 }"></c:set>
		<form data-serial="${serial }" class="form-horizontal search-form" id="search-form${serial}">
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Username</label>
				<div class="col-sm-10">
					<input type=text class="form-control" id="username${serial}">
				</div>
			</div>
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email${serial}">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="bth-search"
						class="btn btn-primary btn-lg">Search</button>
				</div>
			</div>
		</form>

	</div>

</div>

<c:url var="ajaxTest" value="/ajax-test" scope="request" />
<script>
	jQuery(document).ready(function() {

		$(".search-form").submit(function() {
			var serial=$(this).attr("data-serial");
			// Disble the search button
			//enableSearchButton(false);

			// Prevent the form from submitting via the browser.
			event.preventDefault();

			searchViaAjax(serial);

		});

	});

	function searchViaAjax(serial) {

		var search = {}
		search["applierUid"] = $("#username"+serial).val();
		search["commentOfAdmin"] = $("#email"+serial).val();
		$.ajaxSetup({
			type : "POST",
			contentType : "application/json",
			url : "${ajaxTest}",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
		$ajax();

	}

	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
	}

	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		$('#feedback').html(json);
	}
</script>

</body>
</html>