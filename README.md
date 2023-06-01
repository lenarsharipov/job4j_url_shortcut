# "JOB4J_URL_SHORTCUT" REST API SERVICE

Client sends original long url, service converts int into short version and send back.

## DESCRIPTION:

### 1. Registering web site
Service could be used by any web-site. Each client receives login and password.

To sign-up client should sent request as follows:

<b>URL:</b>

<pre>POST /sites/sign-up</pre>

<b>With body as JSON object:</b>

<pre>{site : "job4j.ru"}</pre>

<b>Server response:</b>

<pre>{registration : true/false, login: UNIQUE_CODE, password : UNIQUE_CODE}</pre>

### 2. Authorization
Authorization is proceeded by means of JWT. Client sends POST request with username and password.
Server sends back a token in HEAD block of response:

<pre>Authorization: Bearer e25d31c5-db66-4cf2-85d4-8faa8c544ad6</pre>

### 3. Registering URL
After authorization client can send original url and get shortened modified version:

<b>Send original URL</b>
<pre>https://job4j.ru/profile/exercise/106/task-view/532</pre>

<b>Get shortened URL</b>
<pre>ZRUfdD2</pre>

ZRUfD2 key is associated with URL.

<b>Request:</b>
<pre>POST /convert</pre>

<b>Body: Json object</b>
<pre>{
    url: "https://job4j.ru/profile/exercise/106/task-view/532"
}</pre>

<b>Server Response:</b>
<pre>{code: UNIQUE_CODE}</pre>

### 4. Redirecting. Proceeded without authorization
Once web-site sends request with UNIQUE_CODE, server sends response with original URL and Status 302.

<b>Request:</b>
<pre>GET /redirect/UNIQUE_CODE</pre>

<b>Server response in head block.</b>
<pre>HTTP CODE - 302 REDIRECT URL</pre>

### 5. Statistics
Service counting each call of all persisted web-sites.

<b>Request:</b>
<pre>GET /statistic</pre>

<b>Response: JSON object.</b>
<pre>{
    {url : URL, total : 0},
    {url : "https://job4j.ru/profile/exercise/106/task-view/532", total : 103}
}</pre>

## Stack:
<ul>
    <li>Java 17</li>
    <li>Postgresql 14</li>
    <li>Spring Boot 2.7.12</li>
    <li>Spring Data Jpa 2.7.12</li>
    <li>Spring Security 2.7.12</li>
    <li>Hibernate 5.6.15</li>
    <li>Lombok 1.18.26</li>
    <li>Liquibase 3.6.2</li>
    <li>java-jwt 3.4.0</li>
    <li>Hibernate-validator 6.2.5.Final</li>
    <li>Jcip-annotations 1.0</li>
    <li>Checkstyle</li>
</ul>

## Environment requirements:
<ul>
    <li>Java 17</li>
    <li>Maven 3.8.7</li>
    <li>Postgresql 14</li>
</ul>

## How to start:
<ol>
    <li>Create "urls" database with username - "postgres", password - "password":
    <pre>CREATE DATABASE urls;</pre>
    </li>
    <li>
    Download zip archive of the project from current page.
    </li>
    <li>Unzip archive, open folder as a project in Intellij Idea.</li>
    <li>In Maven tab, start clean, test commands.</li>
    <li>Open Main.class and run main method.</li>
</ol>

## App testing in Postman:
<b>Sign up endpoint: <i>POST localhost:8080/sites/sign-up</i></b>

![001_sign_up.png](src%2Fmain%2Fresources%2Fstatic%2Fscreenshots%2F001_sign_up.png)

<b>Login endpoint: <i>POST localhost:8080/login</i></b>

![002_login_and_get_token.png](src%2Fmain%2Fresources%2Fstatic%2Fscreenshots%2F002_login_and_get_token.png)

<b>Convert endpoint <i>POST localhost:8080/convert/</i></b>

![003_convert_with_token.png](src%2Fmain%2Fresources%2Fstatic%2Fscreenshots%2F003_convert_with_token.png)

![004_convert.png](src%2Fmain%2Fresources%2Fstatic%2Fscreenshots%2F004_convert.png)

<b>Redirects endpoint <i>GET localhost:8080/redirects/{short_url}</i></b>

![005_redirect.png](src%2Fmain%2Fresources%2Fstatic%2Fscreenshots%2F005_redirect.png)

<b>Statistics endpoint <i>GET localhost:8080/statistic/</i></b>

![006_statistics.png](src%2Fmain%2Fresources%2Fstatic%2Fscreenshots%2F006_statistics.png)

### 6. Contacts
<ul>
    <li><a href="mailto:lenarsharipov@gmail.com">lenarsharipov@gmail.com</a></li>
    <li><a href="https://t.me/LenarSharipov" rel="nofollow">telegram</a></li>
</ul>
