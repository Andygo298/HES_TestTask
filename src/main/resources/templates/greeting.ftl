<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Hello!!!</h5>
    <div>This is my test task.</div>
    <div class="alert alert-info" role="alert">
        What functionality does this application have?<br>
        Do you see the navbar in this page? ^_^<br>
        - If you click HOME, you'll see this greeting page.<br>
        - If you click Users list, you'd see the main page with Users list,<br>
        or else you hadn't authorized in system, you would be redirect on the Login page.<br>
        We have two roles in our application - USER or ADMIN.<br>
        - If you signed in as USER you would be redirect on the main page with users list.<br>
          And you can only browse through this list.<br>
        - If you signed in as ADMIN you also would be redirect on the main page with users list.<br>
        - But you'll get an extra tab on the navbar- Admin page!!!<br>
        And that’s awesome, isn’t it? If only because you'll can add new users and edit their information!<br>
        Also the field validation in the forms was implemented in our application.<br>
        Logout button was also implemented for you!<br>
        <br>
        List of technologies used in the development of the application:<br>
        1. Java 8+<br>
        2. Spring (Spring Boot, Spring MVC, Spring Data, Spring Security)<br>
        3. PostgreSQL<br>
        4. Html Template Engine - любой (Thymeleaf, Freemarker, etc.)<br>
        5. Gradle<br>
        <br>
        Have a good day!
    </div>
</@c.page>