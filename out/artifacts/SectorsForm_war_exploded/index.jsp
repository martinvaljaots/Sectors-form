<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="generator" content="HTML Tidy for HTML5 for Linux version 5.2.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="app.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="mystyle.css">
    <title>Form submission</title>
</head>
<body>
Please enter your name and pick the Sectors you are currently involved in.
<br>
<br>
<form onsubmit="return false">
    Name:&nbsp;&nbsp;
    <input type="text" class="box" name="name" id="nameID">
    <br>
    <br>
    Sectors: <select class="box" multiple size="10" name="sectors" id="sectorsID"></select>
    <br>
    <br>
    Agree to terms:
    <input type="checkbox" name="agree" id="agreeID" value="true">
    <br>
    <br>
    <input type="submit" id="submit" value="Save" name="submit">
</form>
</body>
</html>
