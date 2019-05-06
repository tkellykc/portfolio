<!--
Timothy Kelly

NFL Team Name Trivia
-->

<!-- HTML documentation -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>NFL Team Name Trivia</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
</head>
<body>

<!-- open PHP element -->
<?php
// initializes variable as an array and fills will NFL team locartions and names
$TeamNames = array(
     "Kansas City" => "Chiefs",
     "Denver" => "Broncos",
     "Chicago" => "Bears",
     "Green Bay" => "Packers",
     "Detroit" => "Lions",
     "Minnesota" => "Vikings",
	 "Buffalo" => "Bills",
     "New England" => "Patriots",
     "Seattle" => "Seahawks",
	 "San Francisco" => "49ers",
     "Dallas" => "Cowboys",
     "Pittsburgh" => "Steelers"    
);

// if statement to receive input in fields and provide "correct" or "incorrect" responses
if (isset($_POST['submit'])) {
     $Answers = $_POST['answers'];
     if (is_array($Answers)) {
          foreach ($Answers as
               $Team => $Response) {
               $Response =
                    stripslashes($Response);
               if (strlen($Response)>0) {
                    if (strcasecmp(
                         $TeamNames[$Team],
                         $Response)==0)
						 // "correct" response
                         echo "<p>Correct! The
                              NFL team in $Team is the " .
                              $TeamNames[$Team] .
                              ".</p>\n";
                    else // "incorrect" response
                         echo "<p>Sorry, the NFL team
                              in $Team is not the '" .
                              $Response . "'.</p>\n";
               }
               else
                    echo "<p>You did not enter a
                         value for the NFL team in
                         $State.</p>\n";
          }
     }
     // link to reload the quiz
	 echo "<p><a href='NFLTeamNameTrivia.php'>
          Try again?</a></p>\n";
}
else {
     echo "<form action='NFLTeamNameTrivia.php'
          method='POST'>\n";
	 // 'foreach' statement to take in user input
     foreach ($TeamNames as
          $Team => $Response)
          echo "The NFL team in $Team is the:
               <input type='text' name='answers[" .
               $Team . "]' /><br />\n";
          echo "<input type='submit'
               name='submit'
               value='Check Answers' /> ";
          echo "<input type='reset' name='reset'
               value='Reset Form' />\n";
          echo "</form>\n";
}
?> <!-- end PHP element -->
</body>
</html>

