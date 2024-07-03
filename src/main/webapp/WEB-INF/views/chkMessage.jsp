<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<script src= "/js/open/jquery-3.6.0.min.js"> </script>
<script type="javascript">
    function changeLanguage(){
        $.ajax({
            url : "/changeLanguage",
            type : "post",
            data : {
                        lang : $("select[name=lang]").val(),
                        currentLoc : location.href
            },
            success: function(data) {
                console.log("언어 변환");
            },
            error: function() {
                alert("요청 처리 중 오류가 발생하였습니다! \n관리자에게 문의하세요!");
            }
        });
    }

</script>
<body>

<label>
    <select name="lang" onchange="changeLanguage();">
        <option value="ko">한국어</option>
        <option value="en">English</option>
    </select>
</label>

<div>
        <spring:message code="hello" text=""/>
        <spring:message code="hello.title" text=""/>
    </div>


</body>
</html>