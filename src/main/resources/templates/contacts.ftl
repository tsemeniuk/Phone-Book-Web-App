<!DOCTYPE html>
<html>
<head>
    <title>Phone Book</title>
    <!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="css/bootstrap-responsive.css" rel="stylesheet"/>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
    </style>
</head>
<body>


<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="#!/">Phone Book</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li><a href="#!/add">Add</a></li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>


<div class="container">
    <div class="row">
        <div class="span2">
            <h3 class="pagination-centered">Contacts:</h3>
        </div>
        <div class="span10">
            <h3 class="pagination-centered">Contact Details:</h3>
        </div>

    </div>

    <div class="row">

        <div class="span2">

            <ul class="nav nav-pills nav-stacked" id="contact-list-block">
                <!-- Contact list would be injected here by Backbone.js -->
            </ul>

        </div>

        <div class="span10">
            <form class="form-horizontal" id="contact-details-block">
                <!-- Contact details would be injected here by Backbone.js -->
            </form>
        </div>
    </div>

</div>