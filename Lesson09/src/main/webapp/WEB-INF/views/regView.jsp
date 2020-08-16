<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp"%>
<body>
	<c:choose>
		<c:when test="${user==null}">
			<div>
				<div class="divError">
					<ul>
					<c:forEach items="${errorText}" var="errStr">
						<li><c:out value="${errStr}" /></li>
					</c:forEach>
					</ul>
				</div>
				<div class="divForm">
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
							<label>Choose your gender:&nbsp;</label>
							<label>M</label>
							<input type="radio" name="gender" value="male" ${genderMaleState}>
							<label>F</label>
							<input type="radio" name="gender" value="female" ${genderFemaleState}>
						</div>
						<br />

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
							<label>I agree:</label>
							<input type="checkbox" name="agree"	style="vertical-align: middle;">
						</div>

						<div class="submit">
							<button type="submit" value="send">Enter</button>
						</div>
					</form>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="regSuccessText">
			  User: ${user.name}! Registration successfull!
			</div>
			<br />
		</c:otherwise>
	</c:choose>
</body>
</html>
<%@include file="/source/includes/footer.jsp"%>