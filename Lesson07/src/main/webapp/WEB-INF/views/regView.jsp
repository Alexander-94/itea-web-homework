<%@ page isELIgnored="false"%>
<%@include file="/source/includes/menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<div class="divError">
		<c:choose>
			<c:when test="${not empty errorText}">
				<ul>
					<c:forEach items="${errorText}" var="errStr">
						<li><c:out value="${errStr}" /></li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</div>


	<form id="registrationForm" action="./registration" method="post">
		<div class="field">
			<label>Enter your login:</label>
			<div class="input">
				<input type="text" name="login" value="${login}" />
			</div>
		</div>

		<div class="field">
			<label>Enter your password:</label>
			<div class="input">
				<input type="password" name="password" />
			</div>
		</div>

		<div class="field">
			<label>Reenter your password:</label>
			<div class="input">
				<input type="password" name="rePassword" />
			</div>
		</div>

		<div class="field">
			<label>Enter your name:</label>
			<div class="input">
				<input type="text" name="name" />
			</div>
		</div>

		<div>
			<label>Choose your gender:&nbsp;</label> <label>M</label><input
				type="radio" name="gender" value="male" ${genderMaleState}>
			<label>F</label><input type="radio" name="gender" value="female"
				${genderFemaleState}>
		</div>
		<br>

		<div class="field">
			<label>Enter your address:</label>
			<div class="input">
				<select name="address">
					<option value="lnr" ${addressLnrState}>LNR</option>
					<option value="dnr" ${addressDnrState}>DNR</option>
					<option value="crimea" ${addressCrimeaState}>Crimea</option>
				</select>
			</div>
		</div>

		<div class="field">
			<label>Enter your comment:</label>
			<div class="input">
				<textarea name="comment" cols="15" rows="10"></textarea>
			</div>
		</div>

		<div>
			<label>I agree:</label> <input type="checkbox" name="agree"
				style="vertical-align: middle;">
		</div>

		<div class="submit">
			<button type="submit" value="send">Enter</button>
		</div>
	</form>
</body>
</html>