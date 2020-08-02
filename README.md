# lesson05 - registration and authorization forms on HttpServlet

1) copy source and images dirs into tomcat's webapps\ROOT
2) copy target classes itea\web\lesson05\ Authorization.class and Registration.class into tomcat's webapps\ROOT\WEB-INF\classes
3) add following lines into webapps\ROOT\WEB-INF\web.xml:
  <servlet>
    <servlet-name>authorization</servlet-name>
    <servlet-class>itea.web.lesson05.Authorization</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>authorization</servlet-name>
    <url-pattern>/authorization</url-pattern>
  </servlet-mapping>

  <servlet>
    <servlet-name>registration</servlet-name>
    <servlet-class>itea.web.lesson05.Registration</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>registration</servlet-name>
    <url-pattern>/registration</url-pattern>
  </servlet-mapping>
  4) restart tomcat