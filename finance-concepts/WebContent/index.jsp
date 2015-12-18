<!DOCTYPE html>
<%@ page import="java.sql.*" %>
<html lang="en">
<head>
  <title>Organization Manager</title>
  <meta charset="utf-8">
  <meta name="viewport" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  
  	<link type="text/css" rel="Stylesheet" href="http://jspexamples.jqwidgets.com/examples/jqwidgets/styles/jqx.base.css" />
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/scripts/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/jqwidgets/jqxdata.js"></script>
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/jqwidgets/jqxdropdownlist.js"></script>
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/scripts/demos.js"></script>
    
    <!-- For the chart -->
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/jqwidgets/jqxdraw.js"></script>
    <script type="text/javascript" src="http://jspexamples.jqwidgets.com/examples/jqwidgets/jqxchart.core.js"></script>
    
    
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    .form-control-inline {
	    min-width: 0;
	    width: 9;
	    display: inline;
	}
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
    
    .demo-iframe {
            border: none;
            width: 600px;
            height: 400px;
            clear: both;
        }
  </style>
  
  <script type="text/javascript">
        $(document).ready(
            function () {
                var source = {
                    datatype: "json",
                    datafields: [{
                        name: 'YieldCurveId',
                        type: 'int'
                    }, {
                        name: 'CurveName',
                        type: 'string'
                    }],
                    id: 'YieldCurve',
                    url: 'available-curves-list.jsp',
                    async: true
                };
                var dataAdapter = new $.jqx.dataAdapter(source);
                $("#jqxDropDownList").jqxDropDownList({
                    source: dataAdapter,
                    width: 300,
                    height: 25,
                    selectedIndex: 0,
                    displayMember: 'CurveName',
                    valueMember: 'CurveName'
                });
                $('#sendButton').jqxButton({
                    width: 70
                });
            });
    </script>
    
    
    
    <!-- For the chart -->
    <script type="text/javascript">
        $(document).ready(function () {
            // prepare the data
            var source =
            {
                datatype: "tab",
                datafields: [
                    { name: 'Term' },
                    { name: 'Rate' }
                ],
                url: 'http://localhost:8080/finance-concepts/DataServlet',
                type: 'GET'
            };

            var dataAdapter = new $.jqx.dataAdapter(source, { async: false, autoBind: true, loadError: function (xhr, status, error) { alert('Error loading "' + source.url + '" : ' + error); } });

            // prepare jqxChart settings
            var settings = {
                title: "Daily Treasury Yield Curve",
                description: "Obtained from www.treasury.gov",
                enableAnimations: true,
                showLegend: true,
                padding: { left: 10, top: 5, right: 10, bottom: 5 },
                titlePadding: { left: 10, top: 0, right: 0, bottom: 10 },
                source: dataAdapter,
                xAxis:
                {
                	dataField: 'Term',
                    type: 'date',
                    baseUnit: 'year',
                    valuesOnTicks: true,
                    /* labels:
                    {
                        formatFunction: function (value) {
                            return value.getDate();
                        }
                    },
                    toolTipFormatFunction: function (value) {
                        var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
                        return value.getDate() + '-' + months[value.getMonth()] + '-' + value.getFullYear();
                    }, */
                },
                valueAxis:
                {
                    unitInterval: 0.5,
                    minValue: 0,
                    maxValue: 4,
                    title: {text: 'Rates'}
                },
                colorScheme: 'scheme03',
                seriesGroups:
                    [
                        {
                            type: 'stackedarea',
                            series: [
                                    { dataField: 'Rate', displayText: 'Term' }
                                ]
                        }
                    ]
            };

            // setup the chart
            $('#chartContainer').jqxChart(settings);
        });
    </script>
    
    
</head>
<body class='default'>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Org1 Registration</a></li>
        <li><a href="#">Org2 Registration</a></li>
        <li><a href="#">Other</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center">    

  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
    </div>
    
    
    
    <div class="col-sm-8 text-left"> 
		<div class="container">
		  <div class="container">
			  
			  <br>
			  <label>Select a Yield Curve:</label>
			  
			  <form class="form" id="form" target="form-iframe" method="post" action="RetrieveDataServlet" 
			  	style="font-size: 13px; font-family: Verdana; width: 650px;">
			  	
	        		<div id="jqxDropDownList" name="yieldCurveSelection"></div>
	        		<input style="margin-top: 10px;" type="submit" value="Submit" id="sendButton" />
		      </form>
		      
		      
				<div id='chartContainer' style="width:750px; height:375px">
    			</div>
		      
			    <div>
			        <iframe id="form-iframe" name="form-iframe" class="demo-iframe" frameborder="0"></iframe>
			    </div>
			  
			</div>
			
		</div>
    </div>
    
    
    <div class="col-sm-2 sidenav">
      <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div>
    
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>


    			
</body>
</html>
