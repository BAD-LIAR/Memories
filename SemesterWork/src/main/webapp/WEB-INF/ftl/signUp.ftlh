<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="/styles/login.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <title>Login</title>
    <style>
        .error {
            color: red;
        }

    </style>
</head>
<body>
<div class="login">
    <h2><a href="?lang=ru">РУ</a> <a href="?lang=en">EN</a> </h2>
<#--    <h2><a href="?lang=en">EN</a> </h2>-->
    <form method="post">
        <div class="login_field">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <label for="email"><@spring.message 'sign_up_page.registration.enter_email'/></label>
            <br>
            <@spring.formInput "userForm.email"/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="login_field">
            <label for="password"><@spring.message 'sign_up_page.registration.enter_password'/></label>
            <br>
            <@spring.formPasswordInput "userForm.password"/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="login_field">
            <label for="firstName"><@spring.message 'sign_up_page.registration.enter_firstname'/></label>
            <br>
            <@spring.formInput "userForm.firstName"/>
            <@spring.showErrors "<br>" "error"/>
        </div>

        <div class="login_field">
            <label for="lastName"><@spring.message 'sign_up_page.registration.enter_lastname'/></label>
            <br>
            <@spring.formInput "userForm.lastName"/>
            <@spring.showErrors "<br>" "error"/>
        </div>




        <div class="login_field row">
            <button class="form-control col-sm-6" type="submit"><@spring.message 'sign_up_page.registration.title'/></button>
        </div>


        <a href="/signIn">Есть аккаунт</a>



    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>

</body>
</html>
