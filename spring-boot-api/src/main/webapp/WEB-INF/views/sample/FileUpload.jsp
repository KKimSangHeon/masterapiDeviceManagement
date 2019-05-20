<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script language='javascript'>
	function sdkUpload(f) {
		//alert(f.sdkSeq.value);
		f.action = "/masterapi/sdk/100/c/" + f.sdkSeq.value;
		f.submit();
		return true;
	}
</script>
</head>
<body>
	<form enctype="multipart/form-data" method="post"
		onsubmit="sdkUpload(this);"returnfalse">
		<p>
			<label>Select a file: </label> <input type="file" name="file"
				id="selFile" /><br> sdkSeq: <input type="TEXT" name="sdkSeq" /><br>
			verNo: <input type="TEXT" name="verNo" /><br> cretrId: <input
				type="TEXT" name="cretrId" /><br> <SELECT NAME=atcTypeCd
				SIZE=1>
				<OPTION VALUE=0000001 SELECTED>라이브러리</OPTION>
				<OPTION VALUE=0000002>문서</OPTION>
			</SELECT>
		</p>
		<input type="submit" value="Upload">
	</form>

</body>
</html>