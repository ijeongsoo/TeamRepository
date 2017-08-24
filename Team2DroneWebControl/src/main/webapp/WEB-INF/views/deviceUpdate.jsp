<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>JSP Page</title>

<script type="text/javascript">
	

	function fileChange(event) {
		if ($("#dattach")[0].files.length != 0) {

			$("#imgFileView")[0].src = URL
					.createObjectURL(event.target.files[0]);

		}
	}
</script>
</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h1 class="modal-title" id="myModalLabel">장비 정보 수정하기</h1>
	</div>

	<div class="modal-body">
		<form action="updateDevice" method="post"
			enctype="multipart/form-data">
			<div class="alert alert-warning" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">주의 :</span> 장비 정보를
				수정해 주세요
			</div>



			<input type="hidden" class="form-control" name="dmacaddr"
				value="${ drone.dmacaddr }" required />

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"> <span
						class="glyphicon glyphicon-user"></span>
					</span> <input type="text" class="form-control" placeholder="장비이름"
						name="dname" value="${drone.dname}" required />
				</div>
			</div>




			<div class="form-group">
				<div id="imgFileViewContainer" style="padding: 5px;" align="center">
					<img id="imgFileView" width="500px"
						src="file?msavedfilename=${drone.dsavedfilename}&mfiletype=${drone.dfilecontent}" />
				</div>
				<div class="input-group col-lg-12 ">
					<label for="dattach" id="dattachBtn"
						class="col-lg-12 btn btn-default"><span
						class="glyphicon glyphicon-camera"></span> 사진 변경하기</label><input
						type="file" id="dattach" style="visibility: hidden"
						onchange="fileChange(event)" name="dattach" />
				</div>
			</div>


			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"> <input type="radio"
						placeholder="장비종류" name="dtype" value="1" ${drone.dtype=='1'?'checked':''}>드론 <input
						style="margin-left: 30px" type="radio" placeholder="장비종류"
						name="dtype" value="2" ${drone.dtype=='2'?'checked':''}>자동차 <input
						style="margin-left: 30px" type="radio" placeholder="장비종류"
						name="dtype" value="3" ${drone.dtype=='3'?'checked':''}>비행기
					</span>
				</div>
			</div>






			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">주의 :</span> 위 내용으로
				수정하시겠습니까?
			</div>


			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="submit" class="btn btn-primary">수정하기</button>
			</div>
		</form>
	</div>
</body>
</html>