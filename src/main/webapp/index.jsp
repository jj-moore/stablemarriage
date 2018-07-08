<!--
Name: Jeremy Moore and Alec Maly
Net ID: jmoore28 and amaly1
COSC 311 Course Project
-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="pro.jmoore.stablemarriage.*"%>
<%
    Driver driver = (Driver) session.getAttribute("drivers");
    if (driver == null) {
        driver = new Driver(5);
        session.setAttribute("drivers", driver);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="normalize.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>COSC 311 Project</title>
    </head>
    <body>
        <header>
            <h1>GALE-SHAPLEY ALGORITHM</h1>
            <h3>Alec Maly and Jeremy Moore</h3>
        </header>
        <div id="body">
            <div id="main">
                <div id="display">
                    <div class="men">
                        <c:forEach var="man" items="${drivers.men}">
                            <c:choose>
                                <c:when test="${man.partner == null}">
                                    <p class="name">${man}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="blank">&nbsp;</p>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>

                    <div class="matches">
                        <c:forEach var="man" items="${drivers.men}">
                            <c:choose>
                                <c:when test="${man.partner != null}">
                                    <p class="matches_name">${man} &amp; ${man.partner}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="blank">&nbsp;</p>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>

                    <div class="women">
                        <c:forEach var="woman" items="${drivers.women}">
                            <c:choose>
                                <c:when test="${woman.partner == null}">
                                    <p class="name">${woman}</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="blank">&nbsp;</p>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
                <p id="lastMessage">${drivers.lastMessage}</p>
                <div class="buttons">
                    <form action="Servlet" method="post">
                        <button class="button" name="action" type="submit" value="step" id="step">Step</button>
                        <button class="button" name="action" type="submit" value="reset" id="reset">Reset</button>
                    </form>
                    <script src="main.js" type="text/javascript" charset="utf-8"></script>
                </div>
                <div id="preferences">
                    <div id="men_prefs">
                        <table><tr>
                                <c:forEach var="man" items="${drivers.men}">
                                    <th class="prefs">${man}</th>
                                        <c:forEach var="preference" items="${man.preferences}">
                                            <c:choose>
                                                <c:when test="${preference == man.partner}">
                                                <td class="partner">${preference}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="prefs">${preference}</td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div id="women_prefs">
                        <table><tr>
                                <c:forEach var="woman" items="${drivers.women}">
                                    <th class="prefs">${woman}</th>
                                        <c:forEach var="preference" items="${woman.preferences}">
                                            <c:choose>
                                                <c:when test="${preference == woman.partner}">
                                                <td class="partner">${preference}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="prefs">${preference}</td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            <div id="messages"><p>${drivers.message}</p></div>
        </div>
    </body>
</html>
