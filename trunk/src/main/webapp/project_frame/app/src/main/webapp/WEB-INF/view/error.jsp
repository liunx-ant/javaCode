<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.*"%>
<%
    response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
    <head>
        <title>错误页面</title>
        <script type="text/javascript" src="./javascript/jquery-1.5.1.min.js"></script>
        <script>
            function showErrorMessage(){
                $("#errorMessageDiv").toggle();
            }
            $(document).ready(showErrorMessage);
        </script>
    </head>
    <body>
        <table width="100%">
            <tr>
                <td style="border-bottom:dotted 1px Gray;" colspan="2" >
                    <img src="images/error_title_icon.gif" id="img1" />&nbsp;&nbsp;错误提示                               
                </td><td></td>
            </tr>
            <tr>
                <td style="width: 130px" >
                    <img src="images/sorry.gif" id="error_img" />
                </td>
                <td>尊敬的用户：<br />系统出现了异常，请重试。
                    <br />如果问题重复出现，请向系统管理员反馈。<br /><br />
                    <a id="showErrorMessageButton" href="javascript:showErrorMessage();">详细错误信息</a>
                </td>
            </tr>
        </table>
        <div id="errorMessageDiv" >
            <pre>
                <%
                    try {
                        //全部内容先写到内存，然后分别从两个输出流再输出到页面和文件
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        PrintStream printStream = new PrintStream(byteArrayOutputStream);

                        printStream.println();
                        printStream.println("用户信息");
                        printStream.println("账号：" + request.getSession().getAttribute("userName"));
                        printStream.println("访问的路径: " + request.getAttribute("javax.servlet.forward.request_uri"));
                        printStream.println();

                        printStream.println("异常信息");
                        printStream.println(exception.getClass() + " : " + exception.getMessage());
                        printStream.println();

                        Enumeration<String> e = request.getParameterNames();
                        if (e.hasMoreElements()) {
                            printStream.println("请求中的Parameter包括：");
                            while (e.hasMoreElements()) {
                                String key = e.nextElement();
                                printStream.println(key + "=" + request.getParameter(key));
                            }
                            printStream.println();
                        }

                        printStream.println("堆栈信息");
                        exception.printStackTrace(printStream);
                        printStream.println();

                        out.print(byteArrayOutputStream);    //输出到网页

                        File dir = new File(request.getRealPath("/errorLog"));
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmssS").format(new Date());
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(dir.getAbsolutePath() + File.separatorChar + "error-" + timeStamp + ".txt"));
                        new PrintStream(fileOutputStream).print(byteArrayOutputStream); //写到文件

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                %>
            </pre>
        </div>
    </body>
</html>